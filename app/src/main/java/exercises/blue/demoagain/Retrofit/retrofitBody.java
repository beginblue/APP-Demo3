package exercises.blue.demoagain.Retrofit;

import android.os.Handler;

import exercises.blue.demoagain.userdata.beautyData;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.schedulers.Schedulers;
/**
 * Created by getbl on 2016/6/15.
 */
public class retrofitBody {
    private Retrofit retrofit;
    private beauty_interface service;
    private beautyData toRtn;
    boolean finished =false;
    Handler handler ;

    public retrofitBody(Handler handler){
        this.handler = handler;
    }


    public void beautyRequest() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(beauty_interface.class);

        service.getResult("福利", 10, 1)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<beautyData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //e.printStackTrace();
                        System.out.println("TAT" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(beautyData beautyData) {
                        toRtn = beautyData;
                    }
                });

    }


}
