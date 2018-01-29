package org.test.mapper;

import org.apache.ibatis.annotations.Insert;
import org.test.dto.DetectDTO;

public interface DetectMapper {
	
	@Insert("insert into tbl_detect(agehigh,ageLow,gender,smile) values(#{ageHigh},#{ageLow},#{gender},#{smile})")
	public void regDetect(DetectDTO ddto);
		
}
