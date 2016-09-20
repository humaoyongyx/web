package issac.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import issac.demo.model.ResourceBean;

public class ExcelUtils {
	public static <E> void exportExcel(String fileName, List<E> data, HttpServletResponse response) {
		XSSFWorkbook wb = new XSSFWorkbook();
		exportExcel(fileName, fileName, null, data, wb, true, response);
	}

	public static <E> void exportExcel(String fileName, String[] headerNames, List<E> data, HttpServletResponse response) {
		List<ExcelExportSetting> headerList = new LinkedList<>();
		XSSFWorkbook wb = new XSSFWorkbook();
		for (int i = 0; i < headerNames.length; i++) {
			String headName = headerNames[i];
			ExcelExportSetting excelSetting = getDefaultExcelSetting(wb);
			headerList.add(excelSetting.setHeadName(headName));
		}
		exportExcel(fileName, fileName, headerList, data, wb, false, response);
	}

	public static <E> void exportExcel(String fileName, String sheetName, String[] headerNames, String[] fieldNames, List<E> data, HttpServletResponse response) {
		List<ExcelExportSetting> headerList = new LinkedList<>();
		XSSFWorkbook wb = new XSSFWorkbook();
		for (int i = 0; i < headerNames.length; i++) {
			String headName = headerNames[i];
			ExcelExportSetting excelSetting = getDefaultExcelSetting(wb);
			headerList.add(excelSetting.setHeadName(headName).setFieldName(fieldNames[i]));
		}
		exportExcel(fileName, sheetName, headerList, data, wb, false, response);
	}

	/**
	 * 
	 * @param fileName  导出的Excel名称
	 * @param sheetName 导出的Excel第一个sheet的名称
	 * @param headers 可以设置headerName、fieldName、样式等信息，注意header和field是有顺序的，并且保证它们的顺序和数量一致
	 * @param data 导出的Excel数据
	 * @param wb   
	 * @param enableFieldsAutoGet  开启之后，header和field将自动利用Java反射字段来获取，并且使用默认的样式
	 * @param response 
	 */
	public static <E> void exportExcel(String fileName, String sheetName, List<ExcelExportSetting> headers, List<E> data, XSSFWorkbook wb, boolean enableFieldsAutoGet, HttpServletResponse response) {
		if (headers == null) {
			headers = new LinkedList<>();
		}
		if (data == null) {
			data = new LinkedList<>();
		}
		XSSFSheet sheet = wb.createSheet(fileName);
		int currentRowNo = 0;
		writeHead(headers, sheet, data, currentRowNo, enableFieldsAutoGet, wb);
		currentRowNo++;
		writeBody(headers, sheet, data, currentRowNo, enableFieldsAutoGet, wb);
		currentRowNo = currentRowNo + data.size();
		writeToPage(sheetName, response, wb);
	}

	public static XSSFCellStyle getDefaultHeadStyle(XSSFWorkbook wb) {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格的背景颜色为淡蓝色
		// cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		// cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		// 设置单元格居中对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		// cellStyle.setWrapText(true);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 12);
		cellStyle.setFont(font);
		return cellStyle;
	}

	public static XSSFCellStyle getDefaultBodyStyle(XSSFWorkbook wb) {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格居中对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		font.setFontName("微软雅黑");
		cellStyle.setFont(font);
		return cellStyle;
	}

	private static <E> void writeHead(List<ExcelExportSetting> header, XSSFSheet sheet, List<E> data, int currentRowNo, boolean enableFieldsAutoGet, XSSFWorkbook wb) {

		XSSFRow headerRow = sheet.createRow(currentRowNo);

		if (!enableFieldsAutoGet) {
			for (int i = 0; i < header.size(); i++) {
				XSSFCell headRow = headerRow.createCell(i);
				String headName = header.get(i).getHeadName();
				String fieldName = header.get(i).getFieldName();
				XSSFCellStyle cellStyle = header.get(i).getHeadCellStyle();
				Integer width = header.get(i).getWidth();
				if (headName != null) {
					headRow.setCellValue(StringUtils.capitalize(headName));
				} else {
					headRow.setCellValue(StringUtils.capitalize(fieldName));
				}
				if (cellStyle != null) {
					headRow.setCellStyle(cellStyle);
				}

				if (width != null) {
					sheet.setColumnWidth(i, width * 256);
				} else {
					sheet.autoSizeColumn(i);
				}
			}
		} else {
			if (data.size() > 0) {
				E o = data.get(0);
				Class<?> cls = o.getClass();
				Field[] declaredFields = cls.getDeclaredFields();
				for (int i = 0; i < declaredFields.length; i++) {
					Field field = declaredFields[i];
					XSSFCell headRow = headerRow.createCell(i);
					String headName = field.getName();
					headRow.setCellValue(StringUtils.capitalize(headName));
					headRow.setCellStyle(getDefaultHeadStyle(wb));
					sheet.setColumnWidth(i, 20 * 256);
				}
			}
		}

	}

