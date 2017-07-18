/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceDemo;

/**
 *
 * @author Alex
 */
public class Main 
{
    public static void main(String[] args)
    {
        IOriClass ioriClass = FactoryClass.CreateInter(true);
        System.out.println(ioriClass.Add(""));
        System.out.println(ioriClass.Set(""));

        AbsClass absClass = FactoryClass.CreateAbs(true);
        System.out.println(absClass.Add(""));
        System.out.println(absClass.Get(""));
        System.out.println(absClass.Del(""));//会调用AbsClass.Del
        System.out.println(absClass.Mod(""));//会调用OriClass.Mod
    }
}
