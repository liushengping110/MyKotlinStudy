package wizorle.mykotlin.Test0616

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 * 加载图片的viewPager+fragment
 */
open class ImageVewPagerAdapter(var list:List<Fragment>,fm:FragmentManager): FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }
}