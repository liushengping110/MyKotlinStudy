package wizorle.mykotlin.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.jetbrains.anko.act
import org.jetbrains.anko.internals.AnkoInternals

/**
 * Created by 何人执笔？ on 2017/9/11.
 * liushengping
 */
abstract class BaseFragment : Fragment() {

    val view_layout by lazy { LayoutInflater.from(context).inflate(getLayout(), null, false) }
    private var create_status = false
    protected var create = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = view_layout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        create_status = true
        if (userVisibleHint && create_status && !create) {
            create = true
            init()
            setListener()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && create_status && !create) {
            create = true
            init()
            setListener()
        }
    }

    override fun onDestroy() {
        create = false
        super.onDestroy()
    }

    abstract fun getLayout(): Int

    abstract fun init()

    abstract fun setListener()

    fun showToast(string: String, time: Int = Toast.LENGTH_SHORT) = Toast.makeText(context, string, time).show()

    fun <T : View> findViewById(rid: Int): View? = view_layout?.findViewById(rid)

    inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any>) {
        AnkoInternals.internalStartActivity(context, T::class.java, params)
    }

    inline fun <reified T : Activity> Fragment.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any>) {
        startActivityForResult(AnkoInternals.createIntent(activity, T::class.java, params), requestCode)
    }

}