/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abstractinterface;

/**
 *
 * @author Alex
 */
abstract public class CBase implements IA 
{
    public CBase() {}
    
    abstract public String[] getParams();
    
    public void run()
    {
        String[] s =getParams();
        if(s != null && s.length> 0)
        {
            for   (int   i=0;  i<s.length; i++)
            {
                System.out.println("s["+ i +"]:"+s[i]);
            }
        }
    }
}
