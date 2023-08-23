package com.example.springpr.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/")
@Tag(name = "Images")
public class ImagesController {

    private static final String IMAGES_DIRECTORY = "src/main/resources/images/";

    @GetMapping("images/{imageName:.+}")
    @Operation(summary = "Get image" , description = "Return image by image name.")
    public ResponseEntity<byte[]> showImage(@PathVariable String imageName) throws IOException {
        File imageFile = new File(IMAGES_DIRECTORY, imageName);

        if (imageFile.exists()) {
            try (InputStream in = new FileInputStream(imageFile)) {
                byte[] imageBytes = in.readAllBytes();
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Adjust content type based on your image type
                        .body(imageBytes);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
