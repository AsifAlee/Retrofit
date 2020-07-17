package com.example.retrofit.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyWebService {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    String FEED = "posts";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //@GET(FEED)
   // Call<List<Post>> getPosts();

   /* @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int userId);*/

   // https://jsonplaceholder.typicode.com/?postId=1
    @GET("comments")
    Call<List<Comment>> getComments(@Query("postId") int postId);
}
