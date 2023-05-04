package com.te.fileuploaddownload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.te.fileuploaddownload.entity.FileData;
import com.te.fileuploaddownload.entity.ImageData;
import com.te.fileuploaddownload.repository.FileDataRepository;
import com.te.fileuploaddownload.repository.StorageRepository;
import com.te.fileuploaddownload.utils.ImageUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StorageService {

	private final StorageRepository storageRepository;
	private final FileDataRepository fileDataRepository;
	private String FILE_PATH = "E:\\file-upload\\";

	public String uploadImage(MultipartFile multipartFile) throws IOException {
		ImageData imageData = storageRepository.save(ImageData.builder().imageName(multipartFile.getOriginalFilename())
				.imageType(multipartFile.getContentType()).imageData(ImageUtils.compressImage(multipartFile.getBytes()))
				.build());
		if (imageData != null) {
			return "File uploaded successfully :" + multipartFile.getOriginalFilename();
		} else {
			return "File not uploaded!!!!";
		}
	}

	public byte[] downloadImage(String fileName) {
		Optional<ImageData> imageData = storageRepository.findByImageName(fileName);
		if (imageData.isPresent()) {
			byte[] images = ImageUtils.decomposeImage(imageData.get().getImageData());
			return images;
		}
		return null;
	}

	public String uploadImageToFileSystem(MultipartFile multipartFile) throws IOException {
		FileData fileData = fileDataRepository.save(FileData.builder().fileName(multipartFile.getOriginalFilename())
				.fileType(multipartFile.getContentType()).filepath(FILE_PATH + multipartFile.getOriginalFilename())
				.build());
		String filePath = FILE_PATH  + multipartFile.getOriginalFilename();
		multipartFile.transferTo(new File(filePath));

		if (fileData != null) {
			return "File uploaded successfully :" + FILE_PATH + multipartFile.getOriginalFilename();
		} else {
			return "File not uploaded!!!!";
		}
	}
	
	public byte[] downloadImageToFileSystem(String fileName) throws IOException {
		Optional<FileData> imageData = fileDataRepository.findByFileName(fileName);

		String filepath = imageData.get().getFilepath();
		byte[] images = Files.readAllBytes(new File(filepath).toPath());
		return images;
	}

}
