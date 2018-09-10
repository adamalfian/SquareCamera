package com.example.irfan.squarecamera;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pentil on 11/09/18.
 */

public class Server {
    public static final String url = "http://mobile.if.its.ac.id/";

    private static Retrofit retrofit;

    public static Retrofit getclient()
    {
        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
