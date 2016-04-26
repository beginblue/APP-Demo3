package exercises.blue.demoagain;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * agenda adapter
 * Created by getbl on 2016/4/25.
 */
public class agendaRecyclerAdapter extends RecyclerView.Adapter<agendaRecyclerAdapter.agendaHolder> {

    //private int[] pool = new int[25];

    private List<Integer> mIntegerList = new ArrayList<>();

    public agendaRecyclerAdapter(){
        for (int a = 0; a<25;a++){
            mIntegerList.add(a);
        }
    }

    public void removeItem(int position){
        notifyItemRemoved(position);
        int size = mIntegerList.size();
        if(size==0) return;
        if(position>=size) position=size-1;
        if(position<0) position=0;
        mIntegerList.remove(position);
    }

    public void addItem(int position){
        notifyItemInserted(position);
        int size = mIntegerList.size();
        if(position<0) position=0;
        if(position>size) mIntegerList.add(0);
        mIntegerList.add(position,0);
    }

    @Override
    public agendaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_item_layout,parent,false);
        agendaHolder holder = new agendaHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(agendaHolder holder, int position) {
        holder.title.setText("original item");
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    public class agendaHolder  extends  RecyclerView.ViewHolder{

        TextView title;
      public agendaHolder(View itemView) {
          super(itemView);
          title= (TextView) itemView.findViewById(R.id.agenda_item);
      }
  }

}
