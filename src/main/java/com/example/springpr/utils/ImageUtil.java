package com.example.springpr.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.LocalTime;
import java.util.Base64;

import static com.example.springpr.utils.DomainUtil.getCurrentDomain;

public class ImageUtil {
    public static String base64ToImage(String base64String, String extension, Long id){

        String[] base64Split = base64String.split(",");
        base64String = base64Split[base64Split.length - 1];
        String path = "src/main/resources/images/" + LocalTime.now().getNano() + "." + extension;
        byte[] imageBytes = Base64.getMimeDecoder().decode(base64String);

        try {
            // Create a BufferedImage from the byte array
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Save the BufferedImage as an image file

            File outputFile = new File(path);
            ImageIO.write(bufferedImage, extension, outputFile);

            System.out.println("Image saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] splitPath = path.split("/");
        return  getCurrentDomain() + "/" + splitPath[3] + "/" + splitPath[4];
    }



}
