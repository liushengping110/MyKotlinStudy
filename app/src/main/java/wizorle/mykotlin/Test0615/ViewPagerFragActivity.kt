package wizorle.mykotlin.Test0615

import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_viewpagerfrag.*
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseActivity
import wizorle.mykotlin.base.ViewPagerBaseAdapter

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 */
class ViewPagerFragActivity:BaseActivity(){
    override fun getLayout(): Int {
        return R.layout.activity_viewpagerfrag
    }

    private var list:ArrayList<Fragment>?=null
    private var adapter: ViewPagerBaseAdapter?=null
    override fun init() {
        list= arrayListOf()
        for (i in 0 until 2){
            when(i){
                0->{
                    var bundl=Bundle()
                    bundl.putString("type",i.toString())
                    var fr=Fragment_one()
                    fr.setArguments(bundl);
                    list?.add(fr)
                }
                1->{
                    var bundl=Bundle()
                    bundl.putString("type",i.toString())
                    var fra=Fragment_two()
                    fra.setArguments(bundl);
                    list?.add(fra)
                }
            }
        }

        if(adapter==null){
            viewpager.adapter=object : ViewPagerBaseAdapter(supportFragmentManager, list!!){
                override fun getPageTitle(position: Int): CharSequence {
                    when (position){
                        0->{
                            return "one"
                        }
                        1->{
                            return "two"
                        }
                    }
                    return super.getPageTitle(position)
                }
            }
            viewpager.setOffscreenPageLimit(2)
            tab_layout.setupWithViewPager(viewpager)
            //无需设置标题，调用下面代码
//            adapter=ViewPagerBaseAdapter(supportFragmentManager,list!!)
//            viewpager.adapter=adapter
        }else{
            adapter!!.list=list!!
            adapter!!.notifyDataSetChanged()
        }
    }

    override fun setListener() {

    }


}