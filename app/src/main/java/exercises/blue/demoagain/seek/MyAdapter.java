package exercises.blue.demoagain.seek;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import exercises.blue.demoagain.R;

/**
 * Created by blue on 16-6-15.
 */
public class MyAdapter extends BaseAdapter{
    private  List<ItemBean> mList;              //数据源与数据适配器进行关联
    public void addAll(List<ItemBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public MyAdapter(){
        mList=new ArrayList<>();
    }


    @Override
    public int getCount() {                   //返回ListView需要显示的数据量
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {          //返回指定索引对应的数据项
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;                           //返回每一项的显示内容
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(parent.getContext(),R.layout.item,null);
            viewHolder.imageView=(ImageView)convertView.findViewById(R.id.iv_image);
            viewHolder.title=(TextView)convertView.findViewById(R.id.tv_title);
            viewHolder.content=(TextView)convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);     //创建新建的convertview和viewholder的关联
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ItemBean bean=mList.get(position);
        viewHolder.imageView.setImageResource(bean.ItemImageResid);
        viewHolder.title.setText(bean.ItemTitle);
        viewHolder.content.setText(bean.ItemContent);
        return convertView;
        }
    class ViewHolder {                     //避免重复的findviewbyid
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }
}
