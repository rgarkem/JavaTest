package SUT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class DirectoryServiceLayer implements Serializable {
	private DirectoryDAO directoryDAO;
	
	public DirectoryServiceLayer() {
		super();
	}
	
	public DirectoryServiceLayer(DirectoryDAO directoryDAO) {
		super();
		this.directoryDAO = directoryDAO;
	}
	
	public List<FileInformation> GetFilesInDirectory() throws IOException {
		return this.directoryDAO.GetFileInformation();
	}
	
	public List<VehicleInformation> GetVehicleInformation(FileInformation fileInfo) throws FileNotFoundException, IOException {
		return this.directoryDAO.GetVehicleInformation(fileInfo);
	}

}
