package io.bsu.mmf.helpme.baseAndroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.bsu.mmf.helpme.baseAndroid.R;

public class ProgressItemDecoration extends RecyclerView.ItemDecoration {

    private Context context;
    private Paint circlePaint;
    private Paint linePaint;
    private ArrayList<String> dateList;
    private int radius;
    private int curPosition = 0;   // the current ongoing position

    public ProgressItemDecoration(Context context ) {
        this.context = context;
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        circlePaint.setStyle(Paint.Style.FILL);
        radius = dp2Px(8);
        circlePaint.setStrokeWidth(dp2Px(2));


        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        linePaint.setStrokeWidth(dp2Px(2));

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = dp2Px(20);
        outRect.left = dp2Px(50);
        //outRect.right = dp2Px(80);

    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        int childCount = parent.getChildCount();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int leftDecorationWidth = layoutManager.getLeftDecorationWidth(childView);
            int topDecorationHeight = layoutManager.getTopDecorationHeight(childView);
            // Get the current item is the first childview of the recyclerview
            int childLayoutPosition = parent.getChildLayoutPosition(childView);
            float startX = leftDecorationWidth / 2;
            float stopX = startX;
            // Part of the vertical line of the dome, starting point Y
            float topStartY = childView.getTop() - topDecorationHeight;
            // Part of the vertical line of the dome, the end point Y
            float topStopY = childView.getTop() + childView.getHeight() / 2 - radius;

            // round bottom part of the vertical line, starting point Y
            float bottomStartY = childView.getTop() + childView.getHeight() / 2 + radius;
            // round bottom part vertical line, end point Y
            float bottomStopY = childView.getBottom();

            // When the position exceeds curPosition, the vertical line color is set to light
            if (childLayoutPosition > curPosition) {
                linePaint.setColor(context.getResources().getColor(R.color.colorPrimaryVariant));
                circlePaint.setColor(context.getResources().getColor(R.color.colorPrimaryVariant));
                circlePaint.setStyle(Paint.Style.STROKE);
            } else {
                linePaint.setColor(context.getResources().getColor(R.color.colorPrimary));
                circlePaint.setColor(context.getResources().getColor(R.color.colorPrimary));
                circlePaint.setStyle(Paint.Style.FILL);
            }

            // draw a circle
            if (childLayoutPosition == curPosition) {
                circlePaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(leftDecorationWidth / 2, childView.getTop() + childView.getHeight() / 2, dp2Px(2), circlePaint);
            }
            canvas.drawCircle(leftDecorationWidth / 2, childView.getTop() + childView.getHeight() / 2, radius, circlePaint);

            // draw a vertical line, only need to draw the lower part at the 0th position
            if (childLayoutPosition == 0) {
                // When the current item position = curPosition, the color is set to light when the lower half of the vertical line is drawn
                if (childLayoutPosition == curPosition) {
                    linePaint.setColor(context.getResources().getColor(R.color.colorPrimaryVariant));
                }
                canvas.drawLine(startX, bottomStartY, startX, bottomStopY, linePaint);
                // In the last position, just draw the top half
            } else if (childLayoutPosition == parent.getAdapter().getItemCount() - 1) {
                canvas.drawLine(startX, topStartY, startX, topStopY, linePaint);
            } else {
                // draw all
                canvas.drawLine(startX, topStartY, startX, topStopY, linePaint);
                // When the current item position = curPosition, the color is set to light when the lower half of the vertical line is drawn
                if (childLayoutPosition == curPosition) {
                    linePaint.setColor(context.getResources().getColor(R.color.colorPrimaryVariant));
                }
                canvas.drawLine(startX, bottomStartY, startX, bottomStopY, linePaint);
            }
        }

    }

    /**
     * Set the location in progress
     *
     * @param recyclerView
     * @param position
     */
    public void setDoingPosition(RecyclerView recyclerView, int position) {
        if (recyclerView == null) {
            throw new IllegalArgumentException("RecyclerView can't be null");
        }
        if (recyclerView.getAdapter() == null) {
            throw new IllegalArgumentException("RecyclerView Adapter can't be null");
        }
        if (position < 0) {
            throw new IllegalArgumentException("position can't be less than 0");
        }
        recyclerView.getLayoutManager().getItemCount();
        if (position > recyclerView.getAdapter().getItemCount() - 1) {
            throw new IllegalArgumentException("position can't be greater than item count");
        }
        this.curPosition = position;
    }

    private int dp2Px(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
