package com.petrov.storage.service;

import com.petrov.storage.Persist.Entity.File;
import com.petrov.storage.Persist.Repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FileSeviceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public List<File> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public Optional<File> findById(Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public File save(MultipartFile uploadFile){
        String fileName = uploadFile.getOriginalFilename();
        String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        try{
            File newFile = new File(fileName,currentDate,uploadFile.getContentType(),uploadFile.getSize(),uploadFile.getBytes());
            return fileRepository.save(newFile);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
