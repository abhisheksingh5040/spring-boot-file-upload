package com.te.fileuploaddownload.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="file_stored_db")
public class FileData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileId;
	private String fileName;
	private String fileType;
	private String filepath;
}
