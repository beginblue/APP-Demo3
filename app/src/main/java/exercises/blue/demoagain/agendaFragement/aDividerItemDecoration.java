package exercises.blue.demoagain.agendaFragement;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * The item decoration of agenda RecyclerView
 * Created by getbl on 2016/4/25.
 */
public class aDividerItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top=5;
        outRect.bottom=5;
        outRect.left=5;
        outRect.right=5;
    }
}
