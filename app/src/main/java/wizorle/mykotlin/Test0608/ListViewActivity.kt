package wizorle.mykotlin.Test0608

import android.os.Handler
import android.widget.ListView
import android.widget.Toast
import com.weihuo.weihuo.util.StatusBarUtils
import kotlinx.android.synthetic.main.activity_list.*
import wizorle.mykotlin.MainActivity
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseActivity
import wizorle.mykotlin.util.ImageUtil
import java.lang.ref.WeakReference

/**
 * Created by 何人执笔？ on 2018/6/11.
 * liushengping
 */
class ListViewActivity: BaseActivity(){

    override fun getLayout(): Int {
        return R.layout.activity_list
    }

    //定义集合方法一
    private var listOne:ArrayList<Bean>?= null;
    private fun one(){
        listOne= arrayListOf();//两种 写法都一样
//        listOne= ArrayList();
        for (i in 0 until 30){
            var bean=Bean(i.toString(),"我是"+i);
            listOne?.add(bean)
        }
    }
    //定义集合方法二
    private var listTwo= arrayListOf<Bean>();
    private fun two(){
        for (i in 0 until 50){
            var bean=Bean(i.toString(),"我们是"+i);
            listTwo.add(bean)
        }
    }

    private var adapter: ListAdapter? = null
    override fun init() {
        StatusBarUtils.setWindowStatusBarColor(this, R.color.lanse)
        one()
        two()
        showToast(listOne!!.size.toString())
        showToast(listTwo.size.toString())
//        adapter= ListAdapter(this,R.layout.list_item, listOne!!)
        adapter = ListAdapter(this, R.layout.wzlist_item, listTwo)
        list_view.adapter = adapter

    }

    override fun setListener() {
        list_view.setOnItemClickListener { parent, view, position, id ->
            finish()
        }
    }


}