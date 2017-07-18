/**
 * 
 */
package www.alpha.com;
import java.io.*; 

/**
 * @author Alex
 *
 */
public class FileMain
{

	private static FileMain instance  = new FileMain();
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		byte[] b = new byte[17*1024]; 

		// ** txt 
		 File f = new File("d:\\123.sql"); 
		 File outF = new File("d:\\123456.sql"); 
		// ** jpg 
		//File f = new File("d:\\毫无保留.jpg"); 
		//File outF = new File("d:\\毫无保留Output.jpg"); 
		try 
		{ 
			FileInputStream fis = new FileInputStream(f); 
			FileOutputStream fops = new FileOutputStream(outF); 
			fis.read(b); 
			fops.write(b); 

			//看文件内容 
			String s = new String(b); 
			System.out.println(s); 

			fis.close(); 
			fops.close(); 

		} 
		catch(FileNotFoundException fnfe) 
		{ 
			System.out.println("FileNotFoundException fnfe"); 
			fnfe.printStackTrace(); 
		} 
		catch(IOException ie) 
		{ 
			System.out.println("IOException ie"); 
			ie.printStackTrace(); 
		}
	}
	/** 
     * 读取Unicode编码文本文件 
     * @param resource String - 文件名 
     * @return String - Unicode文本 
     */ 
    public static String read_Uni(String resource) {

        byte word_uni[] = new byte[1024];

        String strReturn = null;

        InputStream is;

        try {

            is = instance.getClass().getResourceAsStream(resource);

            is.skip(2);            // 跳过两个字节的文件头 

 

            is.read(word_uni);

            is.close();

            StringBuffer stringbuffer = new StringBuffer("");

            for (int j = 0; j < word_uni.length; ) {

                int l = word_uni[j++];

                int h = word_uni[j++];

                char c = (char) ((l & 0xff) | ((h << 8) & 0xff00));

                stringbuffer.append(c);

            }

            strReturn = stringbuffer.toString();

        } catch (IOException ex) {

            System.out.println(ex);

        } finally {

            is = null;

        }

        return strReturn;

}

    /** 
     * 读取UTF-8编码文本文件 
     * @param resource String - 文件名 
     * @return String - UTF-8文本 
     */ 
    public static String read_UTF(String resource) 
    {

        byte word_utf[] = new byte[1024];
        String strReturn = null;
        InputStream is;

        try 
        {
            is = instance.getClass().getResourceAsStream(resource);
            is.read(word_utf);
            is.close();

            strReturn = new String(word_utf, "UTF-8");
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
        return strReturn;

   }

}
