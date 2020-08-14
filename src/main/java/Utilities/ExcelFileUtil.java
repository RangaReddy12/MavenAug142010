package Utilities;
import java.io.FileInputStream;
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
public ExcelFileUtil(String excelfile)throws Throwable
{
	FileInputStream fi=new FileInputStream(excelfile);
	wb=WorkbookFactory.create(fi);
}
//count no of rows
public int rowCount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
}
//count no of cells in row
public int colCount(String sheetname)
{
return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
//get data from cell
public String getCellData(String sheetname,int row,int column)
{
	String data=null;
if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
{
int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
data=String.valueOf(celldata);
}
else
{
data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
}
return data;
}
//set status into cell
public void setCellData(String sheetname,int row,int column,String status,
		String writeexcel)throws Throwable
{
	//get sheet from wb
	Sheet ws=wb.getSheet(sheetname);
	//get row from ws
	Row rownum=ws.getRow(row);
	//create cell in a row
	Cell cell=rownum.createCell(column);
	//set status into cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		//create style 
		CellStyle style=wb.createCellStyle();
		//create font 
		Font font =wb.createFont();
		//set colour
		font.setColor(IndexedColors.GREEN.getIndex());
		//bold text
		font.setBold(true);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(column).setCellStyle(style);
		
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		//create style 
				CellStyle style=wb.createCellStyle();
				//create font 
				Font font =wb.createFont();
				//set colour
				font.setColor(IndexedColors.RED.getIndex());
				//bold text
				font.setBold(true);
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Not executed"))
	{
		//create style 
				CellStyle style=wb.createCellStyle();
				//create font 
				Font font =wb.createFont();
				//set colour
				font.setColor(IndexedColors.BLUE.getIndex());
				//bold text
				font.setBold(true);
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);
	}
	FileOutputStream fo= new FileOutputStream(writeexcel);
	wb.write(fo);
	fo.close();
}
}






