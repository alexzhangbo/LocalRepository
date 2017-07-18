/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceDemo;

/**
 *
 * @author Alex
 */
public abstract class AbsClass {
        public abstract String Add(String ID);

        public String Get(String ID)
        {
            return "Get";
        }

        public String Del(String ID)
        {
            return "Del";
        }

        public String Mod(String ID)
        {
            return "Mod";
        }
}
