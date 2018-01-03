package org.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.test.dto.FileDTO;

public interface FileMapper {
	
	@Insert("insert into tbl_file (originalname,uploadname,thumbname,p_thumbname,tno) values(#{originalname},#{uploadname},#{thumbname},#{p_thumbname},(SELECT IDENT_CURRENT('tbl_board')))")
	public void putFile(FileDTO dto);

	@Select("select * from tbl_file where tno=#{tno}")
	public List<FileDTO> getFlist(int tno);
	
	@Delete("delete from tbl_file where uploadname = #{uploadname}")
	public void delFile(FileDTO dto);
	
	@Delete("delete from tbl_file where tno = #{tno}")
	public void delTno(int tno);
	
	@Update("insert into tbl_file (originalname,uploadname,thumbname,p_thumbname,tno) values(#{originalname},#{uploadname},#{thumbname},#{p_thumbname},#{tno})")
	public void addFile(FileDTO dto);
	
	
}
