package wizorle.mykotlin.Test0613

import kotlinx.android.synthetic.main.activity_upimage.*
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseActivity
import wizorle.mykotlin.base.ListViewBaseAdapter
import wizorle.mykotlin.base.ViewHolder

/**
 * Created by 何人执笔？ on 2018/6/11.
 * liushengping
 */
open class ListBaseActivity :BaseActivity(){

    override fun getLayout(): Int {
        return R.layout.activity_upimage
    }

    private var adapter: ListViewBaseAdapter<Data>? = null
//    private var adapter: ListViewAdpater? = null
    private var list= arrayListOf<Data>()

    override fun init() {
        for (i in 0 until 30){
            var  data=Data("测试ListView的封装BaseAdapter"+i)
            list.add(data)
        }
        if (adapter == null) {
            //方法一
            adapter = object : ListViewBaseAdapter<Data>(this, list, R.layout.wzlist_item) {
                override fun initialise(view_holder: ViewHolder, itemData: Data, position: Int) {
                    view_holder.setText(R.id.text_msg, itemData.name)
                }
            }
            //方法二
//            adapter=ListViewAdpater(this, list, R.layout.wzlist_item)
            btn_up?.adapter = adapter
        } else {
            adapter?.list = list
            adapter?.notifyDataSetChanged()
        }
    }


    override fun setListener() {

    }
}