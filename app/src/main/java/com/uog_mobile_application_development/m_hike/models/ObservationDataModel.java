package com.uog_mobile_application_development.m_hike.models;

import java.io.Serializable;

public class ObservationDataModel implements Serializable {
    private String observation;
    private String observationDateTime;
    private String observationComment;
    private int observationId;

    public ObservationDataModel( String observation, String observationDateTime, String observationComment) {
        this.observation = observation;
        this.observationDateTime = observationDateTime;
        this.observationComment = observationComment;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getObservationDateTime() {
        return observationDateTime;
    }

    public void setObservationDateTime(String observationDateTime) {
        this.observationDateTime = observationDateTime;
    }

    public String getObservationComment() {
        return observationComment;
    }

    public void setObservationComment(String observationComment) {
        this.observationComment = observationComment;
    }

    public int getObservationId() {
        return observationId;
    }

    public void setObservationId(int observationId) {
        this.observationId = observationId;
    }




}
