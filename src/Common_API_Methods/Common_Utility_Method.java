package Common_API_Methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Iterator;


public class Common_Utility_Method 
{
	public static void EvidenceFileCrator (String filename,String RequestBody,
				String ResponseBody ,int StatusCode ) throws IOException 
	{
		File TextFile = new File ("E:\\Evidence\\AK\\"+ filename +".txt");
		System.out.println("New File Is Created:" + TextFile.getName());
		
		FileWriter DataWrite = new FileWriter(TextFile);
		
		DataWrite.write("Request Body is In :" + RequestBody + "\n\n");
		DataWrite.write("Response Body is In :" + ResponseBody + "\n\n") ;
		DataWrite.write("StatusCode is :" + StatusCode) ;
		
		DataWrite.close();
		System.out.println("Data Is Written in New File" + TextFile.getName());
		}

	public static ArrayList<String> ReadDataExcel(String sheetname, String TestCaseName) throws IOException 
	{

		ArrayList<String> ArrayData = new ArrayList<String>();
		// Create the object of file input stream to locate the excel file
		FileInputStream Fis = new FileInputStream("E:\\Selenium\\Framework.xlsx");
		// Step 2: Open the excel file by creating the object XSSFWorkbook
		XSSFWorkbook WorkBook = new XSSFWorkbook(Fis);
		// Step no 3: Open the desired Sheet
		int countofsheet = WorkBook.getNumberOfSheets();
		for (int i = 0; i < countofsheet; i++)
		{
			String SheetName = WorkBook.getSheetName(i);
			// Access the desire sheet
			if (SheetName.equalsIgnoreCase(sheetname))
			{
				// Use XSSFSheet to save the sheet into a variable
				XSSFSheet Sheet = WorkBook.getSheetAt(i);
				// create iterators iterated thought row and find out in which column the test
				// case name found
				Iterator<Row> Rows = Sheet.iterator();
				Row FirstRow = Rows.next();
				// create the Iterator to Iterated though the cells of 1st Row to find out which
				// cell contains TestCase name
				Iterator<Cell> CellsOfFirstRow = FirstRow.cellIterator();
				int k = 0;
				int TC_column = 0;
				while (CellsOfFirstRow.hasNext())
				{
					Cell CellValue = CellsOfFirstRow.next();
					if (CellValue.getStringCellValue().equalsIgnoreCase("TestCaseName")) 
					{
						TC_column = k;
						// System.out.println("expected column for test case name:" +k);
						break;
					}
					k++;
				}
				
				// verify the row where the desired test case is found and fetch the entire row
				while (Rows.hasNext())
				{
					Row Datarow = Rows.next();
					String TCName = Datarow.getCell(TC_column).getStringCellValue();
					//Datarow.getCell(TC_column).getNumericCellValue()
					if (TCName.equalsIgnoreCase(TestCaseName))
					{
						Iterator<Cell> CellValues = Datarow.cellIterator();
						while (CellValues.hasNext())
						{
							String Data = "";
							Cell CurrentCell = CellValues.next();
							try
							{
								String StringData = CurrentCell.getStringCellValue();
								Data = StringData;
							}
							catch(IllegalStateException e)
							{
							    double doubledata = CurrentCell.getNumericCellValue();
							    Data = Double.toString(doubledata);
							}
							
							ArrayData.add(Data);
						}
						break;
					}
				}

			}
		}
		return ArrayData;
	}
}