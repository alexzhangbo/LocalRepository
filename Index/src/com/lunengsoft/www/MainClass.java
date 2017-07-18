package com.lunengsoft.www;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.lunengsoft.bean.*;

public class MainClass
{


	public static void main(String[] args)
	{

		try
		{
			Date dateStart=null,dateFinish=null;//统计时段的开始与结束时间。
			int TCMAX=15;	//SOE事项与遥信动作变位记录时间最大差值（S）：TCMAX，默认值15。
			int TSOE=2;//SOE事项抖动判断时间（S）：TSOE，默认值2。
			int CSOE=3;//SOE事项抖动次数：CSOE，默认值3。
			int ZDC=20;//判断终端异常的掉线次数：ZDC，默认值20。
			int YKD=300;//遥控告警判断周期（分钟）：YKD，默认值5。
			int YKC=3;//遥控告警判断次数：YKC，默认值3。
			


			if(args!=null&&args.length==10)
			{
				
				//System.out.println(args.length);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try
				{
					dateStart = df.parse(args[0]+" "+args[1]);
					dateFinish = df.parse(args[2]+" "+args[3]);
					TCMAX	=Integer.parseInt(args[4]);
					TSOE	=Integer.parseInt(args[5]);
					CSOE	=Integer.parseInt(args[6]);
					ZDC		=Integer.parseInt(args[7]);
					YKD		=Integer.parseInt(args[8]);
					YKC		=Integer.parseInt(args[9]);
				}
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					System.out.println("解析开始时间：［"+args[0]+"］和结束时间：［"+args[1]+"］时出错");
					return;
				}
				catch (NumberFormatException e)
				{
					// TODO Auto-generated catch block
					System.out.println("参数解析出错，请检查参数设置。");
					return;
				}


			}
/*			else
			{
				Calendar c=Calendar.getInstance();
				Date d=c.getTime();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				c.add(Calendar.MONTH, 0);
		        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
				System.out.println(d.toString());
			}
*/
			System.out.println("统计开始时间："+args[0]+" "+args[1]);
			System.out.println("统计结束时间："+args[2]+" "+args[3]);
			
			
			File file = new File("8.xls");
			System.out.println(file.getAbsolutePath());
			Workbook wb = Workbook.getWorkbook(file);
			
			//listTermi=
			
			// /////////////////////////////////////
			/*
			Online online = new Online();
			online.readExel(wb);
			online.readEx(wb);

			online.getResult(wb, dateStart, dateFinish);
			getResult(online, dateStart, dateFinish);
			*/

			// //////////////////////////////////////
			
			
			ControlUse use = new ControlUse();

			double rcUse=use.getYtotal(wb, dateStart, dateFinish);
			
			
			// //////////////////////////////////////
			
			ControlSuccess success = new ControlSuccess();
		
			
			//success.readSuc(wb);
			double rcSuccess=success.gtYuc(wb, dateStart, dateFinish);

			
			// //////////////////////////////////////
			
			SignalingCorrect correct = new SignalingCorrect();
			
			correct.TCMAX=TCMAX;
			double rcCorrect=correct.gtMiddleList(wb, dateStart, dateFinish);
			System.out.println(rcCorrect);
		}
		catch (BiffException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getResult(Online online, Date starttime, Date endtime)
	{
		FileWriter fw=null;
		try
		{	
			File f = new File("123.txt");
			f.createNewFile();
			fw = new FileWriter(f);
			fw.write("\r\n");
			fw.write("信息展示(注释:当开始时间和结束时间为空时,统计的终端在线率为本月份的终端在线率)\r\n");
		}
		catch (Exception e)
		{

		}
		
		
		List<PdUtilBean> st = online.pdBeanlist;
		long outtime = 0;
		long totalTime = (endtime.getTime() - starttime.getTime()) / 1000; // 输入的两个时间相差的分钟
		
		if (st != null && st.size() != 0)
		{
			for (int i = 0; i < st.size(); i++)
			{
				outtime += st.get(i).getTrc();

			}
		}
		double outreat = (((totalTime * online.count) - outtime) * 100)
				/ (totalTime * online.count);
		try
		{	
			fw.write("终端在线率为:" + outreat + "%\r\n");
			
			
			
			getLastResult(online, starttime, endtime,fw);
			
			
			
			
			fw.flush();
			fw.close();
		}
		catch (Exception e)
		{

		}

	}

	// 本方法用来得到最终结果数据
	public static void getLastResult(Online online, Date starttime, Date endtime,FileWriter fw)
	{

		try
		{
			List<PdUtilBean> st = online.pdBeanlist;

			fw.write("终端id     " + "       投入时间    " + "     退出时间      "
					+ "       时间差      ");
			if (st != null && st.size() != 0)
			{
				for (int i = 0; i < st.size(); i++)
				{

					fw.write("\r\n");
					fw.write(" " + st.get(i).id + "            " + st.get(i).Tr
							+ "     " + st.get(i).Tc + "           "
							+ st.get(i).Trc);
				}
			}
			fw.write("\r\n");

		}
		catch (Exception e)
		{

		}

	}
	


}
