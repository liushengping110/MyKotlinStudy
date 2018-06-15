package wizorle.mykotlin.Test0612

import android.os.Bundle
import android.service.notification.StatusBarNotification
import android.support.v4.app.Fragment
import com.weihuo.weihuo.util.StatusBarUtils
import kotlinx.android.synthetic.main.activity_viewpager.*
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseActivity

/**
 * Created by 何人执笔？ on 2018/6/11.
 * liushengping
 */
class ViewPagerActivity :BaseActivity(){

    private var wei_adapter:SrcAdapter?=null;
    override fun getLayout(): Int {
        return R.layout.activity_viewpager
    }

    override fun init() {
        StatusBarUtils.setWindowStatusBarColor(this,R.color.lanse)
        val views = arrayListOf<Fragment>()
//        val strings = arrayListOf<String>()//图片地址集合--遍历--传递给适配器
//        strings.forEach {
//            val bunlde = Bundle()
//            bunlde.putParcelable("item", it)
//            val fragment = ImageFragment()
//            fragment.arguments = bunlde
//            views.add(fragment)
//        }

        for (i in 0 until 3 ){
            val bunlde = Bundle()
            when(i){
                0->
                    bunlde.putSerializable("imagePath", "http://p4.so.qhmsg.com/t01936b57cee9417eb9.jpg")
                1->
                    bunlde.putSerializable("imagePath", "http://p4.so.qhmsg.com/t0167525c0462eabd09.jpg")
                2->
                    bunlde.putSerializable("imagePath", "http://p0.so.qhimgs1.com/t016122ec45c5fdc5fa.jpg")
            }
            val fragment = ImageFragment()
            fragment.arguments = bunlde
            views.add(fragment)
        }
        if (wei_adapter === null) {
            wei_adapter = SrcAdapter(views, supportFragmentManager)
            viewpager.adapter = wei_adapter
        } else {
            wei_adapter?.list = views
            wei_adapter?.notifyDataSetChanged()
        }
    }

    override fun setListener() {


    }
}