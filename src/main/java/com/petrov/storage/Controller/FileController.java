package com.petrov.storage.Controller;

import com.petrov.storage.Persist.Entity.File;
import com.petrov.storage.Persist.Repo.FileRepository;
import com.petrov.storage.Persist.Repo.FileSpecification;
import com.petrov.storage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    private final FileService fileService;
    private final FileRepository fileRepository;

    @Autowired
    public FileController(FileService fileService, FileRepository fileRepository) {
        this.fileService = fileService;
        this.fileRepository = fileRepository;
    }

    @GetMapping("/file")
    public String get(Model model,
                      @RequestParam(value = "name", required = false) String name,
                      @RequestParam(value = "up", required = false) String up,
                      @RequestParam(value = "low", required = false) String low) {

        Specification<File> spec = FileSpecification.trueLiteral();

        if(name != null && !name.isEmpty()){
            spec = spec.and(FileSpecification.nameLike(name));
        }
        if (up != null) {
            spec = spec.and(FileSpecification.timeLesserOrEqualsThan(up));
        }
        if (low != null) {
            spec = spec.and(FileSpecification.timeGreaterOrEqualsThan(low));
        }

        model.addAttribute("files", fileRepository.findAll(spec));
        return "products";
    }

    @PostMapping("/file/create")
    public String upload(@RequestParam("file") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            fileService.save(file);
        }
        return "redirect:/file";
    }

    @GetMapping("/file/{id}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id){
        File file = fileService.findById(id).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename\""+file.getName()+"\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @DeleteMapping("/file/{id}/delete")
    public String deleteFile(Model model, @PathVariable("id") Long id) {
        fileService.deleteById(id);
        return "redirect:/file";
    }
}
