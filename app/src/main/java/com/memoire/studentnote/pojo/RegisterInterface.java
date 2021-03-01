package com.memoire.studentnote.pojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {
    @FormUrlEncoded // annotation used in POST type requests
    @POST("register")
        // API's endpoints
    Call<com.memoire.studentnote.pojo.RegisterResponse> registration(@Field("nom") String nom,
                                                                @Field("prenom") String prenom,
                                                                @Field("telephone") String telephone,
                                                                @Field("mail") String mail,
                                                                @Field("password") String password,
                                                                @Field("type") String type,
                                                                @Field("c_password") String c_password);
}
