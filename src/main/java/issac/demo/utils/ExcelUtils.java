package issac.demo.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static <E> void exportExcel(String fileName, String[] header, List<E> data, HttpServletResponse response) {
		List<ExcelSetting> headerList = new LinkedList<>();
		XSSFWorkbook wb = new XSSFWorkbook();
		for (int i = 0; i < header.length; i++) {
			String headName = header[i];
			ExcelSetting excelSetting = getDefaultExcelSetting(wb);
			headerList.add(excelSetting.setHeadName(headName));
		}
		exportExcel(fileName, fileName, headerList, data, wb, false, response);
	}

	public static <E> void exportExcel(String fileName, String sheetName, List<ExcelSetting> header, List<E> data, XSSFWorkbook wb, boolean enableFieldsAutoGet, HttpServletResponse response) {
		if (header == null) {
			header = new LinkedList<>();
		}
		if (data == null) {
			data = new LinkedList<>();
		}
		XSSFSheet sheet = wb.createSheet(fileName);
		int currentRowNo = 0;
		writeHead(header, sheet, data, currentRowNo, enableFieldsAutoGet);
		currentRowNo++;
		writeBody(header, sheet, data, currentRowNo, enableFieldsAutoGet);
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

	private static <E> void writeHead(List<ExcelSetting> header, XSSFSheet sheet, List<E> data, int currentRowNo, boolean enableFieldsAutoGet) {

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
					sheet.autoSizeColumn(i);
				}
			}
		}

	}

	private static <E> void writeBody(List<ExcelSetting> header, XSSFSheet sheet, List<E> data, int currentRowNo, boolean enableFieldsAutoGet) {
		XSSFRow contentRow = null;
		String[] fieldNames = null;
		XSSFCellStyle[] bodyCellStyles = null;

		if (!enableFieldsAutoGet) {
			fieldNames = new String[header.size()];
			bodyCellStyles = new XSSFCellStyle[header.size()];
			for (int i = 0; i < header.size(); i++) {
				String headName = header.get(i).getHeadName();
				String fieldName = header.get(i).getFieldName();
				XSSFCellStyle bodyCellStyle = header.get(i).getBodyCellStyle();
				bodyCellStyles[i] = bodyCellStyle;
				if (fieldName != null) {
					fieldNames[i] = fieldName;
				} else {
					fieldNames[i] = headName;
				}
			}
		} else {
			List<String> tmpFieldNames = new LinkedList<>();
			if (data.size() > 0) {
				E o = data.get(0);
				Class<?> cls = o.getClass();
				Field[] declaredFields = cls.getDeclaredFields();
				for (int i = 0; i < declaredFields.length; i++) {
					Field field = declaredFields[i];
					tmpFieldNames.add(field.getName());
				}
			}
			fieldNames = tmpFieldNames.toArray(new String[0]);
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
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf8") + ".xls");
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

	public static ExcelSetting getDefaultExcelSetting(XSSFWorkbook wb) {
		return new ExcelSetting().setHeadCellStyle(getDefaultHeadStyle(wb)).setBodyCellStyle(getDefaultBodyStyle(wb)).setWidth(20);
	}
	public static class ExcelSetting {
		private String headName;
		private String fieldName;
		private Integer width;
		private XSSFCellStyle headCellStyle;
		private XSSFCellStyle bodyCellStyle;

		public String getHeadName() {
			return headName;
		}

		public ExcelSetting setHeadName(String headName) {
			this.headName = headName;
			return this;
		}

		public String getFieldName() {
			return fieldName;
		}

		public ExcelSetting setFieldName(String fieldName) {
			this.fieldName = fieldName;
			return this;
		}

		public Integer getWidth() {
			return width;
		}

		public ExcelSetting setWidth(Integer width) {
			this.width = width;
			return this;
		}

		public XSSFCellStyle getHeadCellStyle() {
			return headCellStyle;
		}

		public ExcelSetting setHeadCellStyle(XSSFCellStyle cellStyle) {
			this.headCellStyle = cellStyle;
			return this;
		}

		public XSSFCellStyle getBodyCellStyle() {
			return bodyCellStyle;
		}

		public ExcelSetting setBodyCellStyle(XSSFCellStyle bodyCellStyle) {
			this.bodyCellStyle = bodyCellStyle;
			return this;
		}

	}

}
