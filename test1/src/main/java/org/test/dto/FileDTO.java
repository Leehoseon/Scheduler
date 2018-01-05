package org.test.dto;

import java.sql.Date;

public class FileDTO {
	
	private String originalname,uploadname,thumbname,p_thumbname,extension;
	private int tno,filesize;
	private Date upddate,deldate,regdate;
	public String getOriginalname() {
		return originalname;
	}
	public void setOriginalname(String originalname) {
		this.originalname = originalname;
	}
	public String getUploadname() {
		return uploadname;
	}
	public void setUploadname(String uploadname) {
		this.uploadname = uploadname;
	}
	public String getThumbname() {
		return thumbname;
	}
	public void setThumbname(String thumbname) {
		this.thumbname = thumbname;
	}
	public String getP_thumbname() {
		return p_thumbname;
	}
	public void setP_thumbname(String p_thumbname) {
		this.p_thumbname = p_thumbname;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "FileDTO [originalname=" + originalname + ", uploadname=" + uploadname + ", thumbname=" + thumbname
				+ ", p_thumbname=" + p_thumbname + ", extension=" + extension + ", tno=" + tno + ", filesize="
				+ filesize + ", upddate=" + upddate + ", deldate=" + deldate + ", regdate=" + regdate + "]";
	}
	
	
	
	

}
