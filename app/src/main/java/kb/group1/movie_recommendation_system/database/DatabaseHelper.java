package kb.group1.movie_recommendation_system.database;

import android.content.Context;

import kb.group1.movie_recommendation_system.database.movies.FavMovie;
import kb.group1.movie_recommendation_system.database.movies.MovieDatabase;
import kb.group1.movie_recommendation_system.database.search.RecentSearch;
import kb.group1.movie_recommendation_system.database.search.SearchDatabase;
import kb.group1.movie_recommendation_system.database.series.FavSeries;
import kb.group1.movie_recommendation_system.database.series.SeriesDatabase;

public class DatabaseHelper {
    public static boolean isFavMovie(Context context, Integer movieId){
        if(movieId == null) return false;
        FavMovie f = MovieDatabase.getInstance(context).movieDao().getMovieById(movieId);
        return f != null;
    }

    public static boolean isFavSeries(Context context, Integer seriesId){
        if(seriesId == null) return false;
        FavSeries f = SeriesDatabase.getInstance(context).seriesDao().getSeriesById(seriesId);
        return f != null;
    }

    public static boolean isRecentlySearched(Context context, String name){
        if(name == null) return false;
        RecentSearch recentSearch = SearchDatabase.getInstance(context).searchDao().getSearchesByName(name);
        return recentSearch != null;
    }
}
