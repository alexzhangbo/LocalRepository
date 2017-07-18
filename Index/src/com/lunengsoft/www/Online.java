package com.lunengsoft.www;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.lunengsoft.bean.*;

public class Online {
	public List<ZhongDuan> listTermi = null;
	public List<Pdtui> listTermiTT = null;
	
	public List<PdUtilBean> pdBeanlist = null;
	public int count=0;

	// 本方法用作读取数据源
	public void readExel(Workbook wb) {
		try {

			Sheet sheet = wb.getSheet("PDS_RES_TERMI"); // 获取数据第一个表
			if (sheet == null) {
				System.out.println("无法读取工作薄");
			}
			int cols = sheet.getColumns(); // 获取总列数
			int rows = sheet.getRows();// 总行数
			count=rows;
			String n = sheet.getName();// 获得工作薄名称

			List<ZhongDuan> list = new ArrayList<ZhongDuan>();

			for (int i = 1; i < rows; i++) {

				ZhongDuan zd = new ZhongDuan();
				Cell t0 = sheet.getCell(0, i);// 通过指定行和列得到某一个具体的内容这里3列2行
				zd.id = t0.getContents();

				Cell t1 = sheet.getCell(1, i);// 通过指定行和列得到某一个具体的内容这里3列2行
				zd.Tname = t1.getContents();

				list.add(zd);

				System.out.println("读取第" + i + "条纪录:ID＝" + zd.id + ",名称＝"
						+ zd.Tname + ".");
			}

			listTermi = list;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readEx(Workbook wb) {
		try {

			Sheet sheet = wb.getSheet("PDS_RES_TERMIDEV_TT"); // 获取数据第一个表
			if (sheet == null) {
				System.out.println("无法读取工作薄");
			}
			int cols = sheet.getColumns(); // 获取总列数
			int rows = sheet.getRows();// 总行数

			String n = sheet.getName();// 获得工作薄名称

			List<Pdtui> list = new ArrayList<Pdtui>();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			for (int i = 1; i < rows; i++) {

				Pdtui pd = new Pdtui();
				Cell t0 = sheet.getCell(0, i);// 通过指定行和列得到某一个具体的内容这里3列2行
				pd.gtime = t0.getContents();
				java.util.Date myDate = ft.parse(t0.getContents());
				pd.setGdate(myDate);
				// System.out.println(pd.gtime);

				Cell t1 = sheet.getCell(1, i);
				pd.setId(t1.getContents());

				Cell t2 = sheet.getCell(2, i);
				pd.setTname(t2.getContents());

				Cell t3 = sheet.getCell(3, i);
				pd.setSrc(t3.getContents());

				Cell t4 = sheet.getCell(4, i);

				pd.setValue(t4.getContents());

				System.out.println("读取第" + i + "条纪录:ID＝" + pd.id + ",名称＝"
						+ pd.tname + ",时间＝" + pd.gtime + ",动作＝" + pd.getSrc()
						+ ".");

				list.add(pd);
			}

			listTermiTT = list;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<PdUtilBean> getResult(Workbook wb, Date starttime,
			Date endtime) {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			List<Pdtui> list = new ArrayList<Pdtui>(); // 中间集合,用来存放目标对象

			pdBeanlist = new ArrayList<PdUtilBean>();

			if (listTermi != null && listTermiTT != null) { // 两个数据源都不为空的情况
				for (int i = 0; i < listTermi.size(); i++) {
					list.clear();
					for (int j = 0; j < listTermiTT.size(); j++) {
						if (listTermiTT.get(j).getId()
								.equals(listTermi.get(i).getId())
								&& listTermiTT.get(j).getGdate().after(starttime)
								&& listTermiTT.get(j).getGdate().before(endtime)){
							list.add(listTermiTT.get(j)); // 把终端表中和配电表中id相同的对象放入集合中
						}
					}
				/*	
					if("536871060".equals(listTermi.get(i).id))
					{
						System.out.println();
					}
*/
					if (list.size() > 0) {

						// 下面是对符合条件的目标集合中的对象进行处理
						Pdtui PdtuiTC = null;// 退出时间
						Pdtui PdtuiTR = null;// 投入时间

						for (int j = 0; j < list.size(); j++) // 这个list就是符合条件的目标集合
						{
							if ("0".equals(list.get(j).value)) // 获取最小退出时间
							{
								if (PdtuiTC == null) 
								{

									PdtuiTC = list.get(j);

								}
								else if (PdtuiTC.getGdate().after(list.get(j).getGdate())) 
								{
									PdtuiTC = list.get(j);
								}

							} 
							else // 获取最小投入时间
							{

								if (PdtuiTR == null) 
								{
									PdtuiTR = list.get(j);
								}
								else if (PdtuiTR.getGdate().after(list.get(j).getGdate())) 
								{
									PdtuiTR = list.get(j);

								}
							}

						}
	
						if (PdtuiTC==null && PdtuiTR==null)
						{
							System.out.println("没有投退纪录");
						}
						else if (PdtuiTC==null)
						{
							PdUtilBean pd = new PdUtilBean();
							pd.setId(PdtuiTR.id);
							pd.setTc(ft.format(starttime));
							pd.setTr(PdtuiTR.gtime);
							pd.setTrc(getTwoDay(PdtuiTR.getGdate(), starttime));
							pdBeanlist.add(pd);
							System.out.println("设备:"+pd.id+"在"+pd.Tc+"至"+pd.Tr+"之间退出."+pd.Trc);
						}
						else if (PdtuiTR==null)//后面处理
						{
							System.out.println("没有投入纪录");
						}
						else if (PdtuiTC.getGdate().compareTo(PdtuiTR.getGdate())>0)// 最小退出时间<最小投入时间
						{
							PdUtilBean pd = new PdUtilBean();
							pd.setId(PdtuiTC.id);
							pd.setTc(ft.format(starttime));
							pd.setTr(PdtuiTR.gtime);
							pd.setTrc(getTwoDay(PdtuiTR.getGdate(), starttime));
							pdBeanlist.add(pd);
							System.out.println("设备:"+pd.id+"在"+pd.Tc+"至"+pd.Tr+"之间退出."+pd.Trc);
						}

						for (int x = 0; x < list.size(); x++) {

							if (PdtuiTC != null) 
							{
								if ("0".equals(list.get(x).value))// 退出
								{

									PdtuiTR = null;
									for (int j = 0; j < list.size(); j++) {
										if ("1".equals(list.get(j).value))// 投入
										{
											// list.get(j).gdate.compareTo(PdtuiTC.gdate)>0&&
											// list.get(j).gdate.compareTo(PdtuiTR.gdate)<0
											if (PdtuiTR == null
													&& 
															list.get(j).getGdate().after(
															PdtuiTC.getGdate()) )
															{
												PdtuiTR = list.get(j);
											} else if (
													list.get(j).getGdate().after(PdtuiTC.getGdate())
													&& 
															list.get(j).getGdate().before(PdtuiTR.getGdate())
															) {
												PdtuiTR = list.get(j);
											}
										}
									}

									if (PdtuiTR != null) {
										PdUtilBean pd1 = new PdUtilBean();
										pd1.setId(PdtuiTC.id);
										pd1.setTc(PdtuiTC.gtime);
										pd1.setTr(PdtuiTR.gtime);
										pd1.setTrc(getTwoDay(PdtuiTR.gtime,
												PdtuiTC.gtime));
										pdBeanlist.add(pd1);
										System.out.println("设备:"+pd1.id+"在"+pd1.Tc+"至"+pd1.Tr+"之间退出."+pd1.Trc);


										PdtuiTC = null;
										for (int j = 0; j < list.size(); j++) {

											if ("0".equals(list.get(j).value)) {
												// list.get(j).gdate.compareTo(PdtuiTR.gdate)>0
												if (list.get(j).getGdate().after(PdtuiTR.getGdate())
														&& PdtuiTC == null) {
													PdtuiTC = list.get(j);
												} else if (
														list.get(j).getGdate().after(PdtuiTR.getGdate())
										
														&& 
																list.get(j).getGdate().before(PdtuiTC.getGdate())
															) {
													PdtuiTC = list.get(j);
												}
											}

										}
									} else {
										PdUtilBean pd1 = new PdUtilBean();
										pd1.setId(PdtuiTC.id);
										pd1.setTc(PdtuiTC.gtime);
										pd1.setTr(ft.format(endtime));
										pd1.setTrc(getTwoDay(endtime,
												PdtuiTC.getGdate()));
										pdBeanlist.add(pd1);
										System.out.println("设备:"+pd1.id+"在"+pd1.Tc+"至"+pd1.Tr+"之间退出."+pd1.Trc);
									}

								}
							}

						}

					}

				}

			} 
			else 
			{
				System.out.println("没有获取到数据源");
			}

		return pdBeanlist;

	}



	// 本方法用作解决两个日期之间相差的分钟数
	public static long getTwoDay(String sj1, String sj2) {// 计算两个日期间的相差的毫秒
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		long day = 0;
		try {

			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime())/1000 ;// 得到时间差毫秒
			
		} catch (Exception e) {
			return 0;
		}
		return day;
	}
	
	// 本方法用作解决两个日期之间相差的分钟数
	public static long getTwoDay(Date sj1, Date sj2) {// 计算两个日期间的相差的毫秒
	
		long day = 0;
		try {

			day = (sj1.getTime() - sj2.getTime()) / 1000;// 得到时间差毫秒

		} catch (Exception e) {
			return 0;
		}
		return day;
	}

}
