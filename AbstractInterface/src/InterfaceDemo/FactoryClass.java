/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceDemo;

/**
 *
 * @author Alex
 */
public class FactoryClass 
{
    public static IOriClass CreateInter(boolean IsTrue)
    {
        if (IsTrue)
        {
            OriClass oriClass = new OriClass();
            return oriClass;
        }
        else
        {
            OriClass2 oriClass2 = new OriClass2();
            return oriClass2;
        }
    }


    public static AbsClass CreateAbs(boolean IsTrue)
    {
        if (IsTrue)
        {
            OriClass oriClass = new OriClass();
            return oriClass;
        }
        else
        {
            OriClass2 oriClass2 = new OriClass2();
            return oriClass2;
        }
    }
}
