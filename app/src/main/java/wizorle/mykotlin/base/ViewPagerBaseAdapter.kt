package wizorle.mykotlin.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 * viewPager+fragment适配器
 * 加载一般布局viewPager+Fragment
 * 加载TabLayout+ViewPager+Fragment
 */

open class ViewPagerBaseAdapter(val fm: FragmentManager, list: List<Fragment>) : FragmentStatePagerAdapter(fm) {

    var list = list
        set(value) {
            val ft = fm.beginTransaction()
            value.forEach { i ->
                ft.remove(i)
            }
            ft.commit()
            fm.executePendingTransactions()
        }

    override fun getItem(position: Int): Fragment = list[position]

    override fun getCount(): Int = list.size

    override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE


}