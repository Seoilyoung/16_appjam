package org.androidtown.myko.network;

import org.androidtown.myko.model.Info_Professor;
import org.androidtown.myko.model.Info_Review;
import org.androidtown.myko.model.Info_User;
import org.androidtown.myko.model.test;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Administrator on 2015-07-08.
 */
public interface ServerInterface {

    @POST("/userinfo/login")
    void login(@Body Info_User info_user, Callback<Info_User> callback) ;

    @GET("/reviewinfo/{procode}")
    void getreview_totalpoint(@Path("procode") String procode, Callback<List<Info_Review>> callback) ;

    @POST("/reviewinfo/id")
    void getreview_time(@Body Info_Review info_review, Callback<List<Info_Review>> callback) ;

    @POST("/userinfo/register")
    public void join(@Body Info_User info_user, Callback<Info_User> callback) ;

    @POST("/reviewinfo")
    void newreview(@Body Info_Review info_review, Callback<Info_Review> callback);

    @GET("/professorinfo/{proname}")
    void search_professor(@Path("proname") String proname, Callback<List<Info_Professor>> callback);

    @POST("/reviewinfo/good")
    void good(@Body Info_Review info_review , Callback<List<Info_Review>> callback) ;

    @POST("/reviewinfo/bad")
    void bad(@Body Info_Review info_review , Callback<List<Info_Review>> callback) ;

    @POST("/reviewinfo/delete")
    void delete(@Body Info_Review info_review, Callback<List<Info_Review>> callback) ;

    @POST("/reviewinfo/average")
    void average(@Body Info_Professor info_professor, Callback<Info_Review> callback) ;
}