package com.uog_mobile_application_development.m_hike.models;

public class HikeDataModel {
    private String hikeName;
    private String hikeLocation;
    private String hikeDate;
    private String parkingAvailability;
    private String hikeLength;
    private String hikeDifficulty;
    private String hikeDescription;
    private int hikeId;

    public HikeDataModel(String hikeName, String hikeLocation, String hikeDate, String parkingAvailability, String hikeLength, String hikeDifficulty, String hikeDescription, int hikeId) {
        this.hikeName = hikeName;
        this.hikeLocation = hikeLocation;
        this.hikeDate = hikeDate;
        this.parkingAvailability = parkingAvailability;
        this.hikeLength = hikeLength;
        this.hikeDifficulty = hikeDifficulty;
        this.hikeDescription = hikeDescription;
        this.hikeId = hikeId;
    }

    public String getHikeName() {
        return hikeName;
    }

    public void setHikeName(String hikeName) {
        this.hikeName = hikeName;
    }

    public String getHikeLocation() {
        return hikeLocation;
    }

    public void setHikeLocation(String hikeLocation) {
        this.hikeLocation = hikeLocation;
    }

    public String getHikeDate() {
        return hikeDate;
    }

    public void setHikeDate(String hikeDate) {
        this.hikeDate = hikeDate;
    }

    public String getParkingAvailability() {
        return parkingAvailability;
    }

    public void setParkingAvailability(String parkingAvailability) {
        this.parkingAvailability = parkingAvailability;
    }

    public String getHikeLength() {
        return hikeLength;
    }

    public void setHikeLength(String hikeLength) {
        this.hikeLength = hikeLength;
    }

    public String getHikeDifficulty() {
        return hikeDifficulty;
    }

    public void setHikeDifficulty(String hikeDifficulty) {
        this.hikeDifficulty = hikeDifficulty;
    }

    public String getHikeDescription() {
        return hikeDescription;
    }

    public void setHikeDescription(String hikeDescription) {
        this.hikeDescription = hikeDescription;
    }


    public int getHikeId() {
        return hikeId;
    }

    public void setHikeId(int hikeId) {
        this.hikeId = hikeId;
    }
}
