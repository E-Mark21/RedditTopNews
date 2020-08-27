package com.test.reddittopnews;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CardNewsAdapter extends RecyclerView.Adapter<CardNewsAdapter.ViewHolder> {

    public static String BASE_URL = "https://www.reddit.com/";
    private String[] authorName;
    private String[] titleNews;
    private String[] thumbnailNews;
    private int[] numCommentsNews;
    private static int[] createdUtcNews;
    private static String[] urlNews;
    private Listener listener;

    interface Listener {
        void onClick(int position);
    }

    public static String getUrlNews(int urlID) {
        return urlNews[urlID];
    }

    public CardNewsAdapter(String[] author, String[] titleNews, String[] thumbnailNews, int[] numCommentsNews, int[] createdUtcNews, String[] urlNews) {
        this.authorName = author;
        this.titleNews = titleNews;
        this.thumbnailNews = thumbnailNews;
        this.numCommentsNews = numCommentsNews;
        this.createdUtcNews = createdUtcNews;
        this.urlNews = urlNews;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        ImageView thumbnail = (ImageView) cardView.findViewById(R.id.thumbnail);
        Picasso.with(holder.itemView.getContext()).
                load(thumbnailNews[position])
                .placeholder(R.drawable.placeholder)
                .into(thumbnail);
        TextView author = (TextView) cardView.findViewById(R.id.author);
        author.setText(authorName[position]);
        TextView title = (TextView) cardView.findViewById(R.id.title);
        title.setText(titleNews[position]);
        Calendar calendar = new GregorianCalendar();
        Date today = calendar.getTime();
        long milSec = createdUtcNews[position] * 1000L;
        long duration = today.getTime() - milSec;
        int hours = (int) (duration / (60 * 60 * 1000));
        TextView postedTime = (TextView) cardView.findViewById(R.id.posted_time);
        postedTime.setText(hours + "");

        TextView comments = (TextView) cardView.findViewById(R.id.comments);
        comments.setText(numCommentsNews[position] + "");
        TextView postedBy = (TextView) cardView.findViewById(R.id.posted_by);
        postedBy.setText(R.string.posted_by);
        TextView posted = (TextView) cardView.findViewById(R.id.posted);
        posted.setText(R.string.posted_hours_ago);
        TextView commentsText = (TextView) cardView.findViewById(R.id.comments_text);
        commentsText.setText(R.string.comments);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}