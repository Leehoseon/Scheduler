package org.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.test.dto.ScheduleDTO;

public interface ScheduleMapper {
	
	@Insert("insert into tbl_schedule(uid,content,sdate) values(#{uid},#{content},#{sdate})")
	public void registSchedule(ScheduleDTO dto);
	
	@Select("select sdate,content from tbl_schedule WHERE (uid LIKE #{uid})")
	public List<ScheduleDTO> getSchedule(String uid);
	
	@Update("update tbl_schedule set content = #{content}, upddate = getdate() where sdate = #{sdate} and uid = #{uid}")
	public void modifySchedule(ScheduleDTO dto);
	
	@Delete("delete from tbl_schedule where sdate = #{sdate} and uid = #{uid}")
	public void deleteSchedule(ScheduleDTO dto);
	
}
