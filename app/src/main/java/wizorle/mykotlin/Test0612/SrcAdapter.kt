package wizorle.mykotlin.Test0612

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class SrcAdapter(var list: List<Fragment>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = list[position]

    override fun getCount() = list.size

    override fun getPageWidth(position: Int) = 0.8f

    override fun saveState() = null

}