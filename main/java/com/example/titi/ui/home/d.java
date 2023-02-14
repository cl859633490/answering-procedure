package com.example.titi.ui.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public  class d extends View {
    public d(Context context) {
        super(context);
    }
@Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(0xFFFF00);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(10,10,100,100,paint);
    }
}
