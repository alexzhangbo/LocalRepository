package com.lunengsoft.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YxdzUtilBean
{ // 遥信动作变位记录表的实体bean
	public String id; // 开关ID
	public String gtime; // 时间
	public String tname; // 开关的名称
	public String src; // 变位描述
	public String value; // 变位
	public String type; // 变位原因
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

	public String getTname()
	{
		return tname;
	}

	public void setTname(String tname)
	{
		this.tname = tname;
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
