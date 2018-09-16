package io.sif.sifweb.controller;

import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

@RestController
@RequestMapping("/map")
public class MapController {

    @PostMapping("/imgUpload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(Objects.requireNonNull(file.getOriginalFilename()))));
                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                return file.getName() + " Upload Failed:" + e.getMessage();
            }

            return file.getName() + " Upload Success";

        } else {
            return "Upload Failed: Empty File";
        }
    }

}
