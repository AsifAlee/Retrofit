package com.example.retrofit.Model;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

   // https://jsonplaceholder.typicode.com/comments/?postId=1
   /* @GET("comments")
    Call<List<Comment>> getComments(@Query("postId") int postId);*/

    // https://jsonplaceholder.typicode.com/comments/?postId=3&id=13
    //https://jsonplaceholder.typicode.com/comments/?postId=1&_sortby=id&_orderby=desc
   /* @GET("comments")
    Call<List<Comment>> getComments(@Query("postId") Integer postId,@Query("_sort") String sortBy,
                             @Query("_order") String orderby);*/
    @GET("comments")
    Call<List<Comment>> getComments(@QueryMap Map<String,String> params);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("userId") int userId,
                          @Field("title") String title,
                          @Field("body") String text);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> postMap);

    /**
     * you cant update a single column with a put method
     * while with patch method you can update a single column as
     * well
     * @param id
     * @param post
     * @return
     */

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id,@Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id,@Body Post post);


}
