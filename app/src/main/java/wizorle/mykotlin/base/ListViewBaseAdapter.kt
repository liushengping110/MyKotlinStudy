package wizorle.mykotlin.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import wizorle.mykotlin.Test0613.Data


/**
 * Created by 何人执笔？ on 2017/10/12.
 * liushengping
 */

abstract class ListViewBaseAdapter<T>(var context: Context, var list: List<T>, val layout: Int) : BaseAdapter() {

    private val inflater=LayoutInflater.from(context)
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       var view=convertView
        if (view==null){
            view=inflater.inflate(layout,parent,false)
        }
        val view_holder= ViewHolder(view!!,context)
        initialise(view_holder,getItem(position),position)
        return view_holder.itemView
    }

    abstract fun initialise(view_holder: ViewHolder, item: T, position: Int)

    override fun getItem(position: Int): T =list[position]

    override fun getItemId(position: Int): Long =position.toLong()

    override fun getCount(): Int=list.size

}

