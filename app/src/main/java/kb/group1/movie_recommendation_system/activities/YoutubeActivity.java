package kb.group1.movie_recommendation_system.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import kb.group1.movie_recommendation_system.R;
import kb.group1.movie_recommendation_system.utils.Constants;

public class YoutubeActivity extends YouTubeBaseActivity {
    private YouTubePlayerView youTubePlayerView;
    private FloatingActionButton youtube_close;

    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        youTubePlayerView = findViewById(R.id.youtube_view);
        youtube_close = findViewById(R.id.youtube_close);

        Intent receivedIntent = getIntent();
        String youtube_id = receivedIntent.getStringExtra("youtube_id");

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    youTubePlayer.loadVideo(youtube_id);
                    setFullscreenListener(youTubePlayer);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                // Handle initialization failure
            }
        };

        youTubePlayerView.initialize(Constants.YOUTUBE_API_KEY, onInitializedListener);

        youtube_close.setOnClickListener(view -> onBackPressed());
    }

    // Set fullscreen listener after the player is initialized
    private void setFullscreenListener(final YouTubePlayer youTubePlayer) {
        youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
            @Override
            public void onFullscreen(boolean isFullscreen) {
                if (isFullscreen) {
                    // Adjust UI when in fullscreen mode
                    youtube_close.setVisibility(View.GONE);
                } else {
                    // Adjust UI when in normal mode
                    youtube_close.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}