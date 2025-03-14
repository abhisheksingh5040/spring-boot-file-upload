package com.te.fileuploaddownload.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name="image_stored_db")
public class ImageData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageId;
	private String imageName;
	private String imageType;
	@Lob
	@Column(name="image_date")
	private byte[] imageData;

}
