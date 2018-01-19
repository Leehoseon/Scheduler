package org.test.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.test.dto.ReplyDTO;

public interface ReplyMapper {
	@Insert("insert into tbl_reply (content,writer,tno) values(#{content},#{writer},#{tno})")
	public void replyRegister(ReplyDTO dto);
	
	@Select("select * from tbl_reply where tno = #{tno}")
	public void getReply(int tno);
	
}
