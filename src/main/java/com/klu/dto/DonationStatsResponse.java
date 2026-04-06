package com.klu.dto;

public class DonationStatsResponse {
    private long totalDonations;
    private long activeListings;
    private long recipientAccepted;

    public DonationStatsResponse() {}

    public DonationStatsResponse(long totalDonations, long activeListings, long recipientAccepted) {
        this.totalDonations = totalDonations;
        this.activeListings = activeListings;
        this.recipientAccepted = recipientAccepted;
    }

    public long getTotalDonations() { return totalDonations; }
    public void setTotalDonations(long totalDonations) { this.totalDonations = totalDonations; }
    public long getActiveListings() { return activeListings; }
    public void setActiveListings(long activeListings) { this.activeListings = activeListings; }
    public long getRecipientAccepted() { return recipientAccepted; }
    public void setRecipientAccepted(long recipientAccepted) { this.recipientAccepted = recipientAccepted; }
}
