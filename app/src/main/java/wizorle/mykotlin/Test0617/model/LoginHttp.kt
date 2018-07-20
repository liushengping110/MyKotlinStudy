package wizorle.mykotlin.Test0617.model

import org.json.JSONObject
import wizorle.mypointbroadcast.HttpUtil

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 */
class LoginHttp(var dataBack: DataBack){


    fun login(name:String ,pass:String){
        var js=JSONObject();
        js.put("TradeCode", "Y003");
        js.put("LoginName", name);
        js.put("LoginPass", pass);
        HttpUtil.AskJson("orderInterface.do",js,object: HttpUtil.OnDataListener {
            override fun Error(e: String?) {
                dataBack.Fail(e.toString())
            }

            override fun Success(content: Any) {
                dataBack.Succ(content as Object)
            }
        },LoginBack::class.java )
    }
}