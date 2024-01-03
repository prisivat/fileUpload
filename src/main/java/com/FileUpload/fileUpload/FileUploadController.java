package com.FileUpload.fileUpload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    private static String UPLOADED_FOLDER = "C:\\priya\\MCA\\upload/";
    @GetMapping("/fileUploads")
    public String showLoginForm() {
        return "fileUpload";
    }

    @PostMapping("/fileUpload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                return "success"; // Redirect to success page
            } catch (Exception e) {
                model.addAttribute("error", "Error uploading file: " + e.getMessage());
                return "upload-failed"; // Redirect to error page
            }
        } else {
            model.addAttribute("error", "Uploaded file is empty");
            return "upload-failed"; // Redirect to error page
        }
    }
}
