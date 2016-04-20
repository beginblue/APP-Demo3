package exercises.blue.userdata;

import java.util.ArrayList;

/**
 * Created by getbl on 2016/4/20.
 */
public class dataSet {
    ArrayList<userDatum> list ;//= new ArrayList<>();
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
        this.list.remove(position);

    }

    public void setList(ArrayList<userDatum> list) {
        this.list = list;
    }

    public void addItem(userDatum datum){
        instance.list.add(datum);
    }
}
