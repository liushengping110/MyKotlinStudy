package wizorle.mykotlin.util.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import wizorle.mykotlin.R

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 */

object LoadDialog{

    fun  showDialog(context:Context,msg:String):Dialog{
        var inflater = LayoutInflater.from(context);
        var v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        var layout = v?.findViewById(R.id.dialog_loading_view) as LinearLayout;// 加载布局
        var tipTextView = v?.findViewById(R.id.tipTextView) as TextView;// 提示文字
        tipTextView.setText(msg);// 设置加载信息
        var loadingDialog =  Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(false); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout,  LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        var window = loadingDialog.getWindow();
        var lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        return loadingDialog;
    }


    /**
     * 关闭dialog
     */
    fun  closeDialog( dialog: Dialog?) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}