	private static <E> void writeBody(List<ExcelExportSetting> header, XSSFSheet sheet, List<E> data, int currentRowNo, boolean enableFieldsAutoGet, XSSFWorkbook wb) {
		XSSFRow contentRow = null;
		String[] fieldNames = null;
		XSSFCellStyle[] bodyCellStyles = null;
		boolean isSetFields = false;
		if (!enableFieldsAutoGet) {
			fieldNames = new String[header.size()];
			bodyCellStyles = new XSSFCellStyle[header.size()];
			for (int i = 0; i < header.size(); i++) {
				String headName = header.get(i).getHeadName();
				String fieldName = header.get(i).getFieldName();
				XSSFCellStyle bodyCellStyle = header.get(i).getBodyCellStyle();
				bodyCellStyles[i] = bodyCellStyle;
				if (fieldName != null) {
					isSetFields = true;
					fieldNames[i] = fieldName;
				} else {
					fieldNames[i] = headName;
				}
			}
		}

		if (enableFieldsAutoGet || !isSetFields) {
			List<String> tmpFieldNames = new LinkedList<>();
			List<XSSFCellStyle> tmpBodyCellStyles = new LinkedList<>();
			if (data.size() > 0) {
				E o = data.get(0);
				Class<?> cls = o.getClass();
				Field[] declaredFields = cls.getDeclaredFields();
				for (int i = 0; i < declaredFields.length; i++) {
					Field field = declaredFields[i];
					tmpFieldNames.add(field.getName());
					tmpBodyCellStyles.add(getDefaultBodyStyle(wb));
				}
			}
			fieldNames = tmpFieldNames.toArray(new String[0]);
			bodyCellStyles = tmpBodyCellStyles.toArray(new XSSFCellStyle[0]);
		}

		try {
			for (int i = 0; i < data.size(); i++) {
				contentRow = sheet.createRow(i + currentRowNo);
				// 获取每一个对象
				E o = data.get(i);
				Class<?> cls = o.getClass();
				for (int j = 0; j < fieldNames.length; j++) {
					String fieldName = StringUtils.capitalize(fieldNames[j]);
					Method getMethod = cls.getMethod("get" + fieldName);
					Object value = getMethod.invoke(o);
					if (value != null) {
						XSSFCell bodyCell = contentRow.createCell(j);
						if (bodyCellStyles != null && bodyCellStyles.length > 0 && bodyCellStyles[j] != null) {
							bodyCell.setCellStyle(bodyCellStyles[j]);
						}
						if (value instanceof String) {
							bodyCell.setCellValue((String) value);
						} else if (value instanceof Boolean) {
							bodyCell.setCellValue((Boolean) value);
						} else if (value instanceof Calendar) {
							bodyCell.setCellValue((Calendar) value);
						} else if (value instanceof Double) {
							bodyCell.setCellValue((Double) value);
						} else if (value instanceof Integer || value instanceof Long || value instanceof Short || value instanceof Float) {
							bodyCell.setCellValue(Double.parseDouble(value.toString()));
						} else if (value instanceof HSSFRichTextString) {
							bodyCell.setCellValue((HSSFRichTextString) value);
						} else {
							bodyCell.setCellValue(value.toString());
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private static void writeToPage(String fileName, HttpServletResponse response, XSSFWorkbook wb) {
		OutputStream os = null;
		try {
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf8") + ".xlsx");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			os = response.getOutputStream();
			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static ExcelExportSetting getDefaultExcelSetting(XSSFWorkbook wb) {
		return new ExcelExportSetting().setHeadCellStyle(getDefaultHeadStyle(wb)).setBodyCellStyle(getDefaultBodyStyle(wb)).setWidth(20);
	}

	private static final SimpleDateFormat sdf_import = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static final SimpleDateFormat sdf_default = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static <T> List<T> importExcel(InputStream inputStream, List<ExcelImportSetting> headers, Class<T> clazz) {
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return importExcel(workbook, headers, clazz, 1, 0);

	}

	public static <T> List<T> importExcel(Workbook workbook, List<ExcelImportSetting> headers, Class<T> clazz, int rowStart, int sheetNum) {
		Sheet sheet = workbook.getSheetAt(sheetNum);

		List<T> list = new LinkedList<>();
		if (headers == null || headers.isEmpty()) {
			Row row = sheet.getRow(0);
			headers = new LinkedList<>();
			ExcelImportSetting excelImportSetting = null;
			for (Cell cell : row) {
				excelImportSetting = new ExcelImportSetting();
				excelImportSetting.setFieldName(cell.getStringCellValue());
				headers.add(excelImportSetting);
			}
		}

		List<String> fieldNames = new ArrayList<>();
		for (ExcelImportSetting setting : headers) {
			fieldNames.add(setting.getFieldName());
		}

		for (int i = rowStart; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			T target = null;
			try {
				target = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int j = 0; j < headers.size(); j++) {
				ExcelImportSetting header = headers.get(j);
				String fieldName = header.getFieldName();
				String fieldFormat = header.getFieldFormat();
				Class[] methodParamTypes = CommonUtils.getMethodParamTypes(target, "set" + StringUtils.capitalize(fieldName));
				String methodType = methodParamTypes[0].getName();
				Cell cell = row.getCell(j);
				String cellValue = getCellValue(cell);
				switch (methodType) {
				case "java.util.Date": {
					if (cellValue != null && !cellValue.trim().equals("")) {
						Date date = null;
						try {
							SimpleDateFormat sdf = null;
							if (fieldFormat == null || fieldFormat.trim().equals("")) {
								sdf = sdf_import;
							} else {
								sdf = new SimpleDateFormat(fieldFormat);
							}
							Date parseDate = sdf.parse(cellValue);
							CommonUtils.setMethod(fieldName, target, parseDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						CommonUtils.setMethod(fieldName, target, date);
					}
					header.setFieldType(Date.class.toString());
					break;
				}
				case "java.lang.Double": {
					if (cellValue != null && !cellValue.trim().equals("")) {
						Double numericCellValue = Double.parseDouble(cellValue);
						CommonUtils.setMethod(fieldName, target, numericCellValue);
					}
					header.setFieldType(Double.class.toString());
					break;
				}

				case "java.lang.Float": {
					if (cellValue != null && !cellValue.trim().equals("")) {
						Float numericCellValue = Float.parseFloat(cellValue);
						CommonUtils.setMethod(fieldName, target, numericCellValue);
					}
					header.setFieldType(Float.class.toString());
					break;
				}

				case "java.lang.Long": {
					if (cellValue != null && !cellValue.trim().equals("")) {
						Long numericCellValue = Long.parseLong(cellValue);
						CommonUtils.setMethod(fieldName, target, numericCellValue);
					}
					header.setFieldType(Long.class.toString());
					break;
				}

				case "java.lang.Integer": {
					if (cellValue != null && !cellValue.trim().equals("")) {
						Integer numericCellValue = Integer.parseInt(cellValue);
						CommonUtils.setMethod(fieldName, target, numericCellValue);
					}
					header.setFieldType(Integer.class.toString());
					break;
				}

				default: {
					if (fieldFormat != null && !fieldFormat.trim().equals("")) {
						try {
							Date parse = sdf_import.parse(cellValue);
							cellValue = sdf_default.format(parse);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					if (cellValue != null && !cellValue.trim().equals("")) {
						CommonUtils.setMethod(fieldName, target, cellValue);
					}
					header.setFieldType(String.class.toString());
					break;
				}

				}

			}
			if (!CommonUtils.checkNullObject(fieldNames, target)) {
				list.add(target);
			}

		}
		return list;

	}

	public static String getCellValue(Cell cell) {
		String result = "";
		if (cell == null) {
			return result;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			result = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			result = null;
			break;
		case Cell.CELL_TYPE_FORMULA:
			Workbook wb = cell.getSheet().getWorkbook();
			CreationHelper crateHelper = wb.getCreationHelper();
			FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
			result = getCellValue(evaluator.evaluateInCell(cell));
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date theDate = cell.getDateCellValue();
				result = sdf_import.format(theDate);
			} else {
				result = NumberToTextConverter.toText(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_STRING:
			result = cell.getRichStringCellValue().getString();
			break;
		default:
			result = null;
		}
		if (result != null) {
			result = result.trim();
		}
		return result;
	}

	public static boolean checkCellType(int cellType) {
		switch (cellType) {
		// 字符串  
		case Cell.CELL_TYPE_STRING:
			return true;
		// 数字  
		case Cell.CELL_TYPE_NUMERIC:
			return true;
		// boolean  
		case Cell.CELL_TYPE_BOOLEAN:
			return true;
		// 方程式  
		case Cell.CELL_TYPE_FORMULA:
			return true;
		case Cell.CELL_TYPE_BLANK:
			return false;
		case Cell.CELL_TYPE_ERROR:
			return false;
		// 空值  
		default:
			return false;
		}
	}

	public static class ExcelImportSetting {
		private static final String DATE_TYPE = "DATE_TYPE";
		private static final String DOUBLE_TYPE = "DOUBLE_TYPE";
		private static final String STRING_TYPE = "STRING_TYPE";
		private String fieldName;
		private String fieldType = STRING_TYPE;
		private String fieldFormat;

		public String getFieldName() {
			return fieldName;
		}

		public ExcelImportSetting setFieldName(String fieldName) {
			this.fieldName = fieldName;
			return this;
		}

		public String getFieldType() {
			return fieldType;
		}

		public ExcelImportSetting setFieldType(String fieldType) {
			this.fieldType = fieldType;
			return this;
		}

		public String getFieldFormat() {
			return fieldFormat;
		}

		public ExcelImportSetting setFieldFormat(String fieldFormat) {
			this.fieldFormat = fieldFormat;
			return this;
		}

	}

	public static class ExcelExportSetting {
		private String headName;
		private String fieldName;
		private Integer width;
		private XSSFCellStyle headCellStyle;
		private XSSFCellStyle bodyCellStyle;

		public String getHeadName() {
			return headName;
		}

		public ExcelExportSetting setHeadName(String headName) {
			this.headName = headName;
			return this;
		}

		public String getFieldName() {
			return fieldName;
		}

		public ExcelExportSetting setFieldName(String fieldName) {
			this.fieldName = fieldName;
			return this;
		}

		public Integer getWidth() {
			return width;
		}

		public ExcelExportSetting setWidth(Integer width) {
			this.width = width;
			return this;
		}

		public XSSFCellStyle getHeadCellStyle() {
			return headCellStyle;
		}

		public ExcelExportSetting setHeadCellStyle(XSSFCellStyle cellStyle) {
			this.headCellStyle = cellStyle;
			return this;
		}

		public XSSFCellStyle getBodyCellStyle() {
			return bodyCellStyle;
		}

		public ExcelExportSetting setBodyCellStyle(XSSFCellStyle bodyCellStyle) {
			this.bodyCellStyle = bodyCellStyle;
			return this;
		}

	}

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		String path = ExcelUtils.class.getResource("").getPath();
		String fileName = "resource.xlsx";
		System.out.println(path);
		InputStream inputStream = new FileInputStream(new File(path + fileName));
		System.out.println(importExcel(inputStream, null, ResourceBean.class));
		//System.out.println(CommonUtils.getMethodParamTypes(new UserInfo(), "setSalary")[0] == Double.class);

		//	System.out.println(CommonUtils.getMethodParamTypes(new UserInfo(), "set" + StringUtils.capitalize("salary"))[0].getName());
		//System.out.println(Date.class.getName());

	}

}
