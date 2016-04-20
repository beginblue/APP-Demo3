package exercises.blue.demoagain;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import exercises.blue.userdata.dataSet;
import exercises.blue.userdata.userDatum;

/**
 * Created by getbl on 2016/4/20.
 */
public class listAdapter extends BaseAdapter  {

    private static final String TAG = "ERROR";
    dataSet mDataSet = dataSet.newInstance();


    public void add(userDatum datum){
        mDataSet.addItem(datum);
        notifyDataSetChanged();
    }

    public void remove(int position){
        mDataSet.removeItem(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataSet.getList().length;
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.getList()[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View.inflate(null,R.layout.listitem,null);

        if(mDataSet.getList().length==0) return null;
        for(int count = 0 ; count<mDataSet.getList().length;count++) {
            Log.e(TAG, "getView:" + mDataSet.getList()[count].getTitle()+"count"+count);
        }
        final View inflatedView;
        if(convertView==null) { //view = View.inflate()
            inflatedView = View.inflate(parent.getContext(), R.layout.listitem, null);
        }else{
            inflatedView = convertView;
        }

        TextView title = (TextView) inflatedView.findViewById(R.id.item_title);
        TextView content = (TextView) inflatedView.findViewById(R.id.item_content);
        TextView category = (TextView) inflatedView.findViewById(R.id.item_category);
        //Log.e(TAG, "getView: "+position );
        Log.e(TAG, "getView: "+getCount() );
        title.setText(mDataSet.getList()[position].getTitle());
        content.setText(mDataSet.getList()[position].getContent());
        category.setText(mDataSet.getList()[position].getCategory()==1?"热门回答":"");
        //notifyDataSetChanged();
        return inflatedView;
    }

}
