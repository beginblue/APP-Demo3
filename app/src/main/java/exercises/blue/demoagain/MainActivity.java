package exercises.blue.demoagain;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentIntegrator;

import exercises.blue.userdata.dataSet;
import exercises.blue.userdata.userDatum;
import layout.agenda;
import layout.fragmentFriends;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, agenda.OnFragmentInteractionListener {

    ViewPager pager;
    public static String TAG = "inMain";
    dataSet set = dataSet.newInstance();
    fragmentPagerAdapter adapter;
    recyclerAdapter mRecyclerAdapter;
    fragmentFriends mFragmentFriends;
    agenda mAgenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main_real);
        //加载Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Blue");
        setSupportActionBar(toolbar);
        //加载FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMyDialog();
            }

        });


        //加载侧边栏
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //找到主界面
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //加载ViewPager
        pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        FragmentManager manager = getSupportFragmentManager();
        if (pager != null) {

            adapter = new fragmentPagerAdapter(manager);
            pager.setAdapter(adapter);
        }
        assert tabs != null;
        tabs.setupWithViewPager(pager);

        mFragmentFriends = (fragmentFriends) adapter.getFragment(0);
        mAgenda = (agenda) adapter.getFragment(1);
        mRecyclerAdapter = mFragmentFriends.getAdapter();
    }

    private void showMyDialog() {
        Intent addActivityIntent = new Intent(getApplicationContext(), publishActivity.class);
        startActivityForResult(addActivityIntent, 213);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.test_add:
                adapter.addFriends(new userDatum("text title 1", "test content 1", 0, 0));
                break;
            case R.id.test_add_more:
                adapter.addFriends(new userDatum("text title 1", "test content 1", 0, 0));
                adapter.addFriends(new userDatum("text title 2", "test content 2", 1, 0));
                adapter.addFriends(new userDatum("text title 3", "test content 3", 0, 0));
                break;
            case R.id.test_remove_at_zero:
                adapter.removeFriends(0);
                break;
            case R.id.test_remove_at:
                showDeleterItemDialog();
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            if (mRecyclerAdapter != null) {

            } else {
                Log.e(TAG, "onNavigationItemSelected: adapter is null");
            }
        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {

            //Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            //startActivity(intent);
        } else if (id == R.id.dianming) {
            //TODO:点名页面
            Intent intent = new Intent(getApplicationContext(), dianming.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            //Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
            intentIntegrator.initiateScan();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void click(View view) {
        if (view.getId() == R.id.imageView) {
            //TODO:启动用户个人资料设置界面
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 444 || resultCode == 0) return;
        if (requestCode == 213) {
            // userDatum datum = (userDatum) data.getExtras().get("data");
            userDatum datum = new userDatum();
            Bundle bundle = data.getBundleExtra("user");

            datum.setTitle(bundle.getString("title"));
            datum.setContent(bundle.getString("content"));
            datum.setCategory(bundle.getInt("category"));
            adapter.addFriends(datum);
//            for (int count = 0; count < set.getList().size(); count++) {
//                Log.e(TAG, "onActivityResult: " + set.getList().get(count).getTitle());
//            }

        }

    }


    public void showDeleterItemDialog() {

        View input = getLayoutInflater().inflate(R.layout.number_input_layout, null);
        final EditText etInput = (EditText) input.findViewById(R.id.input_number);
        //final int[] position = {0};
        //两个OnClickListener好像可以合并的样子
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("请输入要删除的位置")
                .setView(input)
                .setPositiveButton("删除",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int position = Integer.parseInt(etInput.getText().toString());
                                adapter.removeFriends(position);
                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                .create()
                .show();


    }

}
