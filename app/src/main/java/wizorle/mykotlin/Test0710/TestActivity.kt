package wizorle.mykotlin.Test0710

import wizorle.mykotlin.Test0710.BaseActivity

/**
 * Created by 何人执笔？ on 2018/7/10.
 * liushengping
 */
class TestActivity:BaseActivity(){

    override fun initUI() {
        MagicBind.inject(this)
    }

    override fun setListener() {
    }

}