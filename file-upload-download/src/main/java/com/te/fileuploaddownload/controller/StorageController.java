package com.te.fileuploaddownload.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.te.fileuploaddownload.service.StorageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StorageController {

	private final StorageService storageService;
	
	//To store image to the db
	@PostMapping("/")
	public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(storageService.uploadImage(multipartFile));
	}
	
	//To download image from the db
	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/jpg"))
			//	.contentType(MediaType.IMAGE_JPEG)
			//	.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"")
				.body(storageService.downloadImage(fileName));
	}
	
	//To store image to the file path
	@PostMapping("/filename")
	public ResponseEntity<String> uploadImageToFile(@RequestParam("image") MultipartFile multipartFile) throws IOException{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(storageService.uploadImageToFileSystem(multipartFile));
	}
	
	//To download image from the file path
	@GetMapping("/file/{fileName}")
	public ResponseEntity<?> downloadImageToFile(@PathVariable String fileName) throws IOException{
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/jpg"))
			//	.contentType(MediaType.IMAGE_JPEG)
			//	.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"")
				.body(storageService.downloadImageToFileSystem(fileName));
	}
	
	
}
