package wizorle.mykotlin.Test0618

import android.content.Context
import wizorle.mykotlin.R
import wizorle.mykotlin.base.ListViewBaseAdapter
import wizorle.mykotlin.base.ViewHolder

/**
 * Created by 何人执笔？ on 2018/6/19.
 * liushengping
 */
class GridAdapter(context: Context,list:List<String>,layout:Int):ListViewBaseAdapter<String>(context,list,layout ){
    override fun initialise(view_holder: ViewHolder, item: String, position: Int) {
        view_holder.setText(R.id.text_grid,item)
        when(position){
            0->{
                view_holder.setImage(R.id.img_grid,R.mipmap.home_water)
            }
            1->{
                view_holder.setImage(R.id.img_grid,R.mipmap.home_carpentry)
            }
            2->{
                view_holder.setImage(R.id.img_grid,R.mipmap.home_worker)
            }
            3->{
                view_holder.setImage(R.id.img_grid,R.mipmap.home_paint)
            }
            4->{
                view_holder.setImage(R.id.img_grid,R.mipmap.home_dismantle)
            }
            5->{
                view_holder.setImage(R.id.img_grid,R.mipmap.home_design)
            }
            6->{
                view_holder.setImage(R.id.img_grid,R.mipmap.home_seams)
            }
            7->{
                view_holder.setImage(R.id.img_grid,R.mipmap.home_all)
            }

        }

    }

}