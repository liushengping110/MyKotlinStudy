package wizorle.mykotlin.Test0613

import android.content.Context
import android.view.View
import android.widget.Toast
import wizorle.mykotlin.R
import wizorle.mykotlin.base.ListViewBaseAdapter
import wizorle.mykotlin.base.ViewHolder

/**
 * Created by 何人执笔？ on 2018/6/12.
 * liushengping
 */
class ListViewAdpater(context:Context, list:List<Data>, layot:Int): ListViewBaseAdapter<Data>(context,list,layot){
    override fun initialise(view_holder: ViewHolder, item: Data, position: Int) {
        view_holder.setText(R.id.text_msg,item.name)
        view_holder.setClickListener(R.id.text_msg, View.OnClickListener {
            Toast.makeText(context,list[position].name,Toast.LENGTH_LONG).show()
        })
    }
}