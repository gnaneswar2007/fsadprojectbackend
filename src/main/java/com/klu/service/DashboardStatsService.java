package com.klu.service;

import com.klu.model.DashboardStats;
import com.klu.repository.DashboardStatsRepository;
import com.klu.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardStatsService {

    private final DashboardStatsRepository dashboardStatsRepository;
    private final DonationRepository donationRepository;

    public DashboardStatsService(DashboardStatsRepository dashboardStatsRepository, DonationRepository donationRepository) {
        this.dashboardStatsRepository = dashboardStatsRepository;
        this.donationRepository = donationRepository;
    }

    @Transactional
    public DashboardStats getStats() {
        // Clear existing stats
        dashboardStatsRepository.deleteAll();
        
        // Calculate current stats
        long totalDonations = donationRepository.count();
        long activeListings = donationRepository.countByStatusAndRecipientEmailIsNull("available");
        long recipientAccepted = donationRepository.countByStatusIn(List.of("claimed", "picked_up"));
        
        // Create and save new stats
        DashboardStats stats = new DashboardStats();
        stats.setTotalDonations(totalDonations);
        stats.setActiveListings(activeListings);
        stats.setRecipientAccepted(recipientAccepted);
        stats.setUpdatedAt(LocalDateTime.now());
        
        DashboardStats savedStats = dashboardStatsRepository.save(stats);
        System.out.println("DashboardStatsService: Stats saved with ID: " + savedStats.getId());
        return savedStats;
    }

    public DashboardStats getCurrentStats() {
        List<DashboardStats> stats = dashboardStatsRepository.findAll();
        return stats.isEmpty() ? getStats() : stats.get(0);
    }
}
