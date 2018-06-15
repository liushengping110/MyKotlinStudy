package wizorle.mykotlin.Test0612

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_pager.*
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.home_pager.view.*
import wizorle.mykotlin.R
import wizorle.mykotlin.base.BaseFragment


 class ImageFragment : Fragment() {


     override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         return inflater!!.inflate(R.layout.home_pager,null)
     }

     override fun onActivityCreated(savedInstanceState: Bundle?) {
         super.onActivityCreated(savedInstanceState)

         init()
     }


     fun init() {
         val parcelable = arguments.getSerializable("imagePath")
         Glide.with(activity)
                 .load(parcelable)
                 .into(imageView3)
         home_case.setOnClickListener {
//         activity.startActivity<ShowInformationActivity>("link" to parcelable.path) }
//             showToast(parcelable.toString())
             Toast.makeText(activity,parcelable.toString(),Toast.LENGTH_LONG).show()
        }
     }
}