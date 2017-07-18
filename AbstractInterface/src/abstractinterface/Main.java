/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abstractinterface;

/**
 * 虽然interface没有方法体,但是如果你的interface中有abstract接口,那么就必须定义这个interface为abstract了,其实,它的意义巨大，给个例子给吧：
 * @author Alex
 */
public class Main extends   CSub
{
      public   Main()   {}

      public   void   setParams()
      {   
           this.s =new String[]{"abcd",   "efg"};
      }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
            IA main  = new Main();
            main.setParams();
            main.run();
    }

}

//  从设计意义上来说，我举个例子：   
//          CBase   类是所有电器产品总类，其中的run()函数负责输出某种电器产品的库存总价值。   
//          CSub   类是所有小家电产品类，其中getParams()函数负责得到某种小家电产品库存总价值。   
//          Main   类是小家电中的电吹风机类，其中的setParams()函数负责电吹风机的特有计算库存产品价值的方式。   
//          当你的每一类电器产品都有自己不同的价值计算方式时，使用这种构架就可以很好的分类计算。   
//          而这些业务逻辑实现类在三层构架中是不提供给客户端调用的，提供给客户端的仅仅是Interface,那么这种设计就是相当必要的。

