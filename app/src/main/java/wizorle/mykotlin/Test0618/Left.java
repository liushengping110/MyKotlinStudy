package wizorle.mykotlin.Test0618;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import wizorle.mykotlin.R;

/**
 * Created by 何人执笔？ on 2018/6/19.
 * liushengping
 */

public class Left extends View {

    public Left(Context context) {
        super(context);
    }

    public Left(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Left(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        //实例化路径
        Path path = new Path();
        path.moveTo(0, 0);// 此点为多边形的起点
        path.lineTo(440, 0);
        path.lineTo(400, 120);
        path.lineTo(0, 120);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, p);

    }

}
