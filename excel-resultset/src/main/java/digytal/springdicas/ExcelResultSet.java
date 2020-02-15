package digytal.springdicas;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelResultSet {
	private Workbook workbook;;

	private Sheet sheet;

	private int sheetIndex = 0;

	private int HEADER_INDEX = 0;

	private int lastRow = 0;

	private int currentRow = 1;

	private Map<String, Integer> layout;
	private Map<String, Object> rowData;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public void setDateFormat(String format) {
		dateFormat = new SimpleDateFormat(format);
	}

	public void setLocalDateFormat(String format) {
		localDateFormatter = DateTimeFormatter.ofPattern(format);
	}

	public void setLocalDateTimeFormat(String format) {
		localDateTimeFormatter = DateTimeFormatter.ofPattern(format);
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public ExcelResultSet(File file) throws Exception {
		workbook = WorkbookFactory.create(new FileInputStream(file));
	}

	public ExcelResultSet(InputStream inputStream) throws Exception {
		workbook = WorkbookFactory.create(inputStream);
	}

	public Date getDate(String field) throws ParseException {
		Object date = get(field);
		if (isNullOrEmpty(date))
			return null;

		if (date instanceof String)
			return dateFormat.parse(date.toString());
		else
			return (Date) date;
	}

	public LocalDate getLocalDate(String field) {
		Object date = get(field);
		if (isNullOrEmpty(date))
			return null;

		if (date instanceof String)
			return LocalDate.parse(date.toString(), localDateFormatter);
		else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) date);
			LocalDate localDate = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId())
					.toLocalDate();
			return localDate;
		}
	}

	public LocalDateTime getLocalDateTime(String field) {
		Object date = get(field);
		if (isNullOrEmpty(date))
			return null;

		if (date instanceof String)
			return LocalDateTime.parse(date.toString(), localDateTimeFormatter);
		else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) date);
			LocalDateTime localDateTime = LocalDateTime.ofInstant(calendar.toInstant(),
					calendar.getTimeZone().toZoneId());
			return localDateTime;
		}
	}

	public Boolean getBoolean(String field, String trueValue) {
		Object value = get(field);
		if (isNullOrEmpty(value))
			return null;

		if (value instanceof Double) {
			value = ((Double) value).intValue();
		}
		return value.toString().equals(trueValue) ? true : false;
	}

	public BigDecimal getBigDecimal(String field) {
		Double value = getDouble(field);
		if (isNullOrEmpty(value))
			return null;

		return new BigDecimal(value);
	}

	public Double getDouble(String field) {
		Double value = get(field);
		if (isNullOrEmpty(value))
			return null;

		return value;

	}

	public String getString(String field) {
		String value = get(field);
		if (isNullOrEmpty(value))
			return null;

		return value;
	}

	public Integer getInteger(String field) {
		Double value = get(field);
		if (isNullOrEmpty(value))
			return null;

		return value.intValue();
	}

	private boolean isNullOrEmpty(Object value) {
		return value == null || value.toString().isEmpty();
	}

	public <T> T get(String field) {
		final Object value = rowData.get(field);
		return (T) value;

	}

	public boolean next() {
		initSheet();
		if (layout == null) {
			initLayout();
		}
		if (currentRow == (lastRow + 1))
			return false;

		readRow();
		currentRow++;
		return true;
	}

	private void initSheet() {
		if (sheet == null) {
			sheet = workbook.getSheetAt(sheetIndex);
			lastRow = sheet.getLastRowNum();
		}
	}

	private void initLayout() {
		layout = new HashMap<String, Integer>();
		Row row = sheet.getRow(HEADER_INDEX);
		Iterator<Cell> cellIterator = row.cellIterator();
		int col = 0;
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String label = cell.getStringCellValue();
			layout.put(label, col++);
		}
	}

	private void readRow() {
		rowData = new HashMap<String, Object>();
		Row row = sheet.getRow(currentRow);
		layout.forEach((k, v) -> {
			Cell cell = row.getCell(v);
			readColls(k, cell);
		});

	}

	private void readColls(String header, Cell cell) {
		Object value = readColls(cell);
		rowData.put(header, value);
	}

	private Object readColls(Cell cell) {
		if(cell==null || cell.getCellTypeEnum()==CellType._NONE)
			return null;
		Object value = null;
		CellType type = cell.getCellTypeEnum() == CellType.FORMULA ? cell.getCachedFormulaResultTypeEnum()
				: cell.getCellTypeEnum();
		switch (type) {
		case BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				value = cell.getDateCellValue();
			} else {
				value = cell.getNumericCellValue();
			}
			break;
		case BLANK:
			value = "";
			break;
		}
		return value;
	}

	public int getLastRow() {
		return lastRow;
	}

	public int getCurrentRow() {
		return currentRow - 1;
	}
}
