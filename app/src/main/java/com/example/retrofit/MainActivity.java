package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.retrofit.Model.Comment;
import com.example.retrofit.Model.MyWebService;
import com.example.retrofit.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button runCode;
    TextView textView;


    MyWebService myWebService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runCode = findViewById(R.id.runCode);
        textView = findViewById(R.id.textView);
        myWebService = MyWebService.retrofit.create(MyWebService.class);
        runCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getComments();
            }
        });
    }

    private void showPost(Post post) {
        textView.append("userId:"+post.getUserId()+"\n");
        textView.append("id:"+post.getId()+"\n");
        textView.append("Title:"+post.getTitle()+"\n");
        textView.append("body:"+post.getBody()+"\n");


    }
    private void showComments(Comment comment){
        textView.append("postId:"+comment.getPostId()+"\n");
        textView.append("id:"+comment.getId()+"\n");
        textView.append("name:"+comment.getName()+"\n");
        textView.append("email:"+comment.getEmail()+"\n");
        textView.append("body:"+comment.getBody()+"\n"+"\n");
    }

   /* public void getPosts(){
        Call<List<Post>> call =myWebService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    for(Post post:response.body()){
                        showPost(post);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });

    }*/


    public void getComments(){
        Call<List<Comment>> call = myWebService.getComments(5);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()){
                    for(Comment comment:response.body()){
                        showComments(comment);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }
}