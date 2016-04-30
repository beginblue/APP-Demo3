package exercises.blue.demoagain.agendaFragement;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import exercises.blue.demoagain.R;
import exercises.blue.demoagain.interfaces.myOnItemClickListener;
import exercises.blue.demoagain.interfaces.myOnItemLongClickListener;

/**
 * agenda adapter
 * Created by getbl on 2016/4/25.
 */
public class agendaRecyclerAdapter extends RecyclerView.Adapter<agendaRecyclerAdapter.agendaHolder> {
    private static final String TAG = "adapterAgenda";

    //private int[] pool = new int[25];

    private List<String> mStringList = new ArrayList<>();






    private myOnItemClickListener mOnItemClickListener;
    private myOnItemLongClickListener mOnItemLongClickListener;


    public agendaRecyclerAdapter(myOnItemClickListener clickListener,myOnItemLongClickListener longClickListener) {

        mOnItemClickListener=clickListener;
        mOnItemLongClickListener=longClickListener;
        for (int a = 0; a < 25; a++) {
            StringBuilder builder = generateRandomString();
            mStringList.add(builder.toString());
        }
    }

    @NonNull
    private StringBuilder generateRandomString() {
        StringBuilder builder = new StringBuilder("what are they");
        for (int count = 0; count < (int) (Math.random() * 20); count++) {
            builder.append("Hello world");
        }
        return builder;
    }

    public void removeItem(int position) {
        notifyItemRemoved(position);
        int size = mStringList.size();
        if (size == 0) return;
        if (position >= size) return;
        if (position < 0) position = 0;
        try {
            mStringList.remove(position);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addItem(int position) {
        notifyItemInserted(position);
        int size = mStringList.size();
        if (position < 0) position = 0;
        if (position > size) {
            mStringList.add(generateRandomString().toString());
            return;
        }
        mStringList.add(position, generateRandomString().toString());
    }

    @Override
    public agendaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_item_layout, parent, false);
        //view.setOnClickListener();

        agendaHolder holder = new agendaHolder(view,mOnItemClickListener,mOnItemLongClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(agendaHolder holder, int position) {
        try {
            holder.title.setText(mStringList.get(position));
        }catch (Exception e){
            Log.w(TAG, "onBindViewHolder:"+e.getLocalizedMessage() );
        }
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }

    public class agendaHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        RelativeLayout shell;
        TextView title;
        myOnItemClickListener onItemClickListener;
        myOnItemLongClickListener onItemLongClickListener;


        public agendaHolder(View itemView, myOnItemClickListener itemClickListener, myOnItemLongClickListener itemLongClickListener) {
            super(itemView);
            shell = (RelativeLayout) itemView.findViewById(R.id.agenda_item_shell);
            title = (TextView) itemView.findViewById(R.id.agenda_item);

            onItemClickListener = itemClickListener;
            onItemLongClickListener = itemLongClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(v, this.getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemLongClickListener != null)
                onItemLongClickListener.onItemLongClick(v, getLayoutPosition());
            return true;
        }
    }

}
