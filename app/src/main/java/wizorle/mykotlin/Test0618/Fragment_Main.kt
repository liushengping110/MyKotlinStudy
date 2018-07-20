package wizorle.mykotlin.Test0618

import android.support.v4.view.ViewPager
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.transformer.*
import kotlinx.android.synthetic.main.fragment_main.*
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseFragment
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.opengl.GLES10.glTranslatef
import android.opengl.GLES10.glLoadIdentity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_testrecylerview.*
import kotlinx.android.synthetic.main.activity_viewpager.*
import kotlinx.android.synthetic.main.fragment_main_content.*
import kotlinx.android.synthetic.main.fragment_main_content.view.*
import wizorle.mykotlin.Test0612.ImageFragment
import wizorle.mykotlin.Test0612.SrcAdapter
import javax.microedition.khronos.opengles.GL10





/**
 * Created by 何人执笔？ on 2018/6/19.
 * liushengping
 */
class Fragment_Main:BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_main

    override fun init() {
        setContentRecyView()
        setTopBanner()//设置顶部banner
        setCenterView()//设置中部gridView
        setBottomViewPager()//设置底部的viewPager
    }

    private var content_adapter:FragmentMainAdapter?=null
    private var list_detail= arrayListOf<String>()
    private var view_content:View?=null
    private fun setContentRecyView(){
        view_content=LayoutInflater.from(activity).inflate(R.layout.fragment_main_content,null)
        recyclerview.addHeaderView(view_content)
        for (i in 0 until 30){
            list_detail.add("我是"+i)
        }
        if(content_adapter==null){
            recyclerview.layoutManager = LinearLayoutManager(activity)
            content_adapter=FragmentMainAdapter(activity,list_detail,R.layout.recy_item)
            recyclerview.adapter=content_adapter

        }else{
            content_adapter?.list=  list_detail
            content_adapter?.notifyDataSetChanged()
        }
    }
    private var imageList = ArrayList<Int>()
    internal var transformers: MutableList<Class<out ViewPager.PageTransformer>> = java.util.ArrayList()
    private fun setTopBanner() {
        transformers.add(DefaultTransformer::class.java)
        transformers.add(AccordionTransformer::class.java)
        transformers.add(BackgroundToForegroundTransformer::class.java)
        transformers.add(ForegroundToBackgroundTransformer::class.java)
        transformers.add(CubeInTransformer::class.java)//兼容问题，慎用
        transformers.add(CubeOutTransformer::class.java)
        transformers.add(DepthPageTransformer::class.java)
        transformers.add(FlipHorizontalTransformer::class.java)
        transformers.add(FlipVerticalTransformer::class.java)
        transformers.add(RotateDownTransformer::class.java)
        transformers.add(RotateUpTransformer::class.java)
        transformers.add(ScaleInOutTransformer::class.java)
        transformers.add(StackTransformer::class.java)
        transformers.add(TabletTransformer::class.java)
        transformers.add(ZoomInTransformer::class.java)
        transformers.add(ZoomOutTranformer::class.java)
        transformers.add(ZoomOutSlideTransformer::class.java)
        imageList.add(R.mipmap.b1)
        imageList.add(R.mipmap.b2)
        imageList.add(R.mipmap.b3)
        view_content!!.banner_top.setImages(imageList)
                .setImageLoader(GlideImageLoader())
                .setOnBannerListener(bannerChangelistener)
                .start()

    }

    override fun setListener() {
        view_content!!.banner_top.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

            override fun onPageSelected(position: Int) = Unit

            override fun onPageScrollStateChanged(state: Int) = Unit

        })
    }

    private var bannerChangelistener = OnBannerListener { position -> showToast("点击了" + position) }

    private var grid_list=ArrayList<String>()
    private var adapter:GridAdapter?=null
    private fun setCenterView(){
        grid_list.add("水电")
        grid_list.add("木工")
        grid_list.add("瓦工")
        grid_list.add("油漆")
        grid_list.add("拆除")
        grid_list.add("设计")
        grid_list.add("美缝")
        grid_list.add("全部")
        if(adapter==null){
            adapter=GridAdapter(activity,grid_list,R.layout.grid_view)
            view_content!!.grid.adapter=adapter
        }else{
            adapter!!.list=grid_list
            adapter!!.notifyDataSetChanged()
        }
    }

    private var views:ArrayList<Fragment>?=null
    private var wei_adapter:SrcAdapter?=null
    private fun setBottomViewPager(){
        views= ArrayList()
        for (i in 0 until 5  ){
            val bunlde = Bundle()
            when(i){
                0->{
                    bunlde.putSerializable("imagePath", "http://p4.so.qhmsg.com/t01936b57cee9417eb9.jpg")
                }
                1->{
                    bunlde.putSerializable("imagePath", "http://p4.so.qhmsg.com/t0167525c0462eabd09.jpg")
                }
                2->{
                    bunlde.putSerializable("imagePath", "http://p0.so.qhimgs1.com/t016122ec45c5fdc5fa.jpg")
                }
                3->{
                    bunlde.putSerializable("imagePath", "http://p4.so.qhmsg.com/t01936b57cee9417eb9.jpg")
                }
                4-> {
                    bunlde.putSerializable("imagePath", "http://p4.so.qhmsg.com/t0167525c0462eabd09.jpg")
                }
            }
            val fragment = ImageFragment()
            fragment.arguments = bunlde
            views!!.add(fragment)
        }
        if (wei_adapter == null) {
            wei_adapter = SrcAdapter(views!!, childFragmentManager)
            view_content!!.childeviewpager.adapter = wei_adapter
        } else {
            wei_adapter?.list = views!!
            wei_adapter?.notifyDataSetChanged()
        }
    }
}