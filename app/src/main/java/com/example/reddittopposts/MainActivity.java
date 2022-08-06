package com.example.reddittopposts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.reddittopposts.models.Entity;
import com.example.reddittopposts.repository.RedditService;
import com.example.reddittopposts.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Entity> entities;
    MainAdapter mainAdapter;
    int limit = 10;
    String after = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nestedScrollView = findViewById(R.id.scroll_view);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        entities = new ArrayList<>();

        mainAdapter = new MainAdapter(entities, MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);

        getData(limit, after);

        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)
                (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                        after = entities.get(entities.size() - 1).getId();
                        progressBar.setVisibility(View.VISIBLE);
                        getData(limit, after);
                    }
                });

    }

    private void getData(int limit, String after) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RedditService redditService = retrofit.create(RedditService.class);
        Call<String> call = redditService.STRING_CALL(limit, after);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        parseResult(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // TODO
            }
        });
    }

    private void parseResult(JSONObject jsonObject) {
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            for (int i = 0; i < children.length(); i++) {
                JSONObject element = children.getJSONObject(i);
                JSONObject eachData = element.getJSONObject("data");

                Entity entity = new Entity();
                entity.setTitle(eachData.getString("title"));
                entity.setAuthor(eachData.getString("author"));
                entity.setComments(Integer.parseInt(eachData.getString("num_comments")));
                entity.setThumbnail(eachData.getString("thumbnail"));
                entity.setCreated(Utils.convertUTC(eachData.getLong("created_utc")));
                entity.setId(eachData.getString("name"));

                entities.add(entity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mainAdapter = new MainAdapter(entities, MainActivity.this);
        recyclerView.setAdapter(mainAdapter);
    }
}