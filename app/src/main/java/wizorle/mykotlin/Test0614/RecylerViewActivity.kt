package wizorle.mykotlin.Test0614

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_testrecylerview.*
import wizorle.mykotlin.R
import wizorle.mykotlin.Test0613.Data
import wizorle.mykotlin.base.BaseActivity
import wizorle.mykotlin.base.RecyclerViewBaseAdapter
import wizorle.mykotlin.base.ViewHolder

/**
 * Created by 何人执笔？ on 2018/6/12.
 * liushengping
 */
class RecylerViewActivity :BaseActivity(){
    override fun getLayout(): Int {
        return  R.layout.activity_testrecylerview
    }

    private var adapter:RecyclerViewBaseAdapter<Data>?=null;
//    private var adapter:RecylerViewAdapter?=null;
    private var list= arrayListOf<Data>()

    override fun init() {
        for (i in 0 until 30){
            var  data=Data("测试RecylerView的封装BaseAdapter"+i)
            list.add(data)
        }
        if(adapter==null){
            adapter=object :RecyclerViewBaseAdapter<Data>(this,list,R.layout.wzlist_item){
                override fun initialise(holder: ViewHolder?, item: Data, position: Int) {
                    holder!!.setText(R.id.text_msg,item.name)
                }
            }
            //方法二
//            adapter=RecylerViewAdapter(this,list,R.layout.wzlist_item)
            recy_view.layoutManager = LinearLayoutManager(this)
            recy_view.adapter=adapter
        }else{
            adapter!!.list=list
            adapter!!.notifyDataSetChanged()
        }
    }

    override fun setListener() {


    }
}