package com.test.reddittopnews.JSON;

import com.fasterxml.jackson.databind.ObjectMapper;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class RetrofitController implements Callback<RedditTopNews> {

    public static final String BASE_URL = "https://www.reddit.com/";

    public IAPIReddit start() {
        ObjectMapper objectMapper = new ObjectMapper();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        IAPIReddit iapiReddit = retrofit.create(IAPIReddit.class);
        //Call<RedditTopNews> call = iapiReddit.getNews("");
        //call.enqueue(this);
        return iapiReddit;
    }


    @Override
    public void onResponse(Call<RedditTopNews> call, Response<RedditTopNews> response) {
        if (response.isSuccessful()) {


        }
    }

    @Override
    public void onFailure(Call<RedditTopNews> call, Throwable t) {

    }
}
