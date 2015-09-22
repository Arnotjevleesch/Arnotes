package com.arnotjevleesch.arnotes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.arnotjevleesch.arnotes.pojo.GraphicalNote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CanvasView extends View{

    private Paint mPaint;
    private List<List<Float>> listPoints = new ArrayList<List<Float>>();

    public CanvasView(Context c, AttributeSet attrs){
        super(c,attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#CD5C5C"));
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Bitmap mBitmap= Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(List<Float> xy : listPoints ){
            canvas.drawCircle(xy.get(0),xy.get(1),20,mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(MotionEvent.ACTION_UP == event.getAction()) {
            List<Float> xy = new ArrayList<Float>();
            xy.add(event.getX());
            xy.add(event.getY());
            listPoints.add(xy);
            invalidate();
        }
        return true;
    }


    public List<GraphicalNote> getGraphicalNoteList() {
        List<GraphicalNote> res = new ArrayList<GraphicalNote>();
        for(List<Float> xy : listPoints ){
            // negative because the 0 is the up right corner
            res.add(new GraphicalNote(
                    BigDecimal.valueOf(xy.get(0)),BigDecimal.valueOf(xy.get(1)).negate()));
        }
        return res;
    }

    public void clear(){
        listPoints.clear();
        invalidate();
    }
}
