package org.test.dto;

public class FileDTO {
	
	private String originalname,uploadname,thumbname,p_thumbname,regdate;
	int tno;
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	@Override
	public String toString() {
		return "FileDTO [originalname=" + originalname + ", uploadname=" + uploadname + ", thumbname=" + thumbname
				+ ", p_thumbname=" + p_thumbname + ", regdate=" + regdate + ", tno=" + tno + "]";
	}

	

}
