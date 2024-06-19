package com.si.citizenreport.connection;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConnection {

    private static final String BASE_URL = "https://api-rest-cr.nicepebble-44974112.eastus.azurecontainerapps.io/"; // Reemplaza con la URL base de tu API
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
