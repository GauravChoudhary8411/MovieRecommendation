package kb.group1.movie_recommendation_system.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kb.group1.movie_recommendation_system.R;
import kb.group1.movie_recommendation_system.fragments.AccountFragment;
import kb.group1.movie_recommendation_system.fragments.FavouritesFragment;
import kb.group1.movie_recommendation_system.fragments.MovieFragment;
import kb.group1.movie_recommendation_system.fragments.SearchFragment;
import kb.group1.movie_recommendation_system.fragments.SeriesFragment;
public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the user is logged in
        if (!isUserLoggedIn()) {
            // If not logged in, redirect to the login page
            Intent loginIntent = new Intent(MainActivity.this, Login.class);
            startActivity(loginIntent);
            finish();  // Finish the current activity to prevent going back to it
            return;
        }

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        toolbar = findViewById(R.id.toolbar_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MovieFragment()).commit();
        toolbar.setTitle("Movies");

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_movie:
                        if (!getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName().equals("MovieFragment")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MovieFragment()).commit();
                            toolbar.setTitle("Movies");
                        }
                        break;
                    case R.id.nav_series:
                        if (!getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName().equals("SeriesFragment")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SeriesFragment()).commit();
                            toolbar.setTitle("Series");
                        }
                        break;
                    case R.id.nav_search:
                        if (!getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName().equals("SearchFragment")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
                            toolbar.setTitle("Search");
                        }
                        break;
                    case R.id.nav_favourites:
                        if (!getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName().equals("FavouritesFragment")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavouritesFragment()).commit();
                            toolbar.setTitle("Favourites");
                        }
                        break;
                    case R.id.nav_account:
                        if (!getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName().equals("AccountFragment")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                            toolbar.setTitle("Account");
                        }
                        break;
                }
                return true;
            }
        });
    }

    private boolean isUserLoggedIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        return user != null; // Returns true if the user is logged in, false otherwise
    }
}