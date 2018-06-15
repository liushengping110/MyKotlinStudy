package wizorle.mykotlin.Test0611

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import wizorle.mykotlin.R
import wizorle.mykotlin.Test0611.bean.WxMessage

/**
 * Created by 何人执笔？ on 2018/6/11.
 * liushengping
 */
class WxListAdapter(context:Context,var layout:Int,var list:ArrayList<WxMessage.Result.Data>):BaseAdapter(){

    private var inflater=LayoutInflater.from(context)
    class Holder{
        var textContent:TextView?=null;
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view=convertView
        var holder:Holder?=null
        if(view==null){
            holder= Holder()
            view=inflater.inflate(layout,parent,false)
            holder.textContent=view?.findViewById(R.id.text_msg)as TextView
            view.tag=holder
        }else{
            holder=view.tag as Holder
        }

        holder.textContent?.text=list[position].content
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


}