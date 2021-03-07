package com.petrov.storage.Rest;

import com.petrov.storage.Persist.Entity.File;
import com.petrov.storage.service.FileSeviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/file")
@RestController
public class FileResource {


    private final FileSeviceImpl fileService;

    @Autowired
    public FileResource(FileSeviceImpl fileService) {
        this.fileService = fileService;
    }

    @GetMapping(path = "/all", produces = "aplication/json")
    public List<File> findAll(){
        List<File> files = fileService.findAll();
        return files;
    }

//    @PostMapping(path = "/upload",consumes = "application/json")
//    public Response createFile(@RequestParam MultipartFile file) {
//        File newFile = fileService.save(file);
//
//        return new Response(newFile.getName(),newFile.getType(),newFile.getSize());
//
//    }
//
    @GetMapping("/{id}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        File file = fileService.findById(id).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename\""+file.getName()+"\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public File findById(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        return fileService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }


    @DeleteMapping(path = "/{id}/id", produces = "application/json")
    public void deleteById(@PathVariable("id") Long id) {
        fileService.deleteById(id);
    }
}
