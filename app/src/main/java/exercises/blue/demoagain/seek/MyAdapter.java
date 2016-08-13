package exercises.blue.demoagain.seek;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exercises.blue.demoagain.R;

/**
 * Created by blue on 16-6-15.
 */
public class
MyAdapter extends BaseAdapter{
    private  List<seekResponse.ResultsBean> mList;              //数据源与数据适配器进行关联


    public void addAll(List<seekResponse.ResultsBean> resultsBeanList){
        mList.addAll(resultsBeanList);
        notifyDataSetChanged();
    }


    public void removeAll(){
        mList=new LinkedList<>();
        System.gc();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return null;
        // 返回每一项的显示内容
        final Context context = parent.getContext();
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(context,R.layout.item,null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   seekResponse.ResultsBean itemBean = mList.get(position);
                    String url = itemBean.getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                }
            });
            viewHolder.title=(TextView)convertView.findViewById(R.id.tv_title);
            viewHolder.content=(TextView)convertView.findViewById(R.id.tv_content);
            viewHolder.mWebView=(WebView) convertView.findViewById(R.id.webView);
            convertView.setTag(viewHolder);     //创建新建的convertview和viewholder的关联
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        seekResponse.ResultsBean bean=mList.get(position);
        viewHolder.mWebView.loadDataWithBaseURL(null,"<html><body<"+bean.getReadability()+"</body></html>","text/html","utf-8",null);
        viewHolder.title.setText(bean.getDesc());
        viewHolder.content.setText(bean.getUrl());
        return convertView;
        }
    class ViewHolder {                     //避免重复的findviewbyid
        public TextView title;
        public TextView content;
        public WebView mWebView;
    }
}
