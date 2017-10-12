package SUT.Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import SUT.*;

/**
 * Unit tests for the extraction logic in DirectoryServiceLayer
 * The DAO interface is used to mock interactions with the file system
 */
@RunWith(MockitoJUnitRunner.class)
public class DirectoryServiceLayerTests {

	@Mock
	private DirectoryDAO mockDirectoryDAO;
	
	@InjectMocks
	private DirectoryServiceLayer directoryServiceLayer = new DirectoryServiceLayer(mockDirectoryDAO);
	
	private List<FileInformation> dummyList1 = new ArrayList<FileInformation>() {{
		add(new FileInformation() {{
			setName("file1.csv");
			setMimeType("application/vnd.ms-excel");
			setExtension("csv");
			setSize(40);
		}});
		add(new FileInformation() {{
			setName("file2.txt");
			setMimeType("text/plain");
			setExtension("txt");
			setSize(20);
		}});
	}};
	
	private List<VehicleInformation> dummyList2 = new ArrayList<VehicleInformation>() {{
		add(new VehicleInformation() {{
			setRegNo("AB12CDE");
			setColour("White");
		}});
		add(new VehicleInformation() {{
			setRegNo("FG34HIJ");
			setColour("Black");
		}});
	}};
	
	@Test
	public void getFilesInDirectory_WhenDirectoryIsEmpty() throws IOException {
		org.mockito.Mockito.when(mockDirectoryDAO.GetFileInformation())
			.thenReturn(new ArrayList<FileInformation>());
		List<FileInformation> list = directoryServiceLayer.GetFilesInDirectory();
		Assert.assertTrue(list.isEmpty());
		org.mockito.Mockito.verify(mockDirectoryDAO, org.mockito.Mockito.times(1)).GetFileInformation();
	}

	@Test
	public void getFilesInDirectory_WhenDirectoryHasFiles() throws IOException {
		org.mockito.Mockito.when(mockDirectoryDAO.GetFileInformation())
			.thenReturn(dummyList1);
		List<FileInformation> list = directoryServiceLayer.GetFilesInDirectory();
		Assert.assertEquals(2, list.size());
		org.mockito.Mockito.verify(mockDirectoryDAO, org.mockito.Mockito.times(1)).GetFileInformation();
		
	}
	
	@Test(expected = FileNotFoundException.class)
	public void getFilesInDirectory_FileNotFound() throws IOException {
		org.mockito.Mockito.doThrow(new FileNotFoundException("no directory found"))
			.when(mockDirectoryDAO).GetFileInformation();
		directoryServiceLayer.GetFilesInDirectory();
	}
	
	@Test
	public void getVehicleInformation_WhenFileIsBlank() throws IOException {
		FileInformation fi = new FileInformation();
		org.mockito.Mockito.when(mockDirectoryDAO.GetVehicleInformation(fi))
			.thenReturn(new ArrayList<VehicleInformation>());
		List<VehicleInformation> list = directoryServiceLayer.GetVehicleInformation(fi);
		Assert.assertTrue(list.isEmpty());
		org.mockito.Mockito.verify(mockDirectoryDAO, org.mockito.Mockito.times(1)).GetVehicleInformation(fi);
	}
	
	@Test
	public void getVehicleInformation_WhenFileHasData() throws IOException {
		FileInformation fi = new FileInformation();
		org.mockito.Mockito.when(mockDirectoryDAO.GetVehicleInformation(fi))
			.thenReturn(dummyList2);
		List<VehicleInformation> list = directoryServiceLayer.GetVehicleInformation(fi);
		Assert.assertEquals(2, list.size());
		org.mockito.Mockito.verify(mockDirectoryDAO, org.mockito.Mockito.times(1)).GetVehicleInformation(fi);
	}
	
	@Test(expected = IOException.class)
	public void getVehicleInformatio_IOException() throws IOException {
		FileInformation fi = new FileInformation();
		org.mockito.Mockito.doThrow(new IOException("no directory found"))
			.when(mockDirectoryDAO).GetVehicleInformation(fi);
		directoryServiceLayer.GetVehicleInformation(fi);
	}
}
