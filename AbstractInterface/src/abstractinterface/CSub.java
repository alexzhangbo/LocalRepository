/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abstractinterface;

/**
 *
 * @author Alex
 */
abstract public class CSub extends CBase
{
    public   CSub(){}   
    protected String[] s;
    
    //abstract public void setParams();
    
    public   String[]   getParams()
    {
        return this.s;
    }   
}
