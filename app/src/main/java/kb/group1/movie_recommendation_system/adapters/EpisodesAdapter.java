package kb.group1.movie_recommendation_system.adapters;

import android.content.Context;
import android.text.TextUtils;
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
import kb.group1.movie_recommendation_system.network.series.EpisodeBrief;
import kb.group1.movie_recommendation_system.utils.Constants;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.ViewHolder> {

    private List<EpisodeBrief> mEpisodes;
    private String series_id;
    private Context mContext;

    public EpisodesAdapter(List<EpisodeBrief> mEpisodes, String series_id, Context mContext) {
        this.mEpisodes = mEpisodes;
        this.series_id = series_id;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public EpisodesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodesAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_episode, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodesAdapter.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(Constants.IMAGE_LOADING_BASE_URL_780 + mEpisodes.get(position).getStillPath())
                .centerCrop()
                .placeholder(R.drawable.poster_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.episode_imageView);

        holder.episode_name.setText(mEpisodes.get(position).getName());

        holder.episode_number.setText("S" + mEpisodes.get(position).getSeasonNumber().toString() + "E" + mEpisodes.get(position).getEpisodeNumber().toString());
    }

    @Override
    public int getItemCount() {
        return mEpisodes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView episode_cardView;
        private ImageView episode_imageView;
        private TextView episode_name;
        private TextView episode_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            episode_cardView = itemView.findViewById(R.id.eps_cardView);
            episode_imageView = itemView.findViewById(R.id.eps_image);
            episode_name = itemView.findViewById(R.id.eps_name);
            episode_number = itemView.findViewById(R.id.eps_episode);

            episode_name.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            episode_name.setSelected(true);
        }
    }
}
