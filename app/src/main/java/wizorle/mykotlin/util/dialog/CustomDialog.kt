package wizorle.mykotlin.util.dialog

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import wizorle.mykotlin.R

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 */

class CustomDialog(context: Context?, theme: Int) : ProgressDialog(context, theme) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(context)
    }

    private fun init(context: Context?) {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        setContentView(R.layout.load_dialog)
        val params = window!!.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params
    }

    override fun show() {
        super.show()
    }

    override fun hide() {
        super.hide()
    }
}