package kb.group1.movie_recommendation_system.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import kb.group1.movie_recommendation_system.R;
import kb.group1.movie_recommendation_system.activities.YoutubeActivity;
import kb.group1.movie_recommendation_system.network.videos.Trailer;
import kb.group1.movie_recommendation_system.utils.Constants;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.VideoViewHolder> {
    private Context mContext;
    private List<Trailer> mVideos;

    public TrailerAdapter(Context mContext, List<Trailer> mVideos) {
        this.mContext = mContext;
        this.mVideos = mVideos;
    }

    @NonNull
    @Override
    public TrailerAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.VideoViewHolder holder, int position) {
        Glide.with(mContext.getApplicationContext())
                .load(Constants.YOUTUBE_THUMBNAIL_BASE_URL + mVideos.get(position).getKey() + Constants.YOUTUBE_THUMBNAIL_IMAGE_QUALITY)
                .centerCrop()
                .placeholder(R.drawable.trailer_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.trailer_imageView);

        if (mVideos.get(position).getName() != null)
            holder.trailer_textView.setText(mVideos.get(position).getName());
        else
            holder.trailer_textView.setText("");
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private CardView trailer_cardView;
        private ImageView trailer_imageView;
        private TextView trailer_textView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            trailer_cardView = itemView.findViewById(R.id.trailer_cardView);
            trailer_imageView = itemView.findViewById(R.id.trailer_imageView);
            trailer_textView = itemView.findViewById(R.id.trailer_textView);

            trailer_cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open the YouTube app with the specified video ID
                    openYouTubeVideo(mVideos.get(getAdapterPosition()).getKey());
                }
            });
        }

        // Open YouTube video in the YouTube app
        private void openYouTubeVideo(String videoId) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
            intent.putExtra("VIDEO_ID", videoId);

            // Check if the YouTube app is installed
            if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                mContext.startActivity(intent);
            } else {
                // If the YouTube app is not installed, open in the browser
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videoId));
                mContext.startActivity(intent);
            }
        }
    }
}