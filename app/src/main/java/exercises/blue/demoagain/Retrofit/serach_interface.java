package exercises.blue.demoagain.Retrofit;

import exercises.blue.demoagain.seek.seekResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by getbl on 2016/8/14.
 */
public interface serach_interface {
    //http://gank.io/api/search/query/listview/category/Android/count/10/page/1
    @GET("api/search/query/{key}/category/{category}/count/{count}/page/{page}")
    Observable<seekResponse> getResult(@Path("key") String key,
                                       @Path("category") String category,
                                       @Path("count") int count,
                                       @Path("page") int page);
}
