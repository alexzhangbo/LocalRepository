package com.lunengsoft.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YkSucss {//PDS_Res_yk_op（遥控操作记录表）
  public String gtime; //操作时间
  public String id;  //开关ID
  public String tname; //开关名称
  public String src; //描述
  public String value; //操作
  public String type;  //其他系统0 /本系统
  public String OPERATE_TC; //用户班组
  public Boolean cg; //是否成功
  Date gdate=null;// 设置的中间量
  
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
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
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
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getOPERATE_TC() {
	return OPERATE_TC;
}
public void setOPERATE_TC(String oPERATETC) {
	OPERATE_TC = oPERATETC;
}
  
  
  
  

}
