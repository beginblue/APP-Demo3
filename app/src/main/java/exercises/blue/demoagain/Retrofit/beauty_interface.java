package exercises.blue.demoagain.Retrofit;


import exercises.blue.demoagain.userdata.beautyData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * I'm back and I'm going to replace Volley with Retrofit
 * Created by getbl on 2016/6/15.
 */

/**
 * interface with rxjava
 */
public interface beauty_interface {
        @GET("api/data/{category}/{count}/{page}")
        Observable<beautyData> getResult(@Path("category") String category,
                                         @Path("count") int count,
                                         @Path("page") int page);
    }

