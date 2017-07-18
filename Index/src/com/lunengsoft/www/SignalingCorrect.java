package com.lunengsoft.www;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.lunengsoft.bean.YxdzUtilBean;
import com.lunengsoft.bean.YxsoeUtilBean;

public class SignalingCorrect
{

	public int TCMAX;

	// 得到中间的结果list
	public double gtMiddleList(Workbook wb, Date starttime, Date endtime)
	{
		List<YxdzUtilBean> yxdz = readYxdzBw(wb); // 遥信动作变位记录的数据源集合
		List<YxsoeUtilBean> yxsoe = readYxSoe(wb); // 遥信动作SOE记录表的数据源集合
		// 遥信动作记录总条数
		List<YxdzUtilBean> W1 = new ArrayList<YxdzUtilBean>();
		List<YxsoeUtilBean> W2 = new ArrayList<YxsoeUtilBean>();

		// 针对每条记录进行循环，在遥信动作SOE记录表中查找与当前遥信变位记录的同样的SOE记录
		// （要求SOE记录的时间应该在遥信动作变位记录时间前15s之内），
		// 若找到对应的SOE记录，则正确动作次数t2加一。 t2/t1得出遥信动作正确率。
		int iYXBW=yxdz.size();
		int iZQ=0;
		int iFind=0;

		for (int i = 0; i < yxdz.size(); i++)
		{// 循环的变位表
			iFind = 0;
			
			for (int j = 0; j < yxsoe.size(); j++)
			{
				if (yxdz.get(i).id.equals(yxsoe.get(j).id)
						&& yxdz.get(i).src.equals(yxsoe.get(j).src)
						&& yxdz.get(i).getGdate().getTime()
								- yxsoe.get(j).getGdate().getTime() < 1000 * TCMAX
						&& yxdz.get(i).getGdate().getTime()
								- yxsoe.get(j).getGdate().getTime() > 0)
				{
					iFind++;
				}

			}
			if(iFind>0)
			{
				iZQ++;
			}
			else
			{
				W1.add(yxdz.get(i));
			}

		}
		
		
		double r= 100*iZQ / iYXBW;
		return r;

	}

	
	// 本方法用作读取数据源(遥信动作变位记录表)
	public List<YxdzUtilBean> readYxdzBw(Workbook wb)
	{
		try
		{
			Sheet sheet = wb.getSheet("PDS_RES_YX_BW"); // 获取数据第一个表
			if (sheet == null)
			{
				System.out.println("无法读取工作薄");
			}
			int cols = sheet.getColumns(); // 获取总列数
			int rows = sheet.getRows();// 总行数
			String n = sheet.getName();// 获得工作薄名称
			List<YxdzUtilBean> list = new ArrayList();
			for (int i = 1; i < rows; i++)
			{
				YxdzUtilBean yxbw = new YxdzUtilBean();
				Cell t0 = sheet.getCell(0, i);// 通过指定行和列得到某一个具体的内容这里3列2行
				yxbw.gtime = t0.getContents();
				Cell t1 = sheet.getCell(1, i);// 通过指定行和列得到某一个具体的内容这里3列2行
				yxbw.id = t1.getContents();
				Cell t2 = sheet.getCell(2, i);
				yxbw.tname = t2.getContents();
				Cell t3 = sheet.getCell(3, i);
				yxbw.src = t3.getContents();
				Cell t4 = sheet.getCell(4, i);
				yxbw.value = t4.getContents();
				Cell t5 = sheet.getCell(5, i);
				yxbw.type = t5.getContents();
				list.add(yxbw);
			}
			return list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	// 遥信动作SOE记录表
	public List<YxsoeUtilBean> readYxSoe(Workbook wb)
	{
		try
		{
			Sheet sheet = wb.getSheet("PDS_RES_YX_SOE"); // 获取数据第一个表
			if (sheet == null)
			{
				System.out.println("无法读取工作薄");
			}
			int cols = sheet.getColumns(); // 获取总列数
			int rows = sheet.getRows();// 总行数
			String n = sheet.getName();// 获得工作薄名称
			List<YxsoeUtilBean> list = new ArrayList();
			for (int i = 1; i < rows - 1; i++)
			{
				YxsoeUtilBean soe = new YxsoeUtilBean();
				Cell t0 = sheet.getCell(0, i);// 通过指定行和列得到某一个具体的内容这里3列2行
				soe.gtime = t0.getContents();
				Cell t1 = sheet.getCell(1, i);// 通过指定行和列得到某一个具体的内容这里3列2行
				soe.gms = t1.getContents();
				Cell t2 = sheet.getCell(2, i);
				soe.id = t2.getContents();
				Cell t3 = sheet.getCell(3, i);
				soe.tanme = t3.getContents();
				Cell t4 = sheet.getCell(4, i);
				soe.src = t4.getContents();
				Cell t5 = sheet.getCell(5, i);
				soe.value = t5.getContents();
				list.add(soe);
			}
			return list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
