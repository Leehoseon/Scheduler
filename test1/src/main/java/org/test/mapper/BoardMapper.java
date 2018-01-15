package org.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.test.dto.BoardDTO;
import org.test.dto.Criteria;

public interface BoardMapper {
	
	/*@Select("select * from tbl_board")*/
	public List<BoardDTO> getList(Criteria cri);
	
	@Insert("insert into tbl_board(title,writer,content) values(#{title},#{writer},#{content})")
	public void putBoard(BoardDTO dto);
	
	@Select("select * from tbl_board where tno=#{tno}")
	public BoardDTO getView(int tno);
	
	@Delete("delete from tbl_board where tno=#{tno}")
	public void removeBoard(int tno);
	
	@Update("update tbl_board set title=#{title}, writer=#{writer}, content=#{content} where tno=#{tno}")
	public void modifyBoard(BoardDTO dto);
	
	/*@Select("select count(*) from tbl_board")*/
	public int getCount(Criteria Cri);
	

}
