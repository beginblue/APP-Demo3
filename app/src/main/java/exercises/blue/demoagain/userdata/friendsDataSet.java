package exercises.blue.demoagain.userdata;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

/**
 * friends的数据源
 * TODO:从网上下点数据来放到这里
 * TODO:初始值为0 在启动APP或者下拉刷新的时候加载数据
 * TODO:实现上面两点With Volley and Jackson
 * Created by getbl on 2016/4/20.
 */
public class friendsDataSet {

    private int curentPage = 0;


    ArrayList<friendsDatum> list;//= new ArrayList<>();
    static friendsDataSet instance;

    private friendsDataSet() {
        list = new ArrayList<>();
    }

    public static friendsDataSet newInstance() {
        if (instance == null) instance = new friendsDataSet();
        return instance;
    }


    public ArrayList<friendsDatum> getList() {
        return instance.list;
    }


    public void setList(List<friendsDatum> list) {
        //this.list = list;
        //this.list.addAll(list);
        this.list.addAll(list);
    }

    public void addItem(friendsDatum datum) {
        instance.list.add(datum);
    }

    public int getCurentPage() {
        return curentPage;
    }

    public void setCurentPage(int curentPage) {
        this.curentPage = curentPage;
    }
}
