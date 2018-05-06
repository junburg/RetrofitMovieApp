package moon.the.on.junburg.com.retrofitmovieapp.api;

import moon.the.on.junburg.com.retrofitmovieapp.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Junburg on 2018. 5. 6..
 */

public interface Service {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key")String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key")String apiKey);

}
