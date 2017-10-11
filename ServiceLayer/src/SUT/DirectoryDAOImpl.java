package SUT;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDAOImpl implements DirectoryDAO {

	@Override
	public List<FileInformation> GetFileInformation() throws IOException {
		String pathString = null;
		List<FileInformation> fileInformationList = new ArrayList<FileInformation>();
		String filesFilter = null;
		PropReader propReader = new PropReader();
		
		pathString = propReader.getProperty("path");
		filesFilter = propReader.getProperty("filesFilter");
		
		Path path = Paths.get(pathString);
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{" + filesFilter + "}")) {
			for (Path file : stream) {
				fileInformationList.add(new FileInformation () {{
					String name = file.getFileName().toString();
					setName(name);
					setSize(file.toFile().length());
					setMimeType(Files.probeContentType(file));
					setExtension(name.substring(name.lastIndexOf('.') + 1));
					setFilePath(file.toString());
				}});
			}				
		}
		
		return fileInformationList;
	}

	@Override
	public List<VehicleInformation> GetVehicleInformation(FileInformation fileInformation) throws IOException {
		String dirPath = new PropReader().getProperty("path");
		String filePath = dirPath + "\\" + fileInformation.getName();
		List<VehicleInformation> vehicleInformationList = new ArrayList<VehicleInformation>();
		Boolean titleLine = true;
		
		Path filePathVar = Paths.get(filePath);
		try (BufferedReader br = Files.newBufferedReader(filePathVar)) {
			String line;
			while ((line = br.readLine()) != null) {
				if (titleLine) {
					titleLine=false;
					continue;
				}
				String[] lineData = line.split(",");
				vehicleInformationList.add(new VehicleInformation() {{
					setRegNo(lineData[0]);
					setColour(lineData[1]);
				}});
			}
		}
		
		return vehicleInformationList;
	}

}
