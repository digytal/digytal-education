package digytal.springdicas.utils.jpa;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchParams implements Map<String, Object>, Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> params = Collections.emptyMap();
	
	public static SearchParams of(Map<String, Object> map) {
		SearchParams instance = new SearchParams();
		instance.params = map;
		return instance;
	}
	
	public Object getValue(String name) {
		return params.get(name);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String name) {
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof List) return (List<T>)value;
		return Collections.singletonList((T)value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String name, Class<T> type) {
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof List) return (List<T>)value;
		if (value instanceof String) {
			if (value.toString().startsWith("[") && value.toString().endsWith("]")) {
				try {
					Method valueOfMethod = type.getMethod("valueOf", String.class);
					String values = value.toString().substring(1, value.toString().length() - 1);
					List<T> items = Arrays.stream(values.split(", "))
						.map(it -> { try { return (T)valueOfMethod.invoke(null, it); } catch(Exception e) { throw new RuntimeException(e); } })
						.collect(Collectors.toList());
					return items;
				} catch (NoSuchMethodException | SecurityException e) {
					throw new IllegalStateException("Error get list value from params | name=" + name + " | type=" + type.getName(), e);
				}
			}
		}
		return Collections.singletonList((T)value);
	}
	
	public <T> List<T> getNotEmptyList(String name) {
		List<T> list = getList(name);
		return list.isEmpty() ? null : list;
	}
	
	public String getString(String name) {
		final Object value = params.get(name);
		if (value instanceof String) {
			return (String)value;
		}
		return Objects.toString(value, null);
	}
	
	public String getTrimmedString(String name) {
		final String value = getString(name);
		return value == null ? null : value.trim();
	}
	
	public String getNotEmptyString(String name) {
		final Object value = params.get(name);
		if (value instanceof String) {
			return value == null || value.toString().isEmpty() ? null : (String)value;
		}
		return Objects.toString(value, null);
	}
	
	public String getNotBlankString(String name) {
		final Object value = params.get(name);
		if (value instanceof String) {
			return isBlank(value) ? null : (String)value;
		}
		return Objects.toString(value, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getStrings(String name) {
		final Object value = params.get(name);
		
		return (List<String>)value;
	}
	
	public Integer getInt(String name) {
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof Integer) return (Integer)value;
		return isBlank(value) ? null : Integer.valueOf(value.toString().trim());
	}
	
	public Long getLong(String name) {
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof Long) return (Long)value;
		return isBlank(value) ? null : Long.valueOf(value.toString().trim());
	}

	public Double getDouble(String name) {
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof Double) return (Double)value;
		return isBlank(value) ? null : Double.valueOf(value.toString().trim());
	}
	
	public BigDecimal getBigDecimal(String name, BigDecimal defaultValue) {
		final BigDecimal value = getBigDecimal(name);
		return value != null ? value : defaultValue;
	}
	
	public BigDecimal getBigDecimal(String name) {
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof BigDecimal) return (BigDecimal)value;
		return isBlank(value) ? null : new BigDecimal(value.toString().trim());
	}
	
	public LocalDate getLocalDate(String name) {
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof LocalDate) return (LocalDate)value;
		if (value.toString().length() > 10) return getLocalDateTime(name).toLocalDate();
		return isBlank(value) ? null : LocalDate.parse(value.toString().trim());
	}
	
	public Date getDateTime(String name) {
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof Date) return (Date)value;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		try {
			return isBlank(value) ? null : dateFormat.parse(value.toString().trim());
		} catch (ParseException e) {
			throw new IllegalStateException("Error parsing dateTime param name: " + name + " | value: " + value, e);
		}
	}
	
	public LocalDateTime getLocalDateTime(String name) {
		if (name == null || name.trim().length() == 0){
			return null;
		}
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof LocalDateTime) return (LocalDateTime)value;
		return isBlank(value) ? null : LocalDateTime.parse(value.toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
	
	public Boolean getBoolean(String name) {
		Object value = params.get(name);
		if (value == null) return null;
		if (value instanceof Boolean) return (Boolean)value;
		return isBlank(value) ? null : Boolean.valueOf(value.toString());
	}
	
	
	public Object getOrDefault(String name, Object defaultValue) {
		return params.getOrDefault(name, defaultValue);
	}
	
	public boolean contains(String name) {
		return params.containsKey(name);
	}
	
	public boolean isSet(String name) {
		return params.get(name) != null;
	}
	
	public Map<String, Object> getParams() {
		return params;
	}
	
	public Map<String, Object> getSubParams(String ... names) {
		Map<String, Object> subParams = new LinkedHashMap<>();
		for (String name : names) {
			subParams.put(name, params.get(name));
		}
		return subParams;
	}
	
	public SearchParams add(String name, Object value) {
		if (params == Collections.EMPTY_MAP) {
			params = new HashMap<>();
		}
		params.put(name, Objects.toString(value, null));
		return this;
	}
	
	public <T> T getEnum(String name, Class<T> enumClass) {
		Object value = params.get(name);
		if (value == null) return null;
		if (enumClass.isInstance(value)) return enumClass.cast(value);
		for (T enumItem : enumClass.getEnumConstants()) {
			 if (enumItem.toString().equals(value)) {
				 return enumItem;
			 }
		}
		// logger.warn("Enum not found | value: {} | enumClass: {}", value, enumClass.getName());
		return null;
	}
	
	protected boolean isBlank(Object value) {
		return value == null || value.toString().isEmpty();
	}

	@Override
	public void clear() {
		params.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return params.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return params.containsValue(value);
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return params.entrySet();
	}

	@Override
	public Object get(Object key) {
		return params.get(key);
	}

	@Override
	public boolean isEmpty() {
		return params.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return params.keySet();
	}

	@Override
	public Object put(String key, Object value) {
		return params.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		params.putAll(m);
	}

	@Override
	public Object remove(Object key) {
		return params.remove(key);
	}

	@Override
	public int size() {
		return params.size();
	}

	@Override
	public Collection<Object> values() {
		return params.values();
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
