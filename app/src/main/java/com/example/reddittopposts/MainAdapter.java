package com.example.reddittopposts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.reddittopposts.models.Entity;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<Entity> entities;
    private Activity activity;

    public MainAdapter(List<Entity> entities, Activity activity) {
        this.entities = entities;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entity entity = entities.get(position);

        Glide.with(activity).load(entity.getThumbnail())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        holder.titleView.setText(entity.getTitle());
        holder.authorView.setText(entity.getAuthor());
        holder.hoursAgoView.setText(entity.getCreated());
        holder.commentsAgoView.setText(entity.getComments() + " comments");
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView hoursAgoView;
        TextView commentsAgoView;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.post_title);
            authorView = itemView.findViewById(R.id.post_author);
            hoursAgoView = itemView.findViewById(R.id.post_hours_ago);
            commentsAgoView = itemView.findViewById(R.id.post_comments);
            imageView = itemView.findViewById(R.id.post_thumbnail);
        }
    }
}