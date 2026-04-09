package com.example.blogpostmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    private final String uploadDir = "uploads/";

    public String uploadFile(MultipartFile file) throws Exception {

        if (file == null || file.isEmpty()) return null;

        // ✅ FIXED PATH (project root)
        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs(); // ✅ create folder
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        File destination = new File(uploadDir + fileName);

        file.transferTo(destination);

        return fileName;
    }
}
