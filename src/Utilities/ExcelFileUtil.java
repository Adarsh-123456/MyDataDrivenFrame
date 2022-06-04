package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
Workbook wb;
//constructor for reading excel path
public ExcelFileUtil(String excelpath) throws Throwable      //Constructor=initializing object
{
 FileInputStream fi= new FileInputStream(excelpath);
 wb=WorkbookFactory.create(fi);
 }
//counting number of rows
public int rowCount(String SheetName)                         //method=1
{
	return wb.getSheet(SheetName).getLastRowNum();
}
//counting number of cells in 1st row
public int cellCount(String SheetName)                       //method=2
{
	return wb.getSheet(SheetName).getRow(0).getLastCellNum();
}
//get cell data from sheet
public String getCellData(String SheetName,int row,int column)                //method=3
{
	String data="";
	if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata=(int) wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
		data=String.valueOf(celldata);
	}
	else
	{
		data=wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
public void setCellData(String SheetName,int row,int column,String status,String writeexcelpath) throws Throwable              //method=4
{
	//get sheet from WB
	Sheet ws=wb.getSheet(SheetName);
	//get row from sheet
	Row rowNum=ws.getRow(row);
	//create cell in a row
	Cell cell= rowNum.createCell(column);
	//write status in a cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		CellStyle style= wb.createCellStyle();
		Font font= wb.createFont();
		//Colour text
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		CellStyle style= wb.createCellStyle();
		Font font= wb.createFont();
		//Colour text
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Blocked"))
	{
		CellStyle style= wb.createCellStyle();
		Font font= wb.createFont();
		//Colour text
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	FileOutputStream fo= new FileOutputStream(writeexcelpath);
	wb.write(fo);
	
			
}
}
















