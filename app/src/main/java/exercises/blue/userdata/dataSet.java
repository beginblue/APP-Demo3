package exercises.blue.userdata;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by getbl on 2016/4/20.
 */
public class dataSet {
    ArrayList<userDatum> list ;//= new ArrayList<>();
    private static final String TAG = "dataSet";
    static dataSet instance;

    private dataSet(){
        list = new ArrayList<>();
    }

    public static dataSet newInstance(){
        if(instance == null) instance = new dataSet();
        return instance;
    }

    public userDatum[] getList() {
        return list.toArray(new userDatum[]{});
    }

    public void removeItem(int position){
        if(list.get(position)==null) {
            for(userDatum datum : instance.list){
                Log.e(TAG, "removeItem:"+datum.getTitle());
            }

            return;
        }
            //Toast.makeText(, "what", Toast.LENGTH_SHORT).show();
        instance.list.remove(position);

    }

    public void setList(ArrayList<userDatum> list) {
        this.list = list;
    }


    public void addItem(userDatum datum, int position){
        instance.list.add(position,datum);
    }

    public void addItem(userDatum datum){
        instance.list.add(datum);
    }
}
