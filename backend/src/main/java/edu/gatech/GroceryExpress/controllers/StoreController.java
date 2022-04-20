package edu.gatech.GroceryExpress.controllers;

import edu.gatech.GroceryExpress.services.DeliveryService;
import edu.gatech.GroceryExpress.services.ProcessFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StoreController {

    private ProcessFileService processFileService;

    public StoreController(ProcessFileService processFileService) {
        this.processFileService = processFileService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testing() {
        return ResponseEntity.ok("test works");
    }

    @PostMapping("/uploadTest")
    public ResponseEntity<?> processFile(@RequestParam("file") MultipartFile file) {
        // TODO: process file then call delivery service
        processFileService.processFile(file);
        return ResponseEntity.ok("return okay only if files process correctly. Otherwise, return internal server error.");
    }
}
