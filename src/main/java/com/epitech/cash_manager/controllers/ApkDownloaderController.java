package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class ApkDownloaderController {

    @GetMapping(value="/client.apk")
    public ResponseEntity<Object> downloadApk() throws IOException {
        String filename = "/entrypoint/app.apk";
        File apk = new File(filename);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(apk));
        } catch (Exception e) {
            throw new ResourceNotFoundException("client.apk", "file", "app.apk");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", apk.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(apk.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
