package wizorle.mykotlin.Test0608

import android.content.SharedPreferences

/**
 * Created by 何人执笔？ on 2018/6/8.
 * liushengping
 * 定义类  class
 */
class DateTyle{

     val a=0;//val  只读变量、这种声明变量的方式相当于java中的final变量。一个val创建的时候必须初始化
     var b=null;
     var c:SharedPreferences?=null;

    private val list= arrayListOf<Bean>()
//     var list:List<Bean>?=null;
//     val list= arrayListOf<Bean>();//定义

    //fun --不加public修饰符，默认为public
    //无参无返回值方法
     fun one(){

    }
    //带参无返回值方法
     fun one(url: String){

    }

    //无参带返回值方法
     fun two():String{
        return ""
    }
    //带参带返回值方法
     fun two(url: String):String{
        return ""
    }

    //if判断
    fun ifTest(){
        if(list!=null){
            if (list.isNotEmpty()) {//判断集合size>0

            }
        }
    }

    //for循环
    //最开始你的list定义了 arrListOf<Bean>  已经初始化好了
    //然后 for循环里面的  ！！  这个就可以去掉
    // ！！  就是来帮我们判断空指针引用
    fun forTest(){//
//        for (i in 0 until 50){
        for (i in 0 until list.size){
//            var bean=Bean("wode"+i,i.toString())//zheyang
//            list.add(bean);
            //如何添加？
            list.add(Bean("我是"+i,i.toString()))
        }
    }

    //while测试
    fun whileTest(){
        while (true){

        }

    }

    //switch
    var position=null;
    fun switchTest(){
        when(position){
            0->{

            }

            1->{

            }
            else->{

            }
        }
    }
    
}