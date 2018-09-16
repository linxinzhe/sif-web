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

    @GetMapping("/startUpload")
    @ResponseBody
    public String startUpload(@RequestParam("dirname") String dirname) {


        File file = new File(dirname);
        boolean success = file.mkdir();
        if (success) {
            return "Start Upload on " + dirname;
        } else {
            return "Failed to Start Upload on" + dirname;
        }
    }

    @PostMapping("/imgUpload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("dirname") String dirname) {
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(dirname + "/" + Objects.requireNonNull(file.getOriginalFilename()))));
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


    @GetMapping("/endUpload")
    @ResponseBody
    public String endUpload(@RequestParam("dirname") String dirname) {
        //Trigger OpenSFM

        return "Trigger OpenSFM on " + dirname;
    }

}
