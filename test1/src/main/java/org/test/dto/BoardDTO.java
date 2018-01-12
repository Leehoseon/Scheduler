package org.test.dto;

import java.sql.Date;

public class BoardDTO {
	
	private int tno;
	private String title,writer,content;
	private Date regdate;
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	@Override
	public String toString() {
		return "BoardDTO [tno=" + tno + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regdate=" + regdate + "]";
	}

	
}
