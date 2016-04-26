package exercises.blue.demoagain;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * 据说可以让items分开
 * Created by getbl on 2016/4/25.
 */
public class fDividerItemDecoration extends RecyclerView.ItemDecoration
{
    private  int space;
    public fDividerItemDecoration(int space){
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top=space;
    }
}
