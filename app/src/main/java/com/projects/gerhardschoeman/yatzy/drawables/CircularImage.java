package com.projects.gerhardschoeman.yatzy.drawables;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Gerhard on 25/11/2015.
 */
public class CircularImage extends ImageView {

    private static final String LOGTAG = CircularImage.class.getSimpleName();

    public CircularImage(Context context) {
        super(context);
    }

    public CircularImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        int w = getWidth();
        int h = getHeight();
        if (w == 0 || h == 0) {
            return;
        }

        Bitmap bmOut = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas bmCanvas = new Canvas(bmOut);
        drawable.setBounds(0,0,w,h);
        drawable.draw(bmCanvas);

        // Load the bitmap as a shader to the paint.
        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final Shader shader = new BitmapShader(bmOut, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        // Draw a circle with the required radius.
        final float halfWidth = canvas.getWidth()/2;
        final float halfHeight = canvas.getHeight()/2;
        final float radius = Math.max(halfWidth, halfHeight);
        canvas.drawCircle(halfWidth, halfHeight, radius, paint);
    }
}
