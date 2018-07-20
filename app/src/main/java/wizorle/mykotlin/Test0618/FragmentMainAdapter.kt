package wizorle.mykotlin.Test0618

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import wizorle.mykotlin.R
import wizorle.mykotlin.base.RecyclerViewBaseAdapter
import wizorle.mykotlin.base.ViewHolder

/**
 * Created by 何人执笔？ on 2018/6/19.
 * liushengping
 */
class FragmentMainAdapter(context: Context,list:ArrayList<String>,layout:Int): RecyclerViewBaseAdapter<String>(context, list, layout){
    override fun initialise(holder: ViewHolder?, item: String, position: Int) {
        holder!!.setText(R.id.text_recy_item,item)
    }
}