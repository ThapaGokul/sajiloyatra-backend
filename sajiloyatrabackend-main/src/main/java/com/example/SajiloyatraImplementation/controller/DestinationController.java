package com.example.SajiloyatraImplementation.controller;

import com.example.SajiloyatraImplementation.model.Destination;
import com.example.SajiloyatraImplementation.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend
public class DestinationController {

    @Autowired
    private DestinationRepository destinationRepository;

    // ✅ Fetch all destinations
    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {
        List<Destination> destinations = destinationRepository.findAll();

        if (destinations.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 - no data
        }

        return ResponseEntity.ok(destinations); // 200 - success
    }

    // ✅ (Optional) Add endpoint to add new destinations manually
    @PostMapping("/add")
    public ResponseEntity<?> addDestination(@RequestBody Destination destination) {
        destinationRepository.save(destination);
        return ResponseEntity.ok("Destination added successfully!");
    }
}