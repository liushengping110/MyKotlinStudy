package wizorle.mykotlin.Test0611

import android.app.Dialog
import android.os.SystemClock
import android.widget.Toast
import com.weihuo.weihuo.util.StatusBarUtils
import kotlinx.android.synthetic.main.activity_netdata.*
import wizorle.mykotlin.R
import wizorle.mykotlin.Test0608.Bean
import wizorle.mykotlin.Test0611.bean.WxMessage
import wizorle.mykotlin.base.BaseActivity
import wizorle.mykotlin.util.dialog.CustomDialog
import wizorle.mykotlin.util.dialog.LoadDialog
import wizorle.mypointbroadcast.HttpUtil

/**
 * Created by 何人执笔？ on 2018/6/11.
 * liushengping
 */
class NetDataActivity:BaseActivity(){


    private var list:ArrayList<WxMessage.Result.Data>?=null;
    override fun getLayout(): Int {
        return R.layout.activity_netdata
    }
    private var dialog:Dialog?=null;//dialog  一
    private var dialog_two: CustomDialog?=null//dialog  二
    override fun init() {
        StatusBarUtils.setWindowStatusBarColor(this,R.color.lanse)
        //dialog  一  直接show
        dialog=LoadDialog.showDialog(this,"加载中");
        //dialog 二 先实例化
//        dialog_two=CustomDialog(this,R.style.CustomDialog)
//        dialog_two?.show()
        getData()
    }

    private var adapter:WxListAdapter?=null;

    private fun getData(){
        var sjc=(System.currentTimeMillis()).toString();// 13位时间戳
        var timeStampSec = System.currentTimeMillis()/1000;//10位时间戳
        var timestamp = String.format("%010d", timeStampSec);
        var Timestamp = (System.currentTimeMillis() / 1000).toString();
        HttpUtil
                .putKeyCode("sort","desc")
                .putKeyCode("page",1)
                .putKeyCode("pagesize",20)
                .putKeyCode("time",timestamp)
                .putKeyCode("key","2689c4e9d6c76006c8a3280c20915027")
                .AskPost("list.php",object: HttpUtil.OnDataListener {
                    override fun Error(e: String?) {
                        var s=e
                        LoadDialog.closeDialog(dialog)
//                        dialog_two?.hide()
                    }

                    override fun Success(content: Any) {
                        var bean=content as WxMessage
                        list=bean.result.data
                        adapter= WxListAdapter(this@NetDataActivity,R.layout.wzlist_item, list!!)
                        list_view.adapter=adapter
                        LoadDialog.closeDialog(dialog)
//                        dialog_two?.hide()
                    }
                },WxMessage::class.java)
    }

    override fun setListener() {
        list_view?.setOnItemClickListener { parent, view, position, id ->
            val q= list!![position].content;
            showToast(q)
        }
    }
}