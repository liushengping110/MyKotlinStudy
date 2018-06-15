package wizorle.mykotlin.Test0611.bean

/**
 * Created by 何人执笔？ on 2018/6/11.
 * liushengping
 */
data class WxMessage(var error_code:Int,var reason:String,var result :Result){
    data class Result(var data:ArrayList<Data>){
        data class Data(var content:String,var hashId:String,var unixtime:String ,var updatetime:String)
    }
}