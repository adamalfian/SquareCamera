package com.example.irfan.squarecamera;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pentil on 11/09/18.
 */

public class ResponseApi {
    @SerializedName("hasil")
    String hasil;

    public String getHasil() {
        return hasil;
    }
}
