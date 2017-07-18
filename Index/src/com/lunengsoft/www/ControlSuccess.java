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
import com.lunengsoft.bean.YkSucss;
import com.lunengsoft.bean.YxdzUtilBean;

public class ControlSuccess
{// //{3) 遥控成功率

	// 读取数据源 (设备表（PDS_Res_dev)
	public List<YkSucss> readSuc(Workbook wb)
	{
		try
		{
			Sheet sheet = wb.getSheet("PDS_RES_YK_OP"); // 获取数据第一个表
			if (sheet == null)
			{
				System.out.println("无法读取工作薄");
			}
			int cols = sheet.getColumns(); // 获取总列数
			int rows = sheet.getRows();// 总行数
			String n = sheet.getName();// 获得工作薄名称
			// System.out.println("共有--->"+cols+"列"+"---->"+rows+"行");
			List<YkSucss> list = new ArrayList();


			for (int i = 1; i < rows; i++)
			{// 行
				if (!sheet.getCell(0, i).getContents().equals(""))
				{ // 从第一列第2行开始
					// System.out.println("进来哦");
					YkSucss dev = new YkSucss();
					Cell t0 = sheet.getCell(0, i);// 通过指定行和列得到某一个具体的内容这里3列2行
					dev.gtime = t0.getContents();

					Cell t1 = sheet.getCell(1, i);// 通过指定行和列得到某一个具体的内容这里3列2行
					// dev.tname = t1.getContents();
					dev.id = t1.getContents();
					Cell t2 = sheet.getCell(2, i);
					// dev.tid =t2.getContents();
					dev.tname = t2.getContents();
					Cell t3 = sheet.getCell(3, i);
					// dev.type=t3.getContents();
					dev.src = t3.getContents();
					Cell t4 = sheet.getCell(4, i);
					dev.value = t4.getContents();
					Cell t5 = sheet.getCell(5, i);
					dev.type = t5.getContents();

					// System.out.println(t0.getContents()+"-->"+t1.getContents()+"-->"+t2.getContents()+"-->"+t3.getContents()+"--->"+t4.getContents()+"--->"+t5.getContents());
					list.add(dev);

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

	public double gtYuc(Workbook wb, Date starttime, Date endtime)
	{
		try
		{
			List<YkSucss> readSuc = readSuc(wb); // 数据源总集合
			// 遥控成功率= 去掉多重操作后数据其中成功的次数/去掉多重操作后数据总数;

			// 将五分钟之内的多重遥控操作按照原则进行过滤，去掉多余的记录，得出记录条数c1
			// 防止没有获取到 sttime ,endtime

			List<String> list = new ArrayList<String>(); // 加入集合中所有不重复ID
			for (int i = 0; i < readSuc.size(); i++)
			{
				if (i == 0)
				{
					list.add(readSuc.get(i).id); // 获取到得第一个ID
				}
				else
				{
					if (!list.contains(readSuc.get(i).id)) // 如果在往后的ID中没有和这个ID相等的,那么就再次加入一条记录
					{
						list.add(readSuc.get(i).id);// 获得所有去掉重复之后的id
					}
				}
			}


			List<YkSucss> list2 = new ArrayList();
			for (int i = 0; i < list.size(); i++)// 循环id
			{

				List<YkSucss> list1 = new ArrayList();
				for (int j = 0; j < readSuc.size(); j++)
				{

					if (list.get(i).equals(readSuc.get(j).id))
					{ 
						list1.add(readSuc.get(j)); // 获得同一数值的ID对象集合,类似于[1,1,1,1]
					}
				}


				while (list1.size() > 0)
				{
					YkSucss ykSucss = (YkSucss) list1.get(0);
					ykSucss.cg = false;

					// if("0".equals(ykSucss.value)||"2".equals(ykSucss.value))
					// {
					// ykSucss.cg=true;
					// list2.add(ykSucss);
					// }
					list1.remove(0);

					int tp = list1.size();

					for (int j = 0; j < tp; j++)
					{
						if ((ykSucss.getGdate().getTime() - list1.get(j)
								.getGdate().getTime()) / 1000 < 150
								&& (ykSucss.getGdate().getTime() - list1.get(j)
										.getGdate().getTime()) / 1000 > -150)
						{
							if (ykSucss.value.equals(list1.get(j).value))
							{
								list1.remove(j);
							}

							j--;
							tp--;
						}

					}
					list2.add(ykSucss); //
				}

			}

			int k = 0;
	
			// 测试结果数据
			for (int i = 0; i < list2.size(); i++)
			{

				if (list2.get(i).value.equals("0")
						|| list2.get(i).value.equals("2"))
				{
					k = k + 1;
				}
			}

			double k1 = list2.size();
			double n = 100*k / k1;

			// double f=0;
			return n;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}

	}

}
