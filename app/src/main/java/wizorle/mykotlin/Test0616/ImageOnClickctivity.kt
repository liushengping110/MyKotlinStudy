package wizorle.mykotlin.Test0616

import kotlinx.android.synthetic.main.activity_viewimageonclick.*
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseActivity

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 */
class ImageOnClickctivity:BaseActivity(){
    override fun getLayout(): Int {
        return R.layout.activity_viewimageonclick
    }

    override fun init() {
        var msg=intent.getStringExtra("link");
        text.text=msg
    }

    override fun setListener() {

    }


}