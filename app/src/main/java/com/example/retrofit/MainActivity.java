package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.retrofit.Model.MyWebService;
import com.example.retrofit.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button runCode;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runCode = findViewById(R.id.runCode);
        textView = findViewById(R.id.textView);
        runCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyWebService myWebService = MyWebService.retrofit.create(MyWebService.class);
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
            }
        });
    }

    private void showPost(Post post) {
        textView.append("userId:"+post.getUserId()+"\n");
        textView.append("id:"+post.getId()+"\n");
        textView.append("Title:"+post.getTitle()+"\n");
        textView.append("body:"+post.getBody()+"\n");


    }
}