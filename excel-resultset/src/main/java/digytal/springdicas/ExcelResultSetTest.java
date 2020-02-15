package digytal.springdicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExcelResultSetTest {
	private Titulo titulo;
	@BeforeEach
	public void testeLeituraXls() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("xls/titulos.xlsx");
		try {
			ExcelResultSet exrs = new ExcelResultSet(inputStream);
			titulo = new Titulo();
			while(exrs.next()) {
				
				titulo.setNome(exrs.getString("Nome"));
				titulo.setCpf(exrs.getString("Cpf"));
				titulo.setNumero(exrs.getInteger("Numero"));
				titulo.setLocalDateVencimento(exrs.getLocalDate("Data Vencimento"));
				titulo.setValor(exrs.getDouble("Valor"));
				titulo.setPago(exrs.getBoolean("Pago","Sim"));
				titulo.setDateTimeProcessamento(exrs.getLocalDateTime("Dh Processamento"));
				
				
				
				titulo.setValue(exrs.getBigDecimal("Valor"));
				titulo.setDataVencimento(exrs.getDate("Data Vencimento"));
				break;
				
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}	
	}
	@Test
	public void booleanTest() {
		assertTrue(titulo.getPago());
	}
	@Test
	public void intTest() {
		Integer value=137;
		assertEquals(value, titulo.getNumero());
	}
	@Test
	public void doubleTest() {
		Double value=1235.47;
		assertEquals(value, titulo.getValor());
	}
	@Test
	public void bigDecimalTest() {
		BigDecimal value=new BigDecimal(1235.47);
		assertEquals(value, titulo.getValue());
	}
	@Test
	public void stringTest() {
		String value="RAIMUNDO JOSE";
		assertEquals(value, titulo.getNome());
	}
	@Test
	public void dateTest() {
		Date value=new GregorianCalendar(2020, 2, 10).getTime();
		assertEquals(value, titulo.getDataVencimento());
	}
	@Test
	public void localDateTest() {
		LocalDate value=LocalDate.of(2020, 3, 10);
		assertEquals(value, titulo.getLocalDateVencimento());
	}
	@Test
	public void localDateTimeTest() {
		LocalDateTime value=LocalDateTime.of(2020, 2, 14,16,0,0);
		assertEquals(value, titulo.getDateTimeProcessamento());
	}
}

class Titulo{
	private String nome;
	private String cpf;
	private Integer numero;
	private Date dataVencimento;
	private LocalDate localDateVencimento;
	private Date dateProcessamento;
	private LocalDateTime dateTimeProcessamento ;
	private Double valor;
	private BigDecimal value;
	private Boolean pago;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public LocalDate getLocalDateVencimento() {
		return localDateVencimento;
	}
	public void setLocalDateVencimento(LocalDate localDateVencimento) {
		this.localDateVencimento = localDateVencimento;
	}
	public Date getDateProcessamento() {
		return dateProcessamento;
	}
	public void setDateProcessamento(Date dateProcessamento) {
		this.dateProcessamento = dateProcessamento;
	}
	public LocalDateTime getDateTimeProcessamento() {
		return dateTimeProcessamento;
	}
	public void setDateTimeProcessamento(LocalDateTime dateTimeProcessamento) {
		this.dateTimeProcessamento = dateTimeProcessamento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Boolean getPago() {
		return pago;
	}
	public void setPago(Boolean pago) {
		this.pago = pago;
	}
	@Override
	public String toString() {
		return "Titulo [nome=" + nome + ", cpf=" + cpf + ", numero=" + numero + ", dataVencimento=" + dataVencimento
				+ ", localDateVencimento=" + localDateVencimento + ", dateProcessamento=" + dateProcessamento
				+ ", dateTimeProcessamento=" + dateTimeProcessamento + ", valor=" + valor + ", value=" + value
				+ ", pago=" + pago + "]";
	}
	
	
	
	
}
