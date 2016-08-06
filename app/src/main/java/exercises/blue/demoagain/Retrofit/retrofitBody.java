package exercises.blue.demoagain.Retrofit;

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
    private Retrofit retrofit;
    private beauty_interface service;
    private responseBean toRtn;
    boolean finished =false;
//    Handler handler ;
//
//    public retrofitBody(Handler handler){
//        this.handler = handler;
//    }



    private void prepare(){
        System.out.println("in");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(beauty_interface.class);
    }

    public void beautyRequest(String category,int count,int page,Subscriber<responseBean> subscriber) {
        prepare();
        service.getResult(category, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribeOn(Schedulers.newThread())
                .subscribe(subscriber);
//                .subscribe(new Subscriber<responseBean>() {
//                    @Override
//                    public void onCompleted() {
//                        System.out.println("onCompleted");
//                        for (responseBean.ResultsBean bean :
//                                toRtn.getResults()) {
//                            System.out.println(bean.get_id()+bean.getDesc());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        //e.printStackTrace();
//                        System.out.println("TAT" + e.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onNext(responseBean responseBean) {
//                        System.out.println("onNext");
//                        toRtn = responseBean;
//                    }
//                });

    }


}
