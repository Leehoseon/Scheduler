package org.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.test.dto.FileDTO;

public interface FileMapper {
	
	@Insert("insert into tbl_file (originalname,uploadname,thumbname,p_thumbname,extension,filesize,tno) values(#{originalname},#{uploadname},#{thumbname},#{p_thumbname},#{extension},#{filesize},(SELECT IDENT_CURRENT('tbl_board')))")
	public void putFile(FileDTO dto);

	@Select("select * from tbl_file where tno=#{tno} and deldate is null")
	public List<FileDTO> getFlist(int tno);
	
	@Delete("update tbl_file set deldate = getdate() where uploadname=#{uploadname}")
	public void delFile(FileDTO dto);
	
	/*@Update("update tbl_file set upddate = getdate()")
	public void delFile();*/
	
	@Delete("delete from tbl_file where tno = #{tno}")
	public void delTno(int tno);
	
	@Insert("insert into tbl_file (originalname,uploadname,thumbname,p_thumbname,extension,filesize,tno) values(#{originalname},#{uploadname},#{thumbname},#{p_thumbname},#{extension},#{filesize},#{tno})")
	public void addFile(FileDTO dto);

	@Select("select count(*) from tbl_file WHERE (tno LIKE (SELECT IDENT_CURRENT('tbl_board')))")
	public int getFileCount();
	
	@Select("SELECT IDENT_CURRENT('tbl_board')")
	public int getTno();
}
