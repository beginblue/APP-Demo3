package exercises.blue.demoagain.userdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * friends的数据源
 * TODO:从网上下点数据来放到这里
 * TODO:初始值为0 在启动APP或者下拉刷新的时候加载数据
 * TODO:实现上面两点With Volley and Jackson
 * JACKSON似乎用不到
 * 以及想个办法给不同组数据存放在不同List中.
 * 代码冗余!!!
 *
 *
 * Created by getbl on 2016/4/20.
 */
public class friendsDataSet {

    private static Map<String,List<friendsDatum>> mDataPool;

    static friendsDataSet instance;

    private friendsDataSet() {
        /*福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all*/
        mDataPool= new HashMap<>();
        mDataPool.put("Android",new ArrayList<friendsDatum>());
        mDataPool.put("iOS",new ArrayList<friendsDatum>());
        mDataPool.put("前端",new ArrayList<friendsDatum>());

    }

    public static friendsDataSet newInstance() {
        if (instance == null) instance = new friendsDataSet();
        return instance;
    }


    public ArrayList<friendsDatum> getList(String category) {
        return (ArrayList<friendsDatum>) mDataPool.get(category);
    }


    public void setList(String category, List<friendsDatum> list) {
        //this.list = list;
        //this.list.addAll(list);
       mDataPool.get(category).addAll(list);
    }

    public void addItem(String category,friendsDatum datum) {
        mDataPool.get(category).add(datum);
    }


}
