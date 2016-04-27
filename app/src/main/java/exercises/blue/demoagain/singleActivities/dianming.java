package exercises.blue.demoagain.singleActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import exercises.blue.demoagain.R;

public class dianming extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianming);
        getSupportActionBar().hide();

        webView = (WebView) findViewById(R.id.webPage);
        if (webView != null) {
            webView.loadUrl("http://www.baidu.com");
            //webView.loadUrl("http://www.51uns.com/kq/Index.aspx?
            // weixin=1&
            // xsid=bb9421011cc34736a1b19e3390fbc71e&
            // sch=10710&
            // ts=635966160948318878");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webviewmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            webView.reload();
        }

        return true;
    }
}
