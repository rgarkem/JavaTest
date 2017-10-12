package SUT;

import java.io.IOException;
import java.util.List;

/**
 * This is the interface for the DAO to access directories, describing the supported methods
 */
public interface DirectoryDAO {
	/**
	 * This will return all the the file information from supported files
	 * @return List<FileInformation> 
	 * @throws IOException
	 */
	public List<FileInformation> GetFileInformation() throws IOException;
	
	/**
	 * This will extract vehicle information from supported files
	 * @param fileInformation
	 * @return List<VehicleInformation> 
	 * @throws IOException
	 */
	public List<VehicleInformation> GetVehicleInformation(FileInformation fileInformation) throws IOException;
	
}
