package wizorle.mykotlin.base

import android.view.View

/**
 * Created by 何人执笔？ on 2017/10/12.
 * liushengping
 */
interface Holder {

    fun bind(id: Int): View

    fun setText(id: Int, context: String): Holder

    fun setImage(id: Int, did: Int): Holder

    fun setbackcolor(id: Int, did: Int): Holder

    fun settextcolor(id: Int, did: Int): Holder

    fun setClickListener(id: Int, onClickListener: View.OnClickListener): Holder

//    fun onItemListener(id: Int,onItemListener:View.On)

    fun setStatus(rid: Int, status: Int): Holder

    fun glideimage(id: Int,url:String):Holder

    fun Glideimage(id: Int,url:String,did: Int):Holder

}