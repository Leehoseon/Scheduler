package org.test.dto;

import java.sql.Date;

public class ReplyDTO {
	private int rno,tno;
	private String uid,content,regdate,upddate,deldate;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUpddate() {
		return upddate;
	}
	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}
	public String getDeldate() {
		return deldate;
	}
	public void setDeldate(String deldate) {
		this.deldate = deldate;
	}
	@Override
	public String toString() {
		return "ReplyDTO [rno=" + rno + ", tno=" + tno + ", uid=" + uid + ", content=" + content + ", regdate="
				+ regdate + ", upddate=" + upddate + ", deldate=" + deldate + "]";
	}
	
	
}
