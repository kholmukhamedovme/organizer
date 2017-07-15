package me.kholmukhamedov.organizer.ui.activity;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import me.kholmukhamedov.organizer.R;

public class CustomBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {
    private float pointerStartY, viewStartY, viewEndY;
    private View view;

    public CustomBottomSheetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, V child, MotionEvent event) {
        if (view == null) view = child.findViewById(R.id.main_bottom_sheet_fragment);

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        if (event.getAction() == MotionEvent.ACTION_MOVE && pointerStartY == 0) {
            pointerStartY = event.getY();
            viewStartY = location[1] - view.getTop();
            viewEndY = view.getBottom();
        }

        if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            pointerStartY = 0;
            viewStartY = 0;
            viewEndY = 0;
        }

        return pointerStartY > viewStartY && pointerStartY < viewEndY || super.onTouchEvent(parent, child, event);
    }
}
