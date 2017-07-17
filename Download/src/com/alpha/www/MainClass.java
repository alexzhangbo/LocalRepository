package com.alpha.www;

import java.io.*;
import java.net.*;

public class MainClass {

	public static void main(String[] args) 
	{
		// TODO 自动生成的方法存根
		
		
		//String imageUrl="http://img1.mm131.com/pic/";
		//String newImageName="D:/pic/";
		String imageUrl="https://img.aitaotu.cc:8089/Pics/2017/01";
		String newImageName="D:/Pics/2017/01";
		int add=2;
		int iStart=0;
		int iFinish=31;
		
		int arg =args.length;
		if(arg==4)
		{
			imageUrl=args[0];
			newImageName=args[1];
			add=0;
			iStart=Integer.parseInt(args[2]);
			iFinish=Integer.parseInt(args[3]);
			System.out.println("开始下载任务！");
		}
		else if(arg==3)
		{
			imageUrl=args[0];
			newImageName=args[1];
			add=Integer.parseInt(args[2]);

			System.out.println("开始下载任务！");
		}
		else
		{
			System.out.println("请输入正确参数！");
			//return;
		}

		if(add==2)
		{
			for(int i=1;i<=31;i++)
			{
				
				String imageUrl2=imageUrl+String.format("%02d", i)+"/";
				String newImageName2=newImageName+String.format("%02d", i)+"/";
				//System.out.println(imageUrl2);
				//System.out.println(newImageName2);
				newFolder(newImageName2);
				boolean isFind=true;
				int FindJoy=0;
				int del=0;
				for(int j=1;j<100;j++)
				{
					
				
					FindJoy=j;

					//boolean isFind=true;
					int FindKey=0;
					String imageUrl3=imageUrl2+String.format("%02d", j)+"/";
					String newImageName3=newImageName2+String.format("%02d", j)+"/";
					//System.out.println(imageUrl3);
					System.out.println(newImageName3);
					
					if(!isFolder(newImageName3))
					{
					
						newFolder(newImageName3);
						
						
						for(int k=1;k<200;k++)
						{
	
							FindKey=k;
							
							String imageUrl4=imageUrl3+String.format("%02d", k)+".jpg";
							String newImageName4=newImageName3+String.format("%02d", k)+".jpg";
	
						
							
							isFind=Save(imageUrl4,newImageName4,i,iFinish);
							if(isFind==false)
							{
								del++;
								break;
	
							}
							else
							{
								del=0;
							}
						}
	
						if(isFind==false && FindKey==1)
						{
							delFolder(newImageName3);
							
							if(del>=3)
							{
								break;
							}
							
						}
					}
				}
				if(isFind==false && FindJoy==1)
				{
					delFolder(newImageName2);
					//break;
					
				}
				
			}
		}
		else
		{
			
			for(int i=iStart;i<=iFinish;i++)
			{
				String imageUrl2=imageUrl+String.format("%04d", i)+"/";
				String newImageName2=newImageName+String.format("%04d", i)+"/";
					
				newFolder(newImageName2);
				int j=0;
				for(j=1;j<100;j++)
				{
					String imageUrl3=imageUrl2+j+".jpg";
					String newImageName3=newImageName2+j+".jpg";
					
					boolean isFind=Save(imageUrl3,newImageName3,i,iFinish);
					if(isFind==false && j==0)
					{
						delFolder(newImageName2);
						break;
						
					}
					
				}
			}
		}
		System.out.println("下载任务完成！");

	}
	
	/**  
	 * 新建目录  
	  * @param folderPath String 如 c:/fqf  
	  * @return boolean  
	  */  
	static void newFolder(String folderPath) {   
	   try {   
	     String filePath = folderPath;   
	     filePath = filePath.toString();   
	     java.io.File myFilePath = new java.io.File(filePath);   
	     if (!myFilePath.exists()) {   
	       myFilePath.mkdir();   
	     }   
	   }   
	   catch (Exception e) {   
	     System.out.println("新建目录操作出错");   
	     e.printStackTrace();   
	   }   
	 }   
	
	static boolean isFolder(String folderPath) 
	{   
	
	     java.io.File myFilePath = new java.io.File(folderPath); 
		 return myFilePath.exists();
	}   
	
	
	 /**  
	  * 删除文件夹  
	  * @param filePathAndName String 文件夹路径及名称 如c:/fqf  
	  * @param fileContent String  
	  * @return boolean  
	  */  
	static void delFolder(String folderPath) {   
	   try {   
	     //delAllFile(folderPath); //删除完里面所有内容   
	     String filePath = folderPath;   
	     filePath = filePath.toString();   
	     java.io.File myFilePath = new java.io.File(filePath);   
	     myFilePath.delete(); //删除空文件夹   
	 
	   }   
	   catch (Exception e) {   
	     System.out.println("删除文件夹操作出错");   
	     e.printStackTrace();   
	 
	   }   
	  
	  }   
	static boolean Save(String imageUrl,String newImageName,int iNow,int iFinish)
	{
	

		URL url;
		try {
			url = new URL(imageUrl);
			DataInputStream dis = new DataInputStream(url.openStream());
			


			//建立一个新的文件

			FileOutputStream fos = new FileOutputStream(new File(newImageName));

			byte[] buffer = new byte[1024];

			int length;

			//开始填充数据

			while((length = dis.read(buffer))>0)
			{
				fos.write(buffer,0,length);
			}

			dis.close();

			fos.close(); 
			
			System.out.println(newImageName);

		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("完成一组下载！"+iNow+"/"+iFinish);
			return false;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			//e.printStackTrace();
			System.out.println("完成一组下载！"+iNow+"/"+iFinish);
			return false;
		}
		return true;
		//打开网络输入流
	}
	
	

}
