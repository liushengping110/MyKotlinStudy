package wizorle.mykotlin.Test0618;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;


/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.cornerslibrary.RoundCornersTransformation
 *     e-mail : 1012126908@qq.com
 *     time   : 2017/08/18
 *     desc   : 根据需求，对图片定制指定的圆角
 * </pre>
 */

public class RoundCornersTransformation implements com.bumptech.glide.load.Transformation<Bitmap> {


    public enum CornerType {
        ALL,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
        TOP, BOTTOM, LEFT, RIGHT,
        OTHER_TOP_LEFT, OTHER_TOP_RIGHT, OTHER_BOTTOM_LEFT, OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT, DIAGONAL_FROM_TOP_RIGHT
    }

    private BitmapPool mBitmapPool;
    private int mRadius;
    private int mDiameter;
    private int mMargin;
    private CornerType mCornerType;

    public RoundCornersTransformation(Context context, int radius, int margin) {
        this(context, radius, margin, CornerType.ALL);
    }

    public RoundCornersTransformation(BitmapPool pool, int radius, int margin) {
        this(pool, radius, margin, CornerType.ALL);
    }

    public RoundCornersTransformation(Context context, int radius, int margin,
                                        CornerType cornerType) {
        this(Glide.get(context).getBitmapPool(), radius, margin, cornerType);
    }

    public RoundCornersTransformation(BitmapPool pool, int radius, int margin,
                                        CornerType cornerType) {
        mBitmapPool = pool;
        mRadius = radius;
        mDiameter = mRadius * 2;
        mMargin = margin;
        mCornerType = cornerType;
    }

    @Override
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();

        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap bitmap = mBitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setColor(Color.BLUE);

