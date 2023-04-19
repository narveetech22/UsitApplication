package com.narvee.usit.service;

import org.springframework.web.multipart.MultipartFile;

public interface IfileStorageService {

	public String storeFile(MultipartFile file);

	public String storemultiplefiles(MultipartFile file);

}
