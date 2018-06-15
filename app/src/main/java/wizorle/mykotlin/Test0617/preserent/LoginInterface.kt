package wizorle.mykotlin.Test0617.preserent

import wizorle.mykotlin.Test0617.model.LoginBack

/**
 * Created by 何人执笔？ on 2018/6/13.
 * liushengping
 *
 */
interface LoginInterface {
    fun  LoginSucc( loginBack: LoginBack)
    fun  LoginFail( errorMsg: String)
}