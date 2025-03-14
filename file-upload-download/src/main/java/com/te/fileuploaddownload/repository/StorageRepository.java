package com.te.fileuploaddownload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.fileuploaddownload.entity.ImageData;

@Repository
public interface StorageRepository extends JpaRepository<ImageData,Long> {

	Optional<ImageData>  findByImageName(String imageName);
}
