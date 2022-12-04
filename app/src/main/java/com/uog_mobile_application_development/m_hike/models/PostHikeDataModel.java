package com.uog_mobile_application_development.m_hike.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PostHikeDataModel {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("detailList")
    @Expose
    private ArrayList<APIIndividualHikeDetailsModel> detailList = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<APIIndividualHikeDetailsModel> getDetailList() {
        return detailList;
    }

    public void setDetailList(ArrayList<APIIndividualHikeDetailsModel> detailList) {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PostHikeDataModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("detailList");
        sb.append('=');
        sb.append(((this.detailList == null)?"<null>":this.detailList));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}