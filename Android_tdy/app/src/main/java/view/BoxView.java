package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.administrator.android_tdy.R;

/**
 * Created by Administrator on 2016/7/7.
 */
public class BoxView extends View {
    public float bitmapX;
    public float bitmapY;
    public float width;
    public float height;
    public String text;
    public String color;
    public BoxView(Context context,float bitmapX,float bitmapY,float width,float height,String color) {
        super(context);
        bitmapX = bitmapX;
        bitmapY = bitmapY;
        width = 50;
        height = 50;
        text="";
        color="#000000";
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.box);
        canvas.drawBitmap(bitmap,bitmapX,bitmapY,paint);
        if(bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
