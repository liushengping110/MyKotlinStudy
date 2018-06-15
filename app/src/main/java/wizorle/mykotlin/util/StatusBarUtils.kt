package com.weihuo.weihuo.util

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.view.WindowManager

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 * 改变状态栏颜色
 */

object StatusBarUtils {
    fun setWindowStatusBarColor(activity: Activity, colorResId: Int) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = activity.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = activity.resources.getColor(colorResId)
                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun setWindowStatusBarColor(dialog: Dialog, colorResId: Int) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = dialog.window
                window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = dialog.context.resources.getColor(colorResId)

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}