/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceDemo;

/**
 *
 * @author Alex
 */
public class OriClass extends AbsClass implements IOriClass  {
        /// <summary>
        /// 该方法在接口和abstract类中出现，因为在AbsClass中有定义需要加override，表示重写
        /// </summary>
        /// <param name="ID"></param>
        /// <returns></returns>
        public String Add(String ID)
        {
            return "Add";
        }

        /// <summary>
        /// 该方法只在接口中出现，不用加override
        /// </summary>
        /// <param name="ID"></param>
        /// <returns></returns>
        public String Set(String ID)
        {
            return "Set";
        }

        /// <summary>
        /// 该方法在abstract类中出现，这里加new表示是新的方法。在接口和abstract中看不到这个方法。
        /// </summary>
        /// <param name="ID"></param>
        /// <returns></returns>
        public String Del(String ID)
        {
            return "DelO";
        }

        /// <summary>
        /// 该方法在abstract类中出现，使用了override来修饰，是因为在abstract中用了virtual来修饰。
        /// </summary>
        /// <param name="ID"></param>
        /// <returns></returns>
        public String Mod(String ID)//base:abstract
        {
            return "ModO";
        }
}
