package com.te.fileuploaddownload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.fileuploaddownload.entity.FileData;

@Repository
public interface FileDataRepository extends JpaRepository<FileData,Long> {

	Optional<FileData> findByFileName(String fileName);

}
