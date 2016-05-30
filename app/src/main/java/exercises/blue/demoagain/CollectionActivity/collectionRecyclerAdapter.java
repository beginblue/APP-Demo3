package exercises.blue.demoagain.CollectionActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Set;

import exercises.blue.demoagain.R;

/**
 * Adapter for the recycler view in collection activity
 * Created by getbl on 2016/5/30.
 */
public class collectionRecyclerAdapter extends RecyclerView.Adapter<collectionRecyclerAdapter.myHolder> {

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private String[] mKeySet;
    public collectionRecyclerAdapter(Context context){
        mContext=context;
        mSharedPreferences=mContext.getSharedPreferences(mContext.getString(R.string.sharedPreferenceName),Context.MODE_PRIVATE);
        mKeySet=mSharedPreferences.getAll().keySet().toArray(new String[0]);
        Log.i("@@@???@?@?@?", "collectionRecyclerAdapter: "+mKeySet.length);
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.fav_item,null);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(myHolder holder, int position) {
        String title = mKeySet[position];
        Log.e("(╯‵□′)╯︵┻━┻", "onBindViewHolder: "+title+"="+mSharedPreferences.getString(title,""));
        holder.tv_title.setText(title);
        holder.tv_content.setText(mSharedPreferences.getString(title,""));
    }

    @Override
    public int getItemCount() {
        return mKeySet.length;
    }

    public class myHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{

        public TextView tv_title,tv_content;

        public myHolder(View itemView) {
            super(itemView);
            tv_title= (TextView) itemView.findViewById(R.id.fav_item_title);
            tv_content=(TextView) itemView.findViewById(R.id.fav_item_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String url = tv_content.getText().toString();

            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setData(Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
}
