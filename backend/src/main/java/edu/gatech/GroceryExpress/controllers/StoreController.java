package edu.gatech.GroceryExpress.controllers;

import edu.gatech.GroceryExpress.services.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreController {

    private DeliveryService deliveryService;

    public StoreController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/processFile")
    public ResponseEntity<?> processFile() {
        // TODO: process file then call delivery service
        return ResponseEntity.ok("return okay only if files process correctly. Otherwise, return internal server error.");
    }
}
