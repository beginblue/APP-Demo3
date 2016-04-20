package layout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import exercises.blue.demoagain.R;
import exercises.blue.demoagain.listAdapter;
import exercises.blue.userdata.dataSet;

/**
 * Created by getbl on 2016/4/18.
 */
public class fragmentFriends extends Fragment {
    listAdapter adapter;
    ListView mListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public listAdapter getAdapter() {
        return adapter;
    }

    public static fragmentFriends newInstance() {
        Bundle args = new Bundle();
        fragmentFriends fragment = new fragmentFriends();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new listAdapter();
        View view = inflater.inflate(R.layout.friends, container, false);
        mListView = (ListView) view.findViewById(R.id.list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog(position);
                //Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
            }
        });
//        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                showDialog(position);
//
//
//                return false;
//            }
//        });
        mListView.setAdapter(adapter);
        return view;
    }

    private void showDialog(final int position) {
        final int Position = position;
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
        builder.setTitle("提示").setIcon(R.drawable.welcome)
                .setMessage("真的要删除吗")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dataSet.newInstance().removeItem(Position);
                        adapter.remove(position);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }
}
