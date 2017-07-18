package com.lunengsoft.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YxsoeUtilBean
{ // 遥信动作SOE记录表实体类
	public String id; // 开关id
	public String gtime;// SOE时间
	public String gms; // soe毫秒
	public String tanme; // 设备名称
	public String src; // 动作描述(例如：合闸、分闸)
	public String value; // 变位(分闸0/合闸1)
	Date gdate = null;// 设置的中间量

	public Date getGdate()
	{

		if (gdate == null)
		{
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try
			{
				gdate = ft.parse(gtime);

			}
			catch (Exception e)
			{
			}
		}
		return gdate;
	}

	public void setGdate(Date gdate)
	{
		this.gdate = gdate;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getGtime()
	{
		return gtime;
	}

	public void setGtime(String gtime)
	{
		this.gtime = gtime;
	}

	public String getGms()
	{
		return gms;
	}

	public void setGms(String gms)
	{
		this.gms = gms;
	}

	public String getTanme()
	{
		return tanme;
	}

	public void setTanme(String tanme)
	{
		this.tanme = tanme;
	}

	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

}
