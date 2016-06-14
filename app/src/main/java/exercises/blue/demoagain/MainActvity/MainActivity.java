package exercises.blue.demoagain.MainActvity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

import exercises.blue.demoagain.CollectionActivity.ScrollingActivity;
import exercises.blue.demoagain.R;
import exercises.blue.demoagain.agendaFragement.agenda;
import exercises.blue.demoagain.friendsFragment.friendsRecyclerAdapter;
import exercises.blue.demoagain.singleActivities.dianming;
import exercises.blue.demoagain.singleActivities.publishActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, agenda.OnFragmentInteractionListener {

    ViewPager pager;
    public static String TAG = "inMain";
    //friendsDataSet set = friendsDataSet.newInstance();
    fragmentPagerAdapter adapter;
    friendsRecyclerAdapter mFriendsRecyclerAdapter;
    boolean isBackPressed = false;
//    fragmentFriends mFragmentFriends;
//    agenda mAgenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main_real);
        //加载Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
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
//            final View.OnClickListener clicked = new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i(TAG, "onClick: ?????????");
//                    Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//                }
//            };

//            myOnItemLongClickListener longClickListener = new myOnItemLongClickListener() {
//                @Override
//                public void onItemLongClick(View v, int position) {
//
//                    Snackbar.make(pager, "Hello world", Snackbar.LENGTH_LONG)
//                            .setAction("YES", clicked)
//                            .show();
//                }
//            };
//
//            myOnItemClickListener clickListener = new myOnItemClickListener() {
//                @Override
//                public void onItemClick(View v, int position) {
//                    adapter.getItem()
//                    Log.i(TAG, "onItemClick: ?????");
//                    Toast.makeText(MainActivity.this, "Hello world", Toast.LENGTH_SHORT).show();
//                }
//            };
//            adapter.setListeners(clickListener, longClickListener);
            pager.setAdapter(adapter);
        }


        /**
         * connect the TabView and the ViewPager
         */
        assert tabs != null;
        tabs.setupWithViewPager(pager);


        /**
         * useless .
         */
//        mFragmentFriends = (fragmentFriends) adapter.getFragment(0);
//        mAgenda = (agenda) adapter.getFragment(1);
//        mFriendsRecyclerAdapter = mFragmentFriends.getAdapter();


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
            if (!isBackPressed) {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                isBackPressed = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            isBackPressed = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).run();
                return;
            }
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

        if (item.getItemId() == R.id.showPref) {
            SharedPreferences preferences = getSharedPreferences(this.getString(R.string.sharedPreferenceName), MODE_PRIVATE);
            Set<String> strings = preferences.getAll().keySet();
            String all = "";
            for (String string : strings) {
                all += (string + preferences.getString(string, null));
            }
            Toast.makeText(MainActivity.this, all, Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.clearPref) {
            getSharedPreferences(getString(R.string.sharedPreferenceName), MODE_PRIVATE).edit().clear().commit();
        }else if (item.getItemId()==R.id.startCollection){
            startActivity(new Intent(this,ScrollingActivity.class));
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        switch (id) {
//            case R.id.test_add:
//                showItemActonDialog(R.id.test_add);
//                break;
//            case R.id.test_add_more:
//
//                break;
//            case R.id.test_remove_at_zero:
//                adapter.removeFriends(0);
//                break;
//            case R.id.test_remove_at:
//                showItemActonDialog(R.id.test_remove_at);
//                break;
//            case R.id.agenda_add:
//                showItemActonDialog(R.id.agenda_add);
//                break;
//            case R.id.agenda_remove:
//                showItemActonDialog(R.id.agenda_remove);
//                break;
//            case R.id.refresh_friends:
//                //adapter.refreshFriends();
//                adapter.clear();
//                break;
//
//            default:
//                break;

        //}

        //return super.onOptionsItemSelected(item);
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            if (mFriendsRecyclerAdapter != null) {

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
//            IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
//            intentIntegrator.initiateScan();
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


    /**
     * 其他Activity返回时回调.
     * 通过Floating ActionBar 添加新条目.
     * 现在已经不用了. 都是在fragment内添加,修改数据
     * @param requestCode requestCode没有使用.
     * @param resultCode  213 是新建用Activity确定并返回信息的resultCode.
     *                    444 是新建用Activity取消的resultCode.
     * @param data        数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 444 || resultCode == 0) return;
        if (requestCode == 213) {
            Toast.makeText(MainActivity.this, "废弃,别想了", Toast.LENGTH_SHORT).show();
        }

    }


    public void showItemActonDialog(int itemId) {

//        View input = getLayoutInflater().inflate(R.layout.number_input_layout, null);
//        final EditText etInput = (EditText) input.findViewById(R.id.input_number);
//        //final int[] position = {0};
//        //两个OnClickListener好像可以合并的样子
//       AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setIcon(R.mipmap.ic_launcher)
//                .setTitle("请输入位置")
//                .setView(input)
//                .setNegativeButton("取消",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//
//        switch (itemId){
//            case R.id.test_add:
//                builder.setTitle("添加功能已经废弃");
//                break;
//            case R.id.test_remove_at:
//                builder.setPositiveButton("删除",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            int position = Integer.parseInt(etInput.getText().toString());
//                            adapter.removeFriends(position);
//                        }
//                    });
//                break;
//            case R.id.agenda_add:
//                builder.setPositiveButton("添加",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                int position = Integer.parseInt(etInput.getText().toString());
//                                adapter.addAgenda(position);
//                            }
//                        });
//                break;
//            case R.id.agenda_remove:
//                builder.setPositiveButton("删除",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                int position = Integer.parseInt(etInput.getText().toString());
//                                adapter.removeAgenda(position);
//                            }
//                        });
//                break;
//            default: break;
//        }
//
//        builder.create().show();

    }

}
