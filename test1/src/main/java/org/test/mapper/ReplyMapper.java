package org.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.test.dto.ReplyDTO;

public interface ReplyMapper {
	@Insert("insert into tbl_reply (content,uid,tno) values(#{content},#{uid},#{tno})")
	public void replyRegister(ReplyDTO dto);
	
	@Select("select * from tbl_reply where tno = #{tno} order by rno desc offset 0 rows fetch next 10 rows only")
	public List<ReplyDTO> getReply(int tno);
	
	@Delete("delete from tbl_reply where rno=#{rno}")
	public void deleteReply(int rno);
	
	@Update("update tbl_reply set content=#{content} where rno=#{rno}")
	public void modifyReply(ReplyDTO dto);
	
}
