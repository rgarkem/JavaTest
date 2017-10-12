package stepDefinitions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import SUT.*;

public class FileUtil {
	
	public static List<VehicleInformation> readFromCsv(FileInformation fileInfo) throws IOException {
		
		List<VehicleInformation> list = new ArrayList<VehicleInformation>();
		
		Path path = Paths.get(fileInfo.getFilePath());
		try (BufferedReader br = Files.newBufferedReader(path)) {
			String line;
			boolean titleLine = true;
			while ((line = br.readLine()) != null) {
				if (titleLine) {
					titleLine = false;
					continue;
				}
				String[] lineData = line.split(",");
				list.add(new VehicleInformation() {{
					setRegNo(lineData[0]);
					setColour(lineData[1]);
				}});
			}
		}
		
		return list;
	}
	
	public static List<VehicleInformation> readFromExcel(FileInformation fileInfo) throws FileNotFoundException, IOException {
		List<VehicleInformation> list = new ArrayList<VehicleInformation>();
		
		try (FileInputStream fis = new FileInputStream(new File(fileInfo.getFilePath()))) {
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			boolean titleRow = true;
			
			//Iterate through each rows one by one
	        Iterator<Row> rowIterator = sheet.iterator();
	        while (rowIterator.hasNext())
	        {
	            Row row = rowIterator.next();
	            if (row == null ) {
	            	continue;	            	
	            } else if (titleRow) {
	            	titleRow = false;
	            	continue;
	            }
	            
	            //For each row, iterate through all the columns
	            Iterator<Cell> cellIterator = row.cellIterator();
	            boolean foundVehicle = false;
                
	            while (cellIterator.hasNext() && !foundVehicle) 
	            {
	                Cell cell = cellIterator.next();
	                //Check the cell type and format accordingly
	                switch (cell.getCellType()) 
	                {
	                    case Cell.CELL_TYPE_BLANK:
	                        break;
	                    case Cell.CELL_TYPE_STRING:
	                    	VehicleInformation info = new VehicleInformation();
	    	            	info.setRegNo(cell.getStringCellValue());
	    	            	info.setColour(cellIterator.next().getStringCellValue());
	    	            	list.add(info);
	    	            	foundVehicle = true;
	                    	break;
	                }	                
	            }	            
	        }
		}
		
		return list;
		
	}

}
