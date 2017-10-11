package SUT;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Runner {

	public static void main(String[] args) throws URISyntaxException, IOException {
		DirectoryServiceLayer dsl = new DirectoryServiceLayer(new DirectoryDAOImpl());
		List<FileInformation> dirInfos = dsl.GetFilesInDirectory();
		for (FileInformation info : dirInfos) {
			System.out.println(String.format("File Info :: Name:%s \t Size:%d \t Mime:%s \t Ext:%s \t FullPath:%s"
					, info.getName(), info.getSize(), info.getMimeType(), info.getExtension(), info.getFilePath()));
		}
		
		List<VehicleInformation> vehicleInfos = dsl.GetVehicleInformation(dirInfos.get(0));
		for (VehicleInformation info : vehicleInfos) {
			System.out.println(String.format("Vehicle Info :: Reg:%s \t Colour:%s ",
					info.getRegNo(), info.getColour()));
		}
	}

}
