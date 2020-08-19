package com.test.reddittopnews;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

public class CardNewsAdapter extends RecyclerView.Adapter<CardNewsAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news, parent, false);
        return new ViewHolder(cardView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView author = (TextView) cardView.findViewById(R.id.author);
        author.setText("gyui");
        TextView title = (TextView) cardView.findViewById(R.id.title);
        title.setText("Clouds in Harrodsburg. Kentucky. No filter");
        TextView postedTime = (TextView) cardView.findViewById(R.id.posted_time);
        postedTime.setText("15");
        TextView comments = (TextView) cardView.findViewById(R.id.comments);
        comments.setText("68424");
        ImageButton thumbnail = (ImageButton) cardView.findViewById(R.id.thumbnail);
        Picasso.with(holder.itemView.getContext()).
                load("https://b.thumbs.redditmedia.com/Voef8Bob-2zZt41O-GGQxmgY1TKYdw0r1ylE8B1jxig.jpg")
                .into(thumbnail);
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
