package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.retrofit.Model.Comment;
import com.example.retrofit.Model.MyWebService;
import com.example.retrofit.Model.Post;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                DeletePost();
            }
        });
    }

    private void showPost(Post post) {
        textView.append("\n" + "userId:"+post.getUserId()+"\n");
        textView.append("id:"+post.getId()+"\n");
        textView.append("Title:"+post.getTitle()+"\n");
        textView.append("body:"+post.getBody()+"\n");


    }




    public void DeletePost(){
        myWebService.deletePost(5)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()){
                            textView.append(String.valueOf(response.code()));

                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                });
    }


}