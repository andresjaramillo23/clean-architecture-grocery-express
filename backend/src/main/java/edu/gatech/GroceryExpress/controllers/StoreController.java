package edu.gatech.GroceryExpress.controllers;

import edu.gatech.GroceryExpress.services.DeliveryService;
import edu.gatech.GroceryExpress.services.ProcessFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;

@Controller
public class StoreController {

    private ProcessFileService processFileService;

    public StoreController(ProcessFileService processFileService) {
        this.processFileService = processFileService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testing() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().toString();
        } catch (Exception e) {
            System.out.println("Could not get ip. Cause: " + e.getMessage());
        }
        return ResponseEntity.ok(!ip.isEmpty() ? "IP address:" + ip : "test works");
    }

    @PostMapping("/uploadTest")
    public ResponseEntity<?> processFile(@RequestParam("file") MultipartFile file) {
        // TODO: process file then call delivery service
        try {
            String responseBody = processFileService.processFile(file);
            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            System.out.println("Error while tyring to process file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 Bad Request. Could not process file.");
        }
    }
}
