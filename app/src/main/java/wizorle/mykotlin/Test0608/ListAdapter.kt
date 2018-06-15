package wizorle.mykotlin.Test0608

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import org.w3c.dom.Text
import wizorle.mykotlin.R

/**
 * Created by 何人执笔？ on 2018/6/8.
 * liushengping
 */
class ListAdapter(context: Context,private var layout:Int,var list:ArrayList<Bean>):BaseAdapter(){

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view=convertView
        var holder:Holder?=null
        if(view==null){
            holder= Holder()
            view=inflater.inflate(layout,parent,false)
            holder.text_msg = view?.findViewById(R.id.text_msg)as TextView
            view.tag = holder
        }else{
            holder=view.tag as Holder
        }

        holder?.text_msg?.text=list[position].details
        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    class Holder{
        var text_msg:TextView?=null
    }

}
