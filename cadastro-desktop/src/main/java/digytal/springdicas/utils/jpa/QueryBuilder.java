package digytal.springdicas.utils.jpa;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import digytal.springdicas.utils.Strings;

public class QueryBuilder<T> {
	
	static final Logger logger = LoggerFactory.getLogger(QueryBuilder.class);
	
	protected EntityManager em;
	
	protected Class<T> entityClass;
	protected WhereParts<T> whereParts;
	protected Integer offset;
	protected Integer limit = 200;
	protected String[] sort;
	protected String alias = "a";	
	protected List<Join> joins = new ArrayList<>();
	protected int joinIndex = 0;
	protected Map<String, Object> parameters;
	protected Map<String, String> sortReplace = Collections.emptyMap();
	protected String[] columns = {};
	protected String[] groupBy = {};
	
	public static <T> QueryBuilder<T> of() {
		return new QueryBuilder<T>();
	}
	
	
	public static <T> QueryBuilder<T> of(Class<T> entityClass) {
		return new QueryBuilder<T>()
			.select()
			.from(entityClass)
		;
	}
	
	public QueryBuilder<T> withEntityManager(EntityManager em) {
		this.em = em;
		return this;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	
	public QueryBuilder<T> setAlias(String alias) {
		this.alias = alias;
		return this;
	}
	
	public QueryBuilder<T> select() {
		return this;
	}
	
	public QueryBuilder<T> select(String ... columns) {
		this.columns = columns;
		return this;
	}
	
	public QueryBuilder<T> from(Class<T> entityClass) {
		this.entityClass = entityClass;
		return this;
	}
	
	public QueryBuilder<T> join(String attribute, String alias) {
		return join(attribute, alias, false, JoinType.INNER);
	}
	
	public QueryBuilder<T> joinFetch(String attribute) {
		return joinFetch(attribute, attribute);
	}
	
	public QueryBuilder<T> joinFetch(String attribute, String alias) {
		return join(attribute, alias, true, JoinType.INNER);
	}
	
	public QueryBuilder<T> leftJoinFetch(String attribute) {
		return leftJoinFetch(attribute, attribute);
	}
	
	public QueryBuilder<T> leftJoinFetch(String attribute, String alias) {
		return join(attribute, alias, true, JoinType.LEFT);
	}
	
	public QueryBuilder<T> removeJoin(String attribute) {
		joins.removeIf(it -> it.attribute.equals(attribute));
		return this;
	}
	
	private QueryBuilder<T> join(String attribute, String alias, boolean fetch, JoinType type) {
		Join join = new Join();
		join.attribute = attribute;
		join.alias = alias;
		join.fetch = fetch;
		join.type = type;
		joins.add(join);
		return this;
	}
	
	public QueryBuilder<T> joinsFetch(String ... attributes) {
		for (String attribute : attributes) {
			joinFetch(attribute, "j" + (joinIndex++));
		}
		return this;
	}
	
	public WhereParts<T> where() {
		if (whereParts == null) {
			whereParts = new WhereParts<T>(this);
		}
		return whereParts;
	}
	
	public QueryBuilder<T> where(WhereParts<T> whereParts) {
		this.whereParts = whereParts;
		return this;
	}
	
	public QueryBuilder<T> whereParam(String field, Object value) {
		if (value instanceof Collection<?>) {
			where().add(field).in(value);
		} else {
			where().add(field).eq(value);
		}
		return this;
	}
	
	public QueryBuilder<T> whereParam(String field, Operator operator, Object value) {
		where().add(field, operator, value);
		return this;
	}
	
	public QueryBuilder<T> orderBy(String ... sort) {
		return sort(sort);
	}
	
	public QueryBuilder<T> groupBy(String ... groupBy) {
		this.groupBy = groupBy;
		return this;
	}
	
	public QueryBuilder<T> sort(String ... sort) {
		this.sort = sort;
		return this;
	}
	
	public QueryBuilder<T> offset(Integer offset) {
		this.offset = offset;
		return this;
	}
	
	public QueryBuilder<T> limit(Integer limit) {
		this.limit = limit;
		return this;
	}
	
	public QueryBuilder<T> putSortReplace(String field, String replace) {
		if (sortReplace == Collections.EMPTY_MAP) {
			sortReplace = new HashMap<>();
		}
		sortReplace.put(field, replace);
		return this;
	}
	
	public Query build() {
		return build(em);
	}
	
	public TypedQuery<Long> buildCount() {
		String jpql = buildCountJpql();
		
		logger.debug("Creating count query for jpql: {}", jpql);
		
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		
		setParameterValues(query);
		
		return query;
	}
	
	public Query build(EntityManager em) {
		this.em = em;
		
		String jpql = buildJpql();
		
		logger.debug("Creating query for jpql: {}", jpql);
		
		Query query = createQuery(jpql);
			
		if (offset != null) {
			//query.setFirstResult(offset);
		}
		
		if (limit != null) {
			//query.setMaxResults(limit);
		}
		
		setParameterValues(query);
		
		if (logger.isTraceEnabled()) {
			logger.trace(
				"Query builded | jpql: {} | offset: {} | limit: {} | params: {}", 
				jpql, offset, limit, query.getParameters().stream()
					.map(it -> it.getName() + "=" + query.getParameterValue(it.getName()))
					.collect(Collectors.joining(",")) 
			);
		}
		
		return query;
	}
	
	protected Query createQuery(String jpql) {
		if (this.columns != null && this.columns.length > 0) {
			return em.createQuery(jpql);
		}
		return em.createQuery(jpql, entityClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> getResultList() {
		return (List<T>)build().getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public T getSingleResult() {
		try {
			return (T)build()
				.setMaxResults(1)
				.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	public int executeUpdate() {
		return build()
			.executeUpdate();		
	}
	
	public Long count() {
		return buildCount().getSingleResult();
	}
	
	private void setParameterValues(Query query) {
		if (whereParts == null) {
			return;
		}
		
		for (Map.Entry<String, Object> param : parameters.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
	}

	protected String buildJpql() {
		StringBuilder sb = new StringBuilder();
		
		String strColumns = alias;
		if (columns.length > 0) {
			strColumns = String.join(", ", columns);
		}
		
		sb.append(MessageFormat.format("SELECT {0} FROM {1} {2}", strColumns, entityClass.getSimpleName(), alias));
		sb.append(buildJoins(true));
		
		String where = buildWhere();
		
		if (!where.isEmpty()) {
			sb.append(" WHERE ").append(where);			
		}
		
		if (groupBy != null && groupBy.length > 0) {
			sb.append(" GROUP BY ").append(String.join(", ", groupBy));			
		}
		
		if (sort != null && sort.length > 0) {
			String orderByWithAlias = Arrays.stream(sort)
				.map(it -> sortReplace.getOrDefault(it, alias + "." + it))
				.collect(Collectors.joining(", "));
			sb.append(" ORDER BY ").append(orderByWithAlias);
		}
		
		return sb.toString();
	}
	
	private String buildCountJpql() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(MessageFormat.format("SELECT COUNT({0}) FROM {1} {0}", alias, entityClass.getSimpleName()));
		sb.append(buildJoins(false));
		
		String where = buildWhere();
		
		if (!where.isEmpty()) {
			sb.append(" WHERE ").append(where);
		}
		
		return sb.toString();
	}

	protected String buildJoins(boolean includeFetchs) {
		if (joins.isEmpty()) return "";
		StringBuilder sb = new StringBuilder();
		for (Join join : joins) {
			String fetch = join.fetch && includeFetchs ? "FETCH" : "";
			String type = join.type == JoinType.INNER ? "" : join.type.toString();
			
			sb.append(String.format(" %s JOIN %s %s.%s %s", type, fetch, alias, join.attribute, join.alias));
		}
		return sb.toString();
	}

	protected String buildWhere() {
		if (whereParts == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int paramIndex = 0;		
		parameters = new HashMap<>();
		for (WherePart part : whereParts.parts) {
			String fieldWithAlias = alias + "." + part.field;
			String not = part.not ? "NOT " : "";
			
			if (!joins.isEmpty()) {
				boolean hasAlias = joins.stream()
					.filter(it -> part.field.startsWith(it.alias + "."))
					.findAny().isPresent();
				if (hasAlias) {
					fieldWithAlias = part.field;
				}
			}
			
			if (sb.length() > 0) {
				sb.append(" AND ");
			}
			if (part.op == Operator.EXISTS) {
				sb.append(String.format("%s%s EXISTS ( %s )", not, part.field.isEmpty() ? "" : fieldWithAlias, part.value));
				parameters.putAll(part.multipleValues);
			} else if (part.op == Operator.IS && part.value == null) {
				sb.append(String.format("%s%s IS NULL", fieldWithAlias, not));
			} else if (part.op == Operator.BETWEEN) {
				Object[] values = (Object[]) part.value;
				sb.append(String.format("%s%s %s :p%s AND :p%s", not, fieldWithAlias, part.op.symbol, paramIndex, paramIndex + 1));
				parameters.put("p" + paramIndex, values[0]);
				parameters.put("p" + (paramIndex + 1), values[1]);
				paramIndex = paramIndex + 2;
			} else {
				sb.append(String.format("%s%s %s :p%s", not, fieldWithAlias, part.op.symbol, paramIndex));
				parameters.put("p" + paramIndex, part.value);
				paramIndex++;
			}
		}
		return sb.toString();
	}

	public enum Operator {
		EQUALS("="), 
		BETWEEN("BETWEEN"), 
		LIKE("LIKE"), 
		EXISTS("EXISTS"),
		LESS_EQUAL("<="), 
		GREATER_EQUAL(">="), 
		LESS_THAN("<"), 
		GREATER_THAN(">"),
		IN("IN"),
		NO_EQUAL("!="),
		IS("IS");
		
		String symbol;
		
		Operator(String op) {
			this.symbol = op;
		}		
	}
	
	static class WherePart {
		boolean not;
		String field;
		Operator op;
		Object value;
		Map<String, Object> multipleValues; 
	}
	
	public static class Join {
		JoinType type = JoinType.INNER;
		String attribute;
		String alias;
		boolean fetch;
	}
	
	public static class WhereParts<T> {
		protected QueryBuilder<T> queryBuilder; 
		protected List<WherePart> parts = new ArrayList<>();
		protected WherePart currentPart;
		protected boolean ifSet, ifNotEmpty;
		
		WhereParts(QueryBuilder<T> queryBuilder) {
			this.queryBuilder = queryBuilder;
		}
		
		public WhereParts<T> addIfSet(String field) {
			ifSet = true;
			return add(field);
		}
		
		public WhereParts<T> addIfNotEmpty(String field) {
			ifSet = true;
			ifNotEmpty = true;
			return add(field);
		}
		
		public WhereParts<T> addIfPresent(String field) {
			ifSet = true;
			ifNotEmpty = true;
			return add(field);
		}
		
		public WhereParts<T> add(String field) {
			currentPart = new WherePart();
			currentPart.field = field;
			return this;
		}
		
		public WhereParts<T> add(String field, Operator op, Object value) {
			add(field);
			finishPart(op, value);
			return this;
		}
		
		public WhereParts<T> not() {
			if (currentPart == null) {
				currentPart = new WherePart();
			}
			currentPart.not = true;
			return this;
		}
		
		public WhereParts<T> isNull() {
			finishPart(Operator.IS, null);
			return this;
		}
		
		public WhereParts<T> eq(Object value) {
			finishPart(Operator.EQUALS, value);
			return this;
		}
		
		public WhereParts<T> noEq(Object value) {
			finishPart(Operator.NO_EQUAL, value);
			return this;
		}
		
		public WhereParts<T> lessThan(Object value) {
			finishPart(Operator.LESS_THAN, value);
			return this;
		}
		
		public WhereParts<T> greaterThan(Object value) {
			finishPart(Operator.GREATER_THAN, value);
			return this;
		}
		
		public WhereParts<T> lessEqual(Object value) {
			finishPart(Operator.LESS_EQUAL, value);
			return this;
		}
		
		public WhereParts<T> greaterEqual(Object value) {
			
			if (value == null){
				return this;
			}
			
			finishPart(Operator.GREATER_EQUAL, value);
			return this;
		}
		
		public WhereParts<T> in(Object ... values) {
			if (values.length == 1 && values[0] instanceof Collection) {
				finishPart(Operator.IN, values[0]);
			} else {
				finishPart(Operator.IN, values);
			}
			return this;
		}
		
		public QueryBuilder<T> end() {
			return queryBuilder;
		}
		
		public Query build() {
			return build(queryBuilder.em);
		}
		
		public Query build(EntityManager em) {
			return queryBuilder.build(em);
		}
		
		public QueryBuilder<T> orderBy(String ... sort) {
			return queryBuilder.orderBy(sort);
		}
		
		private WhereParts<T> finishPart(Operator op, Object value) {
			if (ifSet && value == null) {
				resetCurrent();
				return this;
			}
			if (ifNotEmpty && isEmpty(value)) {
				resetCurrent();
				return this;
			}
			
			currentPart.op = op;
			currentPart.value = value;
			parts.add(currentPart);
			resetCurrent();
			
			return this;
		}
		
		private boolean isEmpty(Object value) {
			if (value == null) {
				return true;
			}
			if (value instanceof String) {
				return value.toString().isEmpty();
			}
			if (value instanceof Collection) {
				return ((Collection<?>)value).isEmpty();
			}
			if (value.getClass().isArray()) {
				return ((Object[])value).length == 0;
			}
			return false;
		}

		private void resetCurrent() {
			ifSet = false;
			ifNotEmpty = false;
			currentPart = null;
		}

		public WhereParts<T> startsWith(String value) {
			finishPart(Operator.LIKE, Strings.isEmpty(value) ? value : value + "%");
			return this;
		}
		
		public WhereParts<T> contains(String value) {
			finishPart(Operator.LIKE, Strings.isEmpty(value) ? value : "%" + value + "%");
			return this;
		}
		
		public WhereParts<T> endsWith(String value) {
			finishPart(Operator.LIKE, Strings.isEmpty(value) ? value : "%" + value);
			return this;
		}
		
		public WhereParts<T> exists(String subSelect, Map<String, Object> params) {
			if (currentPart == null) add("");
			currentPart.multipleValues = params;
			finishPart(Operator.EXISTS, subSelect);
			return this;
		}

		public WhereParts<T> between(Object begin, Object end) {
			finishPart(Operator.BETWEEN, new Object[] { begin, end });
			return this;
		}
		
	}
	
}
