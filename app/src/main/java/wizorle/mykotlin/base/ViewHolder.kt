package wizorle.mykotlin.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import wizorle.mykotlin.util.GlideCircleTransform

/**
 * Created by 何人执笔？ on 2017/10/12.
 * liushengping
 */

class ViewHolder(val itemview: View,val context:Context) : Holder, RecyclerView.ViewHolder(itemview) {

    override fun setStatus(rid: Int, status: Int): Holder {
        val view = bind(rid)
        view.visibility = status
        return this
    }

    private val views = SparseArray<View>()

    override fun bind(id: Int): View {
        if (views[id] === null) {
            views.append(id, itemview.findViewById(id))
        }
        return views[id]
    }

    override fun setText(id: Int, context: String): Holder {
        val view = bind(id) as TextView
        view.text = context
        return this
    }

    override fun glideimage(id: Int, url: String): Holder {
        val view = bind(id) as ImageView
        Glide.with(context).load(url).into(view)
        return this
    }

    override fun setImage(id: Int, did: Int): Holder {
        val view = bind(id) as ImageView
        view.setImageResource(did)
        return this
    }

    override fun setClickListener(id: Int, onClickListener: View.OnClickListener): Holder {
        bind(id).setOnClickListener(onClickListener)
        return this
    }

    override fun settextcolor(id: Int, did: Int): Holder {
        val view = bind(id) as TextView
        view.setTextColor(did)
        return this
    }

    override fun setbackcolor(id: Int, did: Int): Holder {
        val view = bind(id) as TextView
        view.setBackgroundResource(did)
        return this
    }

    override fun Glideimage(id: Int, url: String, did: Int): Holder {
        val view = bind(id) as ImageView
        Glide.with(context).load(url).placeholder(did).error(did).transform(GlideCircleTransform(context)).into(view);
        return this
    }

}