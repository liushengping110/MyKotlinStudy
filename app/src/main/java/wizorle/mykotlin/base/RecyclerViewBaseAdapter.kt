package wizorle.mykotlin.base

import android.content.Context
import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by 何人执笔？ on 2017/10/12.
 * liushengping
 */

abstract class RecyclerViewBaseAdapter<T>(val context: Context, var list: List<T>, val layout: Int) : RecyclerView.Adapter<ViewHolder>() {

    val inflater = LayoutInflater.from(context)

    override fun getItemCount(): Int = list.size + head_view.size()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (head_view[position] != null) {
            return
        }
        initialise(holder, getItem(position), position)
    }

    fun getItem(position: Int) =
            if (head_view[0] != null) {
                list[position - 1]
            } else {
                list[position]
            }

    abstract fun initialise(holder: ViewHolder?, item: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            if (viewType == head) {
                ViewHolder(head_view[0],context)
            } else {
                ViewHolder(inflater.inflate(layout, parent, false),context)
            }

    private val head = 10000

    private var head_view = SparseArrayCompat<View>()

    override fun getItemViewType(position: Int) =
            if (head_view[position] != null) {
                head
            } else {
                super.getItemViewType(position)
            }

    open fun addHead(view: View) {
        head_view.append(0, view)
    }

}