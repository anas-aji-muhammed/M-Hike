package com.uog_mobile_application_development.m_hike.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIResponseModel {

    @SerializedName("uploadResponseCode")
    @Expose
    private String uploadResponseCode;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("names")
    @Expose
    private String names;
    @SerializedName("message")
    @Expose
    private String message;

    public String getUploadResponseCode() {
        return uploadResponseCode;
    }

    public void setUploadResponseCode(String uploadResponseCode) {
        this.uploadResponseCode = uploadResponseCode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(APIResponseModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("uploadResponseCode");
        sb.append('=');
        sb.append(((this.uploadResponseCode == null)?"<null>":this.uploadResponseCode));
        sb.append(',');
        sb.append("userid");
        sb.append('=');
        sb.append(((this.userid == null)?"<null>":this.userid));
        sb.append(',');
        sb.append("number");
        sb.append('=');
        sb.append(((this.number == null)?"<null>":this.number));
        sb.append(',');
        sb.append("names");
        sb.append('=');
        sb.append(((this.names == null)?"<null>":this.names));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}