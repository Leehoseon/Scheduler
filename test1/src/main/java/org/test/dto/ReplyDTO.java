package org.test.dto;

import java.sql.Date;

public class ReplyDTO {
	private int rno,tno;
	private String writer,content;
	private Date regdate,upddate,deldate;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	public Date getDeldate() {
		return deldate;
	}
	public void setDeldate(Date deldate) {
		this.deldate = deldate;
	}
	@Override
	public String toString() {
		return "ReplyDTO [rno=" + rno + ", tno=" + tno + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", upddate=" + upddate + ", deldate=" + deldate + "]";
	}
	
}
