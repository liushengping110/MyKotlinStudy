package wizorle.mykotlin.Test0617.preserent

import wizorle.mykotlin.Test0617.model.DataBack
import wizorle.mykotlin.Test0617.model.LoginBack
import wizorle.mykotlin.Test0617.model.LoginHttp

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 */
class LoginPreserent(var loginInterface: LoginInterface) :DataBack{

    var loginHttp=LoginHttp(this)


    fun Login(name:String ,pass:String ){
        loginHttp.login(name,pass)

    }

    override fun Succ(o: Object) {
        loginInterface.LoginSucc(o as LoginBack)

    }

    override fun Fail(errerMsg: String) {
        loginInterface.LoginFail(errerMsg)

    }
}