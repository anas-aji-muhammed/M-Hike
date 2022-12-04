package com.uog_mobile_application_development.m_hike.utils.api_service;


import com.uog_mobile_application_development.m_hike.models.APIResponseModel;
import com.uog_mobile_application_development.m_hike.models.PostHikeDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("comp1424cw")
    Call<APIResponseModel> postHikesData(@Body PostHikeDataModel data);
}