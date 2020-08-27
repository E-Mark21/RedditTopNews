package com.test.reddittopnews;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.reddittopnews.JSON.Data;
import com.test.reddittopnews.JSON.IAPIReddit;
import com.test.reddittopnews.JSON.News;
import com.test.reddittopnews.JSON.NewsArray;
import com.test.reddittopnews.JSON.RedditTopNews;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class NewsFragment extends Fragment {

    private static String BASE_URL = "https://www.reddit.com/";
    private static String[] authorNews = new String[100];
    private static String[] titleNews = new String[100];
    private static String[] thumbnailNews = new String[100];
    private static int[] numCommentsNew = new int[100];
    private static int[] createdUtcNews = new int[100];
    private static String[] urlNews = new String[100];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView newsRecyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_news, container, false);
        ObjectMapper objectMapper = new ObjectMapper();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        IAPIReddit iapiReddit = retrofit.create(IAPIReddit.class);
        Call<RedditTopNews> call = iapiReddit.getNews("", 50);
        call.enqueue(new Callback<RedditTopNews>() {
            @Override
            public void onResponse(Call<RedditTopNews> call, Response<RedditTopNews> response) {
                RedditTopNews redditTopNews = response.body();
                Data topNewsFromReddit = redditTopNews.getData();
                List<NewsArray> children = new ArrayList<>(topNewsFromReddit.getNewsArray());
                for (int i = 0; i < children.size(); i++) {
                    NewsArray newsArray = children.get(i);
                    News news = newsArray.getNews();
                    authorNews[i] = news.getAuthor();
                    titleNews[i] = news.getTitle();
                    numCommentsNew[i] = news.getNum_comments();
                    createdUtcNews[i] = news.getCreated_utc();
                    thumbnailNews[i] = news.getThumbnail();
                    urlNews[i] = news.getUrl();
                }
            }

            @Override
            public void onFailure(Call<RedditTopNews> call, Throwable t) {

            }
        });

        CardNewsAdapter adapter = new CardNewsAdapter(authorNews, titleNews, thumbnailNews, numCommentsNew, createdUtcNews, urlNews);
        newsRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsRecyclerView.setLayoutManager(layoutManager);
        adapter.setListener(new CardNewsAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ImageViewerActivity.class);
                intent.putExtra(ImageViewerActivity.EXTRA_URL, position);
                getActivity().startActivity(intent);
            }
        });
        return newsRecyclerView;
    }
}