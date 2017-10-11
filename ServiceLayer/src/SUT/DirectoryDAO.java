package SUT;

import java.io.IOException;
import java.util.List;

public interface DirectoryDAO {
	public List<FileInformation> GetFileInformation() throws IOException;
	
	public List<VehicleInformation> GetVehicleInformation(FileInformation fileInformation) throws IOException;
	
}
