package exercises.blue.userdata;

import java.util.ArrayList;

/**
 * Created by getbl on 2016/4/20.
 */
public class friendsDataSet {
    ArrayList<friendsDatum> list ;//= new ArrayList<>();
    static friendsDataSet instance;

    private friendsDataSet(){
        list = new ArrayList<>();
    }

    public static friendsDataSet newInstance(){
        if(instance == null) instance = new friendsDataSet();
        return instance;
    }

//    public friendsDatum[] getList() {
//        return list.toArray(new friendsDatum[]{});
//    }

    public ArrayList<friendsDatum> getList(){
        return instance.list;
    }

//    public void removeItem(int position){
//        this.list.remove(position);
//
//    }

    public void setList(ArrayList<friendsDatum> list) {
        this.list = list;
    }

    public void addItem(friendsDatum datum){
        instance.list.add(datum);
    }
}
