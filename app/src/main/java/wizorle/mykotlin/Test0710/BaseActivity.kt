package wizorle.mykotlin.Test0710

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager


/**
 * Created by EdgeDi
 * 2018/3/15 10:25
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getTransparent()) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
//        if (getStatus()) {
//            setBarColor(getColor())
//        } else {
//            setImageColor()
//        }
        MagicBind.inject(this)
        initGaoDe(savedInstanceState)
        initUI()
        setListener()
    }

    open fun getTransparent() = false

    open fun initGaoDe(savedInstanceState: Bundle?) {

    }

    abstract fun initUI()

    abstract fun setListener()

    protected open fun getColor() = Color.parseColor("#080808")

    /**
     * true为颜色
     * false为图片
     */
    protected open fun getStatus() = true
}