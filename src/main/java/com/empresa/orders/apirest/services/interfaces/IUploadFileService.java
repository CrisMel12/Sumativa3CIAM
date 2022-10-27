package com.empresa.orders.apirest.services.interfaces;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	String copyFile(MultipartFile file) throws IOException;
	
	boolean deleteFile(String fileName);
	  
	public Path getPath(String fileName);

}
