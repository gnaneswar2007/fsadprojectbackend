package com.klu.service;

import com.klu.dto.DonationRequest;
import com.klu.dto.DonationStatsResponse;
import com.klu.model.Donation;
import com.klu.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public DonationStatsResponse getStats() {
        long totalDonations = donationRepository.count();
        long activeListings = donationRepository.countByStatusAndRecipientEmailIsNull("available");
        long recipientAccepted = donationRepository.countByStatusIn(List.of("claimed", "picked_up"));
        return new DonationStatsResponse(totalDonations, activeListings, recipientAccepted);
    }

    public List<Donation> getMyDonations(String donorEmail) {
        return donationRepository.findByDonorEmailOrderByCreatedAtDesc(donorEmail);
    }

    @Transactional
    public Donation createDonation(DonationRequest request) {
        Donation donation = new Donation();
        donation.setDonorEmail(request.getDonorEmail());
        donation.setFoodName(request.getFoodName());
        donation.setCategory(request.getCategory());
        donation.setQuantity(request.getQuantity());
        donation.setPickupLocation(request.getPickupLocation());
        donation.setPhoneNumber(request.getPhoneNumber());
        donation.setDescription(request.getDescription());
        donation.setExpiryDate(request.getExpiryDate());
        donation.setStatus("available");
        donation.setCreatedAt(LocalDateTime.now());
        return donationRepository.save(donation);
    }

    @Transactional
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}