        paint.setStrokeWidth(5);
        drawRoundRect(canvas, paint, width, height);
        return BitmapResource.obtain(bitmap, mBitmapPool);
    }

    private void drawRoundRect(Canvas canvas, Paint paint, float width, float height) {
        float right = width - mMargin;
        float bottom = height - mMargin;

        switch (mCornerType) {
            case ALL:

                canvas.drawRoundRect(new RectF(mMargin, mMargin, right, bottom), mRadius, mRadius, paint);
                break;
            case TOP_LEFT:
                drawTopLeftRoundRect(canvas, paint, right, bottom);
                break;
            case TOP_RIGHT:
                drawTopRightRoundRect(canvas, paint, right, bottom);
                break;
            case BOTTOM_LEFT:
                drawBottomLeftRoundRect(canvas, paint, right, bottom);
                break;
            case BOTTOM_RIGHT:
                drawBottomRightRoundRect(canvas, paint, right, bottom);
                break;
            case TOP:
                drawTopRoundRect(canvas, paint, right, bottom);
                break;
            case BOTTOM:
                drawBottomRoundRect(canvas, paint, right, bottom);
                break;
            case LEFT:
                drawLeftRoundRect(canvas, paint, right, bottom);
                break;
            case RIGHT:
                drawRightRoundRect(canvas, paint, right, bottom);
                break;
            case OTHER_TOP_LEFT:
                drawOtherTopLeftRoundRect(canvas, paint, right, bottom);
                break;
            case OTHER_TOP_RIGHT:
                drawOtherTopRightRoundRect(canvas, paint, right, bottom);
                break;
            case OTHER_BOTTOM_LEFT:
                drawOtherBottomLeftRoundRect(canvas, paint, right, bottom);
                break;
            case OTHER_BOTTOM_RIGHT:
                drawOtherBottomRightRoundRect(canvas, paint, right, bottom);
                break;
            case DIAGONAL_FROM_TOP_LEFT:
                drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom);
                break;
            case DIAGONAL_FROM_TOP_RIGHT:
                drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom);
                break;
            default:
                canvas.drawRoundRect(new RectF(mMargin, mMargin, right, bottom), mRadius, mRadius, paint);
                break;
        }
    }

    private void drawTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, mMargin, mMargin + mDiameter, mMargin + mDiameter),
                mRadius, mRadius, paint);
        canvas.drawRect(new RectF(mMargin, mMargin + mRadius, mMargin + mRadius, bottom), paint);
        canvas.drawRect(new RectF(mMargin + mRadius, mMargin, right, bottom), paint);
    }

    private void drawTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - mDiameter, mMargin, right, mMargin + mDiameter), mRadius,
                mRadius, paint);
        canvas.drawRect(new RectF(mMargin, mMargin, right - mRadius, bottom), paint);
        canvas.drawRect(new RectF(right - mRadius, mMargin + mRadius, right, bottom), paint);
    }

    private void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, bottom - mDiameter, mMargin + mDiameter, bottom),
                mRadius, mRadius, paint);
        canvas.drawRect(new RectF(mMargin, mMargin, mMargin + mDiameter, bottom - mRadius), paint);
        canvas.drawRect(new RectF(mMargin + mRadius, mMargin, right, bottom), paint);
    }

    private void drawBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - mDiameter, bottom - mDiameter, right, bottom), mRadius,
                mRadius, paint);
        canvas.drawRect(new RectF(mMargin, mMargin, right - mRadius, bottom), paint);
        canvas.drawRect(new RectF(right - mRadius, mMargin, right, bottom - mRadius), paint);
    }

    private void drawTopRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, mMargin, right, mMargin + mDiameter), mRadius, mRadius,
                paint);
        canvas.drawRect(new RectF(mMargin, mMargin + mRadius, right, bottom), paint);
    }

    private void drawBottomRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, bottom - mDiameter, right, bottom), mRadius, mRadius,
                paint);
        canvas.drawRect(new RectF(mMargin, mMargin, right, bottom - mRadius), paint);
    }

    private void drawLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, mMargin, mMargin + mDiameter, bottom), mRadius, mRadius,
                paint);
        canvas.drawRect(new RectF(mMargin + mRadius, mMargin, right, bottom), paint);
    }

    private void drawRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - mDiameter, mMargin, right, bottom), mRadius, mRadius,
                paint);
        canvas.drawRect(new RectF(mMargin, mMargin, right - mRadius, bottom), paint);
    }

    private void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, bottom - mDiameter, right, bottom), mRadius, mRadius,
                paint);
        canvas.drawRoundRect(new RectF(right - mDiameter, mMargin, right, bottom), mRadius, mRadius,
                paint);
        canvas.drawRect(new RectF(mMargin, mMargin, right - mRadius, bottom - mRadius), paint);
    }

    private void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, mMargin, mMargin + mDiameter, bottom), mRadius, mRadius,
                paint);
        canvas.drawRoundRect(new RectF(mMargin, bottom - mDiameter, right, bottom), mRadius, mRadius,
                paint);
        canvas.drawRect(new RectF(mMargin + mRadius, mMargin, right, bottom - mRadius), paint);
    }

    private void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, mMargin, right, mMargin + mDiameter), mRadius, mRadius,
                paint);
        canvas.drawRoundRect(new RectF(right - mDiameter, mMargin, right, bottom), mRadius, mRadius,
                paint);
        canvas.drawRect(new RectF(mMargin, mMargin + mRadius, right - mRadius, bottom), paint);
    }

    private void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float right,
                                               float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, mMargin, right, mMargin + mDiameter), mRadius, mRadius,
                paint);
        canvas.drawRoundRect(new RectF(mMargin, mMargin, mMargin + mDiameter, bottom), mRadius, mRadius,
                paint);
        canvas.drawRect(new RectF(mMargin + mRadius, mMargin + mRadius, right, bottom), paint);
    }

    private void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float right,
                                                  float bottom) {
        canvas.drawRoundRect(new RectF(mMargin, mMargin, mMargin + mDiameter, mMargin + mDiameter),
                mRadius, mRadius, paint);
        canvas.drawRoundRect(new RectF(right - mDiameter, bottom - mDiameter, right, bottom), mRadius,
                mRadius, paint);
        canvas.drawRect(new RectF(mMargin, mMargin + mRadius, right - mDiameter, bottom), paint);
        canvas.drawRect(new RectF(mMargin + mDiameter, mMargin, right, bottom - mRadius), paint);
    }

    private void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float right,
                                                   float bottom) {
        canvas.drawRoundRect(new RectF(right - mDiameter, mMargin, right, mMargin + mDiameter), mRadius,
                mRadius, paint);
        canvas.drawRoundRect(new RectF(mMargin, bottom - mDiameter, mMargin + mDiameter, bottom),
                mRadius, mRadius, paint);
        canvas.drawRect(new RectF(mMargin, mMargin, right - mRadius, bottom - mRadius), paint);
        canvas.drawRect(new RectF(mMargin + mRadius, mMargin + mRadius, right, bottom), paint);
    }

    @Override public String getId() {
        return "RoundedTransformation(radius=" + mRadius + ", margin=" + mMargin + ", diameter="
                + mDiameter + ", cornerType=" + mCornerType.name() + ")";
    }
