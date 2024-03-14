package com.benfat.pilpose.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class PilposeUtils {

	private PilposeUtils() {
		throw new UnsupportedOperationException("C'est une classe Utils ne peut pas etre instancié");
	}

	public static XSSFRow getXRow(XSSFSheet sheet, int dataRowIndex) {
		XSSFRow row;
		row = sheet.getRow(dataRowIndex);
		if (row == null) {
			row = sheet.createRow(dataRowIndex);
		}
		return row;
	}

	public static Cell getXCell(XSSFRow row, int cellId) {
		Cell cell;
		cell = row.getCell(cellId);
		if (cell == null) {
			cell = row.createCell(cellId);
		}
		return cell;
	}

}