package wizorle.mykotlin.Test0617.view

import android.app.Dialog
import android.view.View
import kotlinx.android.synthetic.main.activity_mvptest.*
import wizorle.mykotlin.R
import wizorle.mykotlin.Test0617.model.LoginBack
import wizorle.mykotlin.Test0617.preserent.LoginInterface
import wizorle.mykotlin.Test0617.preserent.LoginPreserent
import wizorle.mykotlin.base.BaseActivity
import wizorle.mykotlin.util.dialog.LoadDialog

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 * MVP
 */
class MvpTestActivity:BaseActivity(),LoginInterface{

    override fun getLayout(): Int {
        return R.layout.activity_mvptest
    }

    private var dialog: Dialog?=null

    override fun init() {
        btn_login.setOnClickListener(View.OnClickListener {
            dialog=LoadDialog.showDialog(this,"登录中")
            loginPreserenr.Login("18201367542","lsp")
        })
    }

    private var loginPreserenr=LoginPreserent(this)

    override fun LoginSucc(loginBack: LoginBack) {
        LoadDialog.closeDialog(dialog)
        text_msg.text=loginBack.ResultContent
    }

    override fun LoginFail(errorMsg: String) {
        text_msg.text="网络请求失败"
        LoadDialog.closeDialog(dialog)
    }

    override fun setListener() {

    }
}