//    }
//    /**
//     * 画左下角
//     */
//    private void drawLeftBottomCorner(Canvas canvas, Paint paint, float width, float height) {
//        canvas.drawRect(new RectF(0, 0, width, height - mRadius), paint);
//        canvas.drawRect(new RectF(mRadius, height - mRadius, width, height), paint);
//        canvas.drawArc(new RectF(0, height - mDiameter, mDiameter, height), 90, 90, true, paint);
//    }
//
//    /**
//     * 画右上角
//     */
//    private void drawRightTopCorner(Canvas canvas, Paint paint, float width, float height) {
//        canvas.drawRect(new RectF(0, 0, width - mRadius, height), paint);
//        canvas.drawRect(new RectF(width - mRadius, mRadius, width, height), paint);
//        canvas.drawArc(new RectF(width - mDiameter, 0, width, mDiameter), 270, 90, true, paint);
//    }
//
//    /**
//     * 画右下角
//     */
//    private void drawRightBottomCorner(Canvas canvas, Paint paint, float width, float height) {
//        canvas.drawRect(new RectF(0, 0, width, height - mRadius), paint);
//        canvas.drawRect(new RectF(0, height - mRadius, width - mRadius, height), paint);
//        canvas.drawArc(new RectF(width - mDiameter, height - mDiameter, width, height), 0, 90, true, paint);
//    }
//
//    /**
//     * 画左 角
//     */
//    private void drawLeftCorner(Canvas canvas, Paint paint, float width, float height) {
//        canvas.drawRect(new RectF(mRadius, 0, width, height), paint);
//        canvas.drawRect(new RectF(0, mRadius, mRadius, height - mRadius), paint);
//        canvas.drawArc(new RectF(0, 0, mDiameter, mDiameter), 180, 90, true, paint);
//        canvas.drawArc(new RectF(0, height - mDiameter, mDiameter, height), 90, 90, true, paint);
//    }
//
//    /**
//     * 画右角
//     */
//    private void drawRightCorner(Canvas canvas, Paint paint, float width, float height) {
//        canvas.drawRect(new RectF(0, 0, width - mRadius, height), paint);
//        canvas.drawRect(new RectF(width - mRadius, mRadius, width, height - mRadius), paint);
//        canvas.drawArc(new RectF(width - mDiameter, 0, width, mDiameter), 270, 90, true, paint);
//        canvas.drawArc(new RectF(width - mDiameter, height - mDiameter, width, height), 0, 90, true, paint);
//    }
//
//    /**
//     * 画上 角
//     */
//    private void drawTopCorner(Canvas canvas, Paint paint, float width, float height) {
//        canvas.drawRect(new RectF(0, mRadius, width, height), paint);
//        canvas.drawRect(new RectF(mRadius, 0, width - mRadius, mRadius), paint);
//        canvas.drawArc(new RectF(0, 0, mDiameter, mDiameter), 180, 90, true, paint);
//        canvas.drawArc(new RectF(width - mDiameter, 0, width, mDiameter), 270, 90, true, paint);
//    }
//
//    /**
//     * 画下 角
//     */
//    private void drawBottomCorner(Canvas canvas, Paint paint, float width, float height) {
//        canvas.drawRect(new RectF(0, 0, width, height - mRadius), paint);
//        canvas.drawRect(new RectF(mRadius, height - mRadius, width - mRadius, height), paint);
//        canvas.drawArc(new RectF(0, height - mDiameter, mDiameter, height), 90, 90, true, paint);
//        canvas.drawArc(new RectF(width - mDiameter, height - mDiameter, width, height), 0, 90, true, paint);
//    }





}

