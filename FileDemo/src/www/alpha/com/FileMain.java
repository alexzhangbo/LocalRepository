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
		//File f = new File("d:\\���ޱ���.jpg"); 
		//File outF = new File("d:\\���ޱ���Output.jpg"); 
		try 
		{ 
			FileInputStream fis = new FileInputStream(f); 
			FileOutputStream fops = new FileOutputStream(outF); 
			fis.read(b); 
			fops.write(b); 

			//���ļ����� 
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
     * ��ȡUnicode�����ı��ļ� 
     * @param resource String - �ļ��� 
     * @return String - Unicode�ı� 
     */ 
    public static String read_Uni(String resource) {

        byte word_uni[] = new byte[1024];

        String strReturn = null;

        InputStream is;

        try {

            is = instance.getClass().getResourceAsStream(resource);

            is.skip(2);            // ���������ֽڵ��ļ�ͷ 

 

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
     * ��ȡUTF-8�����ı��ļ� 
     * @param resource String - �ļ��� 
     * @return String - UTF-8�ı� 
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
