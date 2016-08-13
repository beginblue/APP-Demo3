package exercises.blue.demoagain.seek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import exercises.blue.demoagain.R;
import exercises.blue.demoagain.Retrofit.retrofitBody;
import rx.Subscriber;

public class Main2Activity extends AppCompatActivity {

    MyAdapter adapter;
    private String sCategory;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView listView = (ListView) findViewById(R.id.lv_main);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);

        final EditText editText = (EditText) findViewById(R.id.EditText1);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.inSpinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              sCategory= (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sCategory="all";
            }
        });

        Button button = (Button) findViewById(R.id.button);
        if (button != null)
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String toSearch = editText.getText().toString();
                    search(toSearch,sCategory);
                }
            });
    }

    public void search(String key,String category) {
        //mRequestQueue = Volley.newRequestQueue(mView.getContext());
        //Volley
//        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
//
//        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
//                "http://gank.io/api/search/query/"+key+"/category/all/count/10/page/1",
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            ArrayList<ItemBean> fList = new ArrayList<>();// save results
//                            JSONArray jsonArray = response.getJSONArray("results");// real results
//                            for (int count = 0; count < jsonArray.length(); count++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(count);
//                                String title = jsonObject.getString("desc");
//                                String content = jsonObject.getString("url");
//
//                                fList.add(new ItemBean(title,content));
//                            }
//                            //脑残!(╯‵□′)╯︵┻━┻
//                            adapter.addAll(fList);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//               error.printStackTrace();
//            }
//        });
//        mRequestQueue.add(request);

        adapter.removeAll();
        new retrofitBody().queryRequest(key, category, 50, 1, new Subscriber<seekResponse>() {
            @Override
            public void onCompleted() {
                Toast.makeText(Main2Activity.this, "finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(seekResponse seekResponse) {
                adapter.addAll(seekResponse.getResults());
            }
        });
    }
}
