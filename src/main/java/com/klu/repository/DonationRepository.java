package com.klu.repository;

import com.klu.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonorEmailOrderByCreatedAtDesc(String donorEmail);
    long countByStatusAndRecipientEmailIsNull(String status);
    long countByStatusIn(List<String> statuses);
}
