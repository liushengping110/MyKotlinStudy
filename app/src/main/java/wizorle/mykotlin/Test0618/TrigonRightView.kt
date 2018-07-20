package wizorle.mykotlin.Test0618

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import wizorle.mykotlin.R
import android.R.attr.y
import android.R.attr.x



/**
 * Created by 何人执笔？ on 2018/6/19.
 * liushengping
 */
class TrigonRightView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val p = Paint()
        p.setColor(R.color.colorAccent)
        //实例化路径
        val path = Path()
        path.moveTo(200f, 0f)// 此点为多边形的起点
        path.lineTo(0f, 250f)
        path.lineTo(200f, 250f)
        path.close() // 使这些点构成封闭的多边形
        canvas?.drawPath(path, p)

    }

}