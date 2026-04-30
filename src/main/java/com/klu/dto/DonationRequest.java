package com.klu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class DonationRequest {
//json property
    @JsonProperty("donor_email")
    @NotBlank
    private String donorEmail;

    @JsonProperty("food_name")
    @NotBlank
    private String foodName;

    @NotBlank
    private String category;

    @NotBlank
    private String quantity;

    @JsonProperty("pickup_location")
    @NotBlank
    private String pickupLocation;

    @JsonProperty("phone_number")
    @NotBlank
    private String phoneNumber;

    private String description;

    @JsonProperty("expiry_date")
    @NotNull
    private LocalDateTime expiryDate;

    public String getDonorEmail() { return donorEmail; }
    public void setDonorEmail(String donorEmail) { this.donorEmail = donorEmail; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
}
