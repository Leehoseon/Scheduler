package org.test.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.test.dto.UserDTO;

public interface UserMapper {
	
	@Insert("insert into tbl_user (uid,upw,uname,uemail) values(#{uid},#{upw},#{uname},#{uemail})")
	public void registerUser(UserDTO dto);
	
	@Select("select uid from tbl_user where uid=#{uid}")
	public String checkUser(String uid);
	
	@Select("select uid from tbl_user WHERE (uid LIKE #{uid}) AND (upw LIKE #{upw})")
	public String loginUser(String uid);
	
	@Select("select upw,uemail from tbl_user where uemail = #{uemail}")
	public UserDTO getUemail(UserDTO dto);

}
