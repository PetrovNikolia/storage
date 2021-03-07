package com.petrov.storage.service;

import com.petrov.storage.Persist.Entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {

    List<File> findAll();

    Optional<File> findById(Long id);

    void deleteById(Long id);

    File save(MultipartFile file) throws IOException;
}
