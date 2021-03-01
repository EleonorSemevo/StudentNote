package com.memoire.studentnote.pojo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit = null;
    public static RegisterInterface getClient() {

        // change your base URL
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://phplaravel-551937-1773203.cloudwaysapps.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //Creating object for our interface
        RegisterInterface api = retrofit.create(RegisterInterface.class);
        return api; // return the APIInterface object
    }

}