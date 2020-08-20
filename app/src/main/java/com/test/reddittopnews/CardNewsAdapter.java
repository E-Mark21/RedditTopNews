package com.test.reddittopnews;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;
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

public class CardNewsAdapter extends RecyclerView.Adapter<CardNewsAdapter.ViewHolder> {

    public static final String BASE_URL = "https://www.reddit.com/";
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news, parent, false);
        return new ViewHolder(cardView);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ObjectMapper objectMapper = new ObjectMapper();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        IAPIReddit iapiReddit = retrofit.create(IAPIReddit.class);
        Call<RedditTopNews> call = iapiReddit.getNews("", 25);
        call.enqueue(new Callback<RedditTopNews>() {
            @Override
            public void onResponse(Call<RedditTopNews> call, Response<RedditTopNews> response) {
                RedditTopNews redditTopNews = response.body();
                Data topNewsFromReddit = redditTopNews.getData();
                List<NewsArray> children = new ArrayList<>(topNewsFromReddit.getNewsArray());
                NewsArray newsArray = children.get(position);
                News news = newsArray.getNews();
                CardView cardView = holder.cardView;
                TextView author = (TextView) cardView.findViewById(R.id.author);
                author.setText(news.getSubreddit());
                String url = news.getUrl();
                ImageButton thumbnail = (ImageButton) cardView.findViewById(R.id.thumbnail);
                Picasso.with(holder.itemView.getContext())
                        .load(url)
                        .into(thumbnail);
                TextView title = (TextView) cardView.findViewById(R.id.title);
                title.setText("news.getTitle()");
                TextView postedTime = (TextView) cardView.findViewById(R.id.posted_time);
                postedTime.setText("58");
                TextView comments = (TextView) cardView.findViewById(R.id.comments);
                comments.setText("54");
            }

            @Override
            public void onFailure(Call<RedditTopNews> call, Throwable t) {
                CardView cardView = holder.cardView;
                TextView textView = (TextView) cardView.findViewById(R.id.author);
                textView.setText("ERROR");
            }
        });

        CardView cardView = holder.cardView;

        //TextView author = (TextView) cardView.findViewById(R.id.author);
       // author.setText("gyui");
     //   TextView title = (TextView) cardView.findViewById(R.id.title);
     //   title.setText("Clouds in Harrodsburg. Kentucky. No filter");
      //  TextView postedTime = (TextView) cardView.findViewById(R.id.posted_time);
      //  postedTime.setText("15");
       // TextView comments = (TextView) cardView.findViewById(R.id.comments);
       // comments.setText("68424");
       // ImageButton thumbnail = (ImageButton) cardView.findViewById(R.id.thumbnail);
       // Picasso.with(holder.itemView.getContext()).
      //          load("https://b.thumbs.redditmedia.com/Voef8Bob-2zZt41O-GGQxmgY1TKYdw0r1ylE8B1jxig.jpg")
     //           .into(thumbnail);
        TextView postedBy = (TextView) cardView.findViewById(R.id.posted_by);
        postedBy.setText(R.string.posted_by);
        TextView posted = (TextView) cardView.findViewById(R.id.posted);
        posted.setText(R.string.posted_hours_ago);
        TextView commentsText = (TextView) cardView.findViewById(R.id.comments_text);
        commentsText.setText(R.string.comments);


    }


    @Override
    public int getItemCount() {
        return 25;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}