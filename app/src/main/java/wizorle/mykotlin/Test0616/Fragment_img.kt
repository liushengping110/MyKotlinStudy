package wizorle.mykotlin.Test0616

import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_img.*
import kotlinx.android.synthetic.main.fragment_img.view.*
import kotlinx.android.synthetic.main.home_pager.*
import kotlinx.android.synthetic.main.home_pager.view.*
import org.jetbrains.anko.startActivity
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseFragment

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 */
class Fragment_img:BaseFragment(){
    override fun getLayout(): Int {
        return R.layout.fragment_img
    }

    override fun init() {
        var msg=arguments.getString("url")
        Glide.with(activity)
                .load(msg)
                .into(view_layout?.img!!)
        img.setOnClickListener {
            activity.startActivity<ImageOnClickctivity>("link" to msg)
        }
    }

    override fun setListener() {

    }


}