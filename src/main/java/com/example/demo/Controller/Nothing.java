package com.example.demo.Controller;

import com.example.demo.Model.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Nothing {

    @GetMapping
    public ResponseEntity<?> hello(){
        return ResponseEntity.status(200).body(new ResponseAPI("Success", "Startup Page", null));
    }

    @GetMapping("/error")
    public ResponseEntity<?> error(){
        return ResponseEntity.status(500).body(new ResponseAPI("Error", "Error Page", null));
    }
}
