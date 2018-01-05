package org.test.dto;

import java.sql.Date;

public class ScheduleDTO {
	
	private String uid,sdate,content;
	private int sno;
	private Date regdate;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "ScheduleDTO [uid=" + uid + ", sdate=" + sdate + ", content=" + content + ", sno=" + sno + ", regdate="
				+ regdate + "]";
	}
	

}
