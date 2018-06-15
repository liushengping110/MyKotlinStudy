package wizorle.mykotlin.Test0615

import kotlinx.android.synthetic.main.fragment_item.*
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseFragment

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 */
class Fragment_one:BaseFragment(){
    override fun getLayout(): Int {
        return R.layout.fragment_one
    }

    override fun init() {
        var msg=arguments.getString("type")
        showToast(msg)
        text_msg.text=msg
    }

    override fun setListener() {


    }
}