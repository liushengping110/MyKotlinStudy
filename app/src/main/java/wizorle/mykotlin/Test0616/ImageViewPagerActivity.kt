package wizorle.mykotlin.Test0616

import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_imgviewpagerfrag.*
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseActivity

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 */
class ImageViewPagerActivity:BaseActivity(){
    override fun getLayout(): Int {
        return R.layout.activity_imgviewpagerfrag
    }

    private var list:ArrayList<String>?=null//进一步实例化，则不为空

    private var list_temp:List<String>?=null//进一步实例化后，该集合缺少ArrayList方法

    private var list_two=ArrayList<String>()//创建集合
    private var list_three= arrayListOf<String>()//创建集合


    private var adapter:ImageVewPagerAdapter?=null
    private var listFragments:ArrayList<Fragment>?=null

    override fun init() {
        list = arrayListOf()//存放图片地址---网络数据请求
        listFragments= arrayListOf()//存放fragment的集合

        list?.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=820967182,1305969535&fm=27&gp=0.jpg")
        list?.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528872442723&di=4e7f14d52fcf33eccebc760ecfd11a79&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151006%2F14-151006114S1135.jpg")
        list?.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528872442536&di=799e7bc2257948fd2b4cc712426260e7&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fd439b6003af33a87589a973ccc5c10385343b525.jpg")

        list?.forEach {
            var bundle=Bundle()
            bundle.putString("url",it)
            var fragment=Fragment_img()
            fragment.setArguments(bundle)
            fragment.arguments=bundle
            listFragments?.add(fragment)
        }


        if(adapter==null){
            adapter=ImageVewPagerAdapter(listFragments!!,supportFragmentManager)
            viewpager.adapter=adapter
        }else{
            adapter?.list=listFragments!!
            adapter?.notifyDataSetChanged()
        }
    }

    override fun setListener() {

    }


}