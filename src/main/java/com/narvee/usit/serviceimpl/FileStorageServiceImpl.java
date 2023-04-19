package com.narvee.usit.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.exception.FileStorageException;
import com.narvee.usit.exception.FileStorageProperties;
import com.narvee.usit.service.IfileStorageService;

@Service
public class FileStorageServiceImpl implements IfileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String storeLocation = "D:/stores2";

		File file2 = new File(storeLocation);
		if (!file2.exists()) {
			file2.mkdir();
		}

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			InputStream inR = file.getInputStream();

			OutputStream osResume = new FileOutputStream(file2.getAbsolutePath() + "\\" + fileName);
			IOUtils.copy(inR, osResume);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public String storemultiplefiles(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String storeLocation = "D:/stores2";
		InputStream inR = null;
		OutputStream osResume = null;

		File file2 = new File(storeLocation);
		if (!file2.exists()) {
			file2.mkdir();
		}

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			inR = file.getInputStream();

			osResume = new FileOutputStream(file2.getAbsolutePath() + "\\" + fileName);
			IOUtils.copy(inR, osResume);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}

		finally {
			try {
				inR.close();
				osResume.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
