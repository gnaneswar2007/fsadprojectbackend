package com.klu.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dashboard_stats")
public class DashboardStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long totalDonations;

    @Column(nullable = false)
    private long activeListings;

    @Column(nullable = false)
    private long recipientAccepted;

    private LocalDateTime updatedAt;

    public DashboardStats() {}

    public DashboardStats(Long id, long totalDonations, long activeListings, long recipientAccepted, LocalDateTime updatedAt) {
        this.id = id;
        this.totalDonations = totalDonations;
        this.activeListings = activeListings;
        this.recipientAccepted = recipientAccepted;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public long getTotalDonations() { return totalDonations; }
    public void setTotalDonations(long totalDonations) { this.totalDonations = totalDonations; }
    public long getActiveListings() { return activeListings; }
    public void setActiveListings(long activeListings) { this.activeListings = activeListings; }
    public long getRecipientAccepted() { return recipientAccepted; }
    public void setRecipientAccepted(long recipientAccepted) { this.recipientAccepted = recipientAccepted; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}