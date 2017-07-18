/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abstractinterface;

/**
 *
 * @author Alex
 */
public class CSubSub extends   CSub
{
      public   CSubSub()   {}   
      public   void   setParams()
      {   
           this.s =new String[]{"abcd2",   "efg2"};
      }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
            IA main  = new CSubSub();
            main.setParams();
            main.run();
    }
}
