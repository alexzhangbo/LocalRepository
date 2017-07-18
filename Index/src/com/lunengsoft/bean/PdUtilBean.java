package com.lunengsoft.bean;

public class PdUtilBean {
  public String id; //终端的id
  public String Tr; //投入时间
  public String Tc;//退出时间
  public long Trc; //时间间隔(退出时间-投入时间)
  public String TotalTrc;//所有时间间隔之和
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTr() {
	return Tr;
}
public void setTr(String tr) {
	Tr = tr;
}
public String getTc() {
	return Tc;
}
public void setTc(String tc) {
	Tc = tc;
}
public long getTrc() {
	return Trc;
}
public void setTrc(long trc) {
	Trc = trc;
}
public String getTotalTrc() {
	return TotalTrc;
}
public void setTotalTrc(String totalTrc) {
	TotalTrc = totalTrc;
}
}
