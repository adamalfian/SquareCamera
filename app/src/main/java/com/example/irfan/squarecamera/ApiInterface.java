package com.example.irfan.squarecamera;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pentil on 11/09/18.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/kirimgambar")
    Call<ResponseApi> kirim (@Field("nrp") String nrp,
                             @Field("image") String image);

}
