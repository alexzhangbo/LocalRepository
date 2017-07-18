package com.lunengsoft.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pdtui {
	public String id; // 终端或者设备ID
	public String gtime;// 投退时间
	Date gdate=null;// 设置的中间量
	public String tname;// 终端或者设备名称
	public String src;// 描述 投入，退出
	public String value;// 投退标志 退出0 / 投入1

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGtime() {
		return gtime;
	}

	public void setGtime(String gtime) {
		this.gtime = gtime;
	}

	public Date getGdate() {

		if (gdate == null) {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				gdate = ft.parse(gtime);

			} catch (Exception e) {
			}
		}
		return gdate;
	}
	public void setGdate(Date gdate) {
		this.gdate = gdate;
	}
	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
