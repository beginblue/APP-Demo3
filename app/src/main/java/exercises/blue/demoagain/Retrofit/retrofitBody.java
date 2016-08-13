package exercises.blue.demoagain.Retrofit;

import exercises.blue.demoagain.seek.seekResponse;
import exercises.blue.demoagain.userdata.responseBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by getbl on 2016/6/15.
 */
public class retrofitBody {
    //private Retrofit retrofit;
    private beauty_interface service;
    private responseBean toRtn;
    boolean finished = false;
//    Handler handler ;
//
//    public retrofitBody(Handler handler){
//        this.handler = handler;
//    }


    private Retrofit prepare() {
        System.out.println("in");
        return new Retrofit.Builder()
                .baseUrl("http://gank.io")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public void beautyRequest(String category, int count, int page, Subscriber<responseBean> subscriber) {
       Retrofit retrofit =  prepare();
        retrofit.create(beauty_interface.class)
                .getResult(category, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void queryRequest(String key, String category, int count, int page, Subscriber<seekResponse> subscriber) {
        Retrofit retrofit = prepare();
        retrofit.create(serach_interface.class)
                .getResult(key,category,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
