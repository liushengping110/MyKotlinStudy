package wizorle.mykotlin.Test0614

import android.content.Context
import android.view.View
import android.widget.Toast
import wizorle.mykotlin.R
import wizorle.mykotlin.Test0613.Data
import wizorle.mykotlin.base.RecyclerViewBaseAdapter
import wizorle.mykotlin.base.ViewHolder

/**
 * Created by 何人执笔？ on 2018/6/12.
 * liushengping
 */
class RecylerViewAdapter(contex :Context,list:List<Data>,layout:Int):RecyclerViewBaseAdapter<Data>(contex,list,layout){
    override fun initialise(holder: ViewHolder?, item: Data, position: Int) {
        holder?.setText(R.id.text_msg,item.name)
        holder?.setClickListener(R.id.text_msg, View.OnClickListener {
            Toast.makeText(context,list[position].name,Toast.LENGTH_LONG).show()
        })
    }
}