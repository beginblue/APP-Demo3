package exercises.blue.demoagain.agendaFragement;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import exercises.blue.demoagain.R;

/**
 * agenda adapter
 * Created by getbl on 2016/4/25.
 */
public class agendaRecyclerAdapter extends RecyclerView.Adapter<agendaRecyclerAdapter.agendaHolder> {

    //private int[] pool = new int[25];

    private List<String> mStringList = new ArrayList<>();


    public agendaRecyclerAdapter() {
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
        mStringList.remove(position);
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
        agendaHolder holder = new agendaHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(agendaHolder holder, int position) {
        holder.title.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    public class agendaHolder extends RecyclerView.ViewHolder {
        RelativeLayout shell;
        TextView title;

        public agendaHolder(View itemView) {
            super(itemView);
            shell = (RelativeLayout) itemView.findViewById(R.id.agenda_item_shell);
            title = (TextView) itemView.findViewById(R.id.agenda_item);
        }
    }

}
