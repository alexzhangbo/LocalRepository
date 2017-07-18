package com.lunengsoft.www;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.lunengsoft.bean.PdevUtilBean;
import com.lunengsoft.bean.YxdzUtilBean;

public class ControlUse
{
	List<String> listDev =null;

	// 从遥信动作变位记录表（PDS_Res_yx_bw）中选出统计时段的变位原因（Type字段）是遥控（值为1）的记录
	public double getYtotal(Workbook wb, Date starttime, Date endtime)
	{
		listDev = new ArrayList<String>();
		List<YxdzUtilBean> yxdz = readYxdzBw(wb); // 遥信动作变位记录的数据源集合
		List<PdevUtilBean> pdev = readDev(wb); // 设备表
		

		int n = 0; // y用来记录变位原因type=1的个数
		for (int i = 0; i < yxdz.size(); i++)
		{
			if (yxdz.get(i).type.equals("1"))
			{
				n = n + 1;
			}

		}


		int m = 0; // 三遥开关的动作记录次数

		for (int j = 0; j < yxdz.size(); j++)
		{

				if (listDev.contains(yxdz.get(j).getId()))
				{
						m = m + 1;
				}

		}

		double yxsy = 100*n / m;
		System.out.println("遥控使用率－－－＞" + yxsy);

		return yxsy;
	}

	// 读取数据源 (遥信动作变位记录表（PDS_Res_yx_bw)
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

				// System.out.println("--gtime-->"+ yxbw.gtime+"--id--->"+
				// yxbw.id);
			}
			//

			return list;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	// 读取数据源 (设备表（PDS_Res_dev)
	public List<PdevUtilBean> readDev(Workbook wb)
	{
		try
		{
			Sheet sheet = wb.getSheet("PDS_RES_DEV"); // 获取数据第一个表
			if (sheet == null)
			{
				System.out.println("无法读取工作薄");
			}
			int cols = sheet.getColumns(); // 获取总列数
			int rows = sheet.getRows();// 总行数
			String n = sheet.getName();// 获得工作薄名称
			// System.out.println("共有--->"+cols+"列"+"---->"+rows+"行");
			List<PdevUtilBean> list = new ArrayList();
			// 如果某一行的ID是空,那么这一行记录废弃


			for (int i = 1; i < rows; i++)
			{// 行
				if (!sheet.getCell(0, i).getContents().equals(""))
				{ // 从第一列第2行开始
					System.out.println("进来哦");
					PdevUtilBean dev = new PdevUtilBean();
					Cell t0 = sheet.getCell(0, i);// 通过指定行和列得到某一个具体的内容这里3列2行
					dev.id = t0.getContents();
					Cell t1 = sheet.getCell(1, i);// 通过指定行和列得到某一个具体的内容这里3列2行
					dev.tname = t1.getContents();
					Cell t2 = sheet.getCell(2, i);
					dev.tid = t2.getContents();
					Cell t3 = sheet.getCell(3, i);
					dev.type = t3.getContents();
					// System.out.println(t0.getContents()+"-->"+t1.getContents()+"-->"+t2.getContents()+"-->"+t3.getContents());
					//list.add(dev);
					
					if("3".equals(dev.type))
					{
						listDev.add(dev.id);
					}

				}
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
