package exercises.blue.demoagain.BeautyGallery.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * my ImageLoader
 * Created by getbl on 2016/6/6.
 */
public class imageLoader {
    private String baseUrl;
    private Bitmap mBitmapRes;
    private int defaultImageResource;
    private int errorImageResource;
    private ImageView targetImageView;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                targetImageView.setImageBitmap(mBitmapRes);
            } else{
                targetImageView.setImageResource(errorImageResource);
                //targetImageView.setImageDrawable(errorImage);
            }
        }
    };


    public imageLoader(String url) {
        baseUrl = url;
    }
    public imageLoader(){}
    public imageLoader into (ImageView imageView) {
        if (imageView != null) targetImageView = imageView;
        targetImageView.setImageResource(defaultImageResource);
        return this;
    }

    public imageLoader execute (String url) {
        baseUrl=url;
        System.out.println("Arrived");
        new Thread(new Runnable() {
            @Override
            public void run() {
                netStuff();
            }
        }).start();
        return this;
    }

    public imageLoader setDefaultImage(int defaultImageResource) {
        this.defaultImageResource = defaultImageResource;
        return this;
    }

    public imageLoader setErrorImage(int errorImageResource) {
        this.errorImageResource = errorImageResource;
        return this;
    }

    private void netStuff() {
                Message message = new Message();
                try {
                    URL url = new URL(baseUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream inputStream = conn.getInputStream();
                    mBitmapRes = BitmapFactory.decodeStream(inputStream);
                    message.what = 1;

                } catch (IOException e) {
                    message.what = 2;
                    e.printStackTrace();
                }
                finally {
                    mHandler.sendMessage(message);
                }

    }
}
