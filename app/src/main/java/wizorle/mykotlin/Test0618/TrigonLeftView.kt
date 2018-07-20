package wizorle.mykotlin.Test0618

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import wizorle.mykotlin.R

/**
 * Created by 何人执笔？ on 2018/6/19.
 * liushengping
 */
class TrigonLeftView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val p = Paint()
        p.setColor(R.color.lanse)
        //实例化路径
        val path = Path()
        path.moveTo(0f, 0f)// 此点为多边形的起点
        path.lineTo(200f, 0f)
        path.lineTo(0f, 250f)
        path.close() // 使这些点构成封闭的多边形
        canvas?.drawPath(path, p)

    }
}