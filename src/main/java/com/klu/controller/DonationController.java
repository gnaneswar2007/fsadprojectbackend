package com.klu.controller;

import com.klu.dto.DonationRequest;
import com.klu.dto.DonationStatsResponse;
import com.klu.model.Donation;
import com.klu.service.DonationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/stats")
    public ResponseEntity<DonationStatsResponse> stats() {
        return ResponseEntity.ok(donationService.getStats());
    }

    @GetMapping
    public ResponseEntity<List<Donation>> myDonations(@RequestParam String donorEmail) {
        return ResponseEntity.ok(donationService.getMyDonations(donorEmail));
    }

    @PostMapping
    public ResponseEntity<Donation> create(@Valid @RequestBody DonationRequest request) {
        return ResponseEntity.ok(donationService.createDonation(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return ResponseEntity.noContent().build();
    }
}