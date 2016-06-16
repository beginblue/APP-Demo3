package exercises.blue.demoagain.seek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import exercises.blue.demoagain.R;

public class Main2Activity extends AppCompatActivity {

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView listView = (ListView) findViewById(R.id.lv_main);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);

        final EditText editText = (EditText) findViewById(R.id.EditText1);


        Button button = (Button) findViewById(R.id.button);
        if(button!=null)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSearch = editText.getText().toString();
                search(toSearch);
            }
        });
    }

    public void search(String key) {
        //mRequestQueue = Volley.newRequestQueue(mView.getContext());
        //Volley
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://gank.io/api/search/query/"+key+"/category/all/count/10/page/1",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<ItemBean> fList = new ArrayList<>();// save results
                            JSONArray jsonArray = response.getJSONArray("results");// real results
                            for (int count = 0; count < jsonArray.length(); count++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(count);
                                String title = jsonObject.getString("desc");
                                String content = jsonObject.getString("url");

                                fList.add(new ItemBean(title,content));
                            }
                            //脑残!(╯‵□′)╯︵┻━┻
                            adapter.addAll(fList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }
}
