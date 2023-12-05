package kb.group1.movie_recommendation_system.request;

import kb.group1.movie_recommendation_system.network.cast.Person;
import kb.group1.movie_recommendation_system.network.movie.GenreMoviesResponse;
import kb.group1.movie_recommendation_system.network.movie.MovieCastsOfPersonResponse;
import kb.group1.movie_recommendation_system.network.movie.SimilarMoviesResponse;
import kb.group1.movie_recommendation_system.network.movie.Movie;
import kb.group1.movie_recommendation_system.network.movie.MovieCreditsResponse;
import kb.group1.movie_recommendation_system.network.movie.NowShowingMoviesResponse;
import kb.group1.movie_recommendation_system.network.movie.PopularMoviesResponse;
import kb.group1.movie_recommendation_system.network.movie.TopRatedMoviesResponse;
import kb.group1.movie_recommendation_system.network.series.AiringTodaySeriesResponse;
import kb.group1.movie_recommendation_system.network.series.OnTheAirSeriesResponse;
import kb.group1.movie_recommendation_system.network.series.PopularSeriesResponse;
import kb.group1.movie_recommendation_system.network.series.SeasonDetailsResponse;
import kb.group1.movie_recommendation_system.network.series.Series;
import kb.group1.movie_recommendation_system.network.series.SeriesCastsOfPersonResponse;
import kb.group1.movie_recommendation_system.network.series.SeriesCreditsResponse;
import kb.group1.movie_recommendation_system.network.series.SimilarSeriesResponse;
import kb.group1.movie_recommendation_system.network.series.TopRatedSeriesResponse;
import kb.group1.movie_recommendation_system.network.videos.TrailersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // Movie
    @GET("movie/now_playing")
    Call<NowShowingMoviesResponse> getNowShowingMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/popular")
    Call<PopularMoviesResponse> getPopularMovies(@Query("api_key") String api_key, @Query("page") int page);

    @GET("movie/top_rated")
    Call<TopRatedMoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") Integer movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<TrailersResponse> getMovieVideos(@Path("id") Integer movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/credits")
    Call<MovieCreditsResponse> getMovieCredits(@Path("id") Integer movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/similar")
    Call<SimilarMoviesResponse> getSimilarMovies(@Path("id") Integer movieId, @Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("discover/movie")
    Call<GenreMoviesResponse> getMoviesByGenre(@Query("api_key") String apiKey, @Query("with_genres") Integer genreNumber, @Query("page") Integer page);

    // Series
    @GET("tv/airing_today")
    Call<AiringTodaySeriesResponse> getAiringTodaySeries(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/on_the_air")
    Call<OnTheAirSeriesResponse> getOnTheAirSeries(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/popular")
    Call<PopularSeriesResponse> getPopularSeries(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/top_rated")
    Call<TopRatedSeriesResponse> getTopRatedSeries(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/{id}")
    Call<Series> getSeriesDetails(@Path("id") Integer tvShowId, @Query("api_key") String apiKey, @Query("append_to_response") String append_to_response);

    @GET("tv/{id}/videos")
    Call<TrailersResponse> getSeriesVideos(@Path("id") Integer movieId, @Query("api_key") String apiKey);

    @GET("tv/{id}/credits")
    Call<SeriesCreditsResponse> getSeriesCredits(@Path("id") Integer movieId, @Query("api_key") String apiKey);

    @GET("tv/{id}/similar")
    Call<SimilarSeriesResponse> getSimilarSeries(@Path("id") Integer movieId, @Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/{id}/season/{season}")
    Call<SeasonDetailsResponse> getSeasonDetails(@Path("id") Integer id, @Path("season") Integer season_number, @Query("api_key") String apiKey);

    // Cast
    @GET("person/{id}")
    Call<Person> getPersonDetails(@Path("id") Integer personId, @Query("api_key") String apiKey);

    @GET("person/{id}/movie_credits")
    Call<MovieCastsOfPersonResponse> getMovieCastsOfPerson(@Path("id") Integer personId, @Query("api_key") String apiKey);

    @GET("person/{id}/tv_credits")
    Call<SeriesCastsOfPersonResponse> getTVCastsOfPerson(@Path("id") Integer personId, @Query("api_key") String apiKey);
}
