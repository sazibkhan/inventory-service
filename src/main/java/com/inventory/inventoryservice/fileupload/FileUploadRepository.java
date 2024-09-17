package com.inventory.inventoryservice.fileupload;

import com.inventory.inventoryservice.fileupload.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload,Integer> {
    Optional<FileUpload> findByName(String fileName);
}