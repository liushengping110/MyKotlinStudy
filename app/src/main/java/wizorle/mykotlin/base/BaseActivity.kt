package wizorle.mykotlin.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast

/**
 * Created by 何人执笔？ on 2018/6/11.
 * liushengping
 */
abstract class BaseActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(getLayout())
    }
    override fun onContentChanged() {
        super.onContentChanged()
        init()
        setListener()
    }

    abstract fun getLayout():Int


    abstract fun init()


    abstract fun setListener()


    fun showToast(string: String, time: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, string, time).show()

    fun skip(clazz: Class<*>) {
        startActivity(Intent(this,clazz))
    }

    protected fun Steep() {
        if (Build.VERSION.SDK_INT >= 19) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)//透明状态栏
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
    }
}