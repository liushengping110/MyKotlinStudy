package wizorle.mykotlin.Test0618

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import kotlinx.android.synthetic.main.activity_weihuo.*
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseActivity
import wizorle.mykotlin.base.ViewPagerBaseAdapter

/**
 * Created by 何人执笔？ on 2018/6/19.
 * liushengping
 */
class WeiHuoActivity:BaseActivity(),View.OnClickListener{


    override fun getLayout(): Int = R.layout.activity_weihuo

    override fun init() {
        Steep()
        setViewPager()
    }

    private var fragments=ArrayList<Fragment>();
    private var adapter: ViewPagerBaseAdapter?=null
    private fun setViewPager(){
        fragments.add(Fragment_Main())
        fragments.add(Fragment_Main())
        fragments.add(Fragment_Main())
        fragments.add(Fragment_Main())
        if(adapter==null){
            adapter= ViewPagerBaseAdapter(supportFragmentManager,fragments)
            view_pager.adapter=adapter;
            view_pager.offscreenPageLimit = 4
        }else{
            adapter?.list=fragments
            adapter?.notifyDataSetChanged()
        }
    }

    override fun setListener() {
        rel_main.setOnClickListener(this)
        rel_sf.setOnClickListener(this)
        rel_xx.setOnClickListener(this)
        rel_me.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.rel_main->{
                view_pager.setCurrentItem(0)
            }
            R.id.rel_sf->{
                view_pager.setCurrentItem(1)
            }
            R.id.rel_xx->{
                view_pager.setCurrentItem(2)
            }
            R.id.rel_me->{
                view_pager.setCurrentItem(3)
            }

        }
    }

}