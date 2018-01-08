package org.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.dto.ScheduleDTO;
import org.test.mapper.ScheduleMapper;

@Service
public class SchdeultServiceImpl implements ScheduleService {
	
	@Autowired
	ScheduleMapper mapper;
	
	@Override
	public void registSchedule(ScheduleDTO dto) {
		
		mapper.registSchedule(dto);

	}

	@Override
	public List<ScheduleDTO> getSchedule(String uid) {
		return mapper.getSchedule(uid);
	}

	@Override
	public void modifySchedule(ScheduleDTO dto) {
		mapper.modifySchedule(dto);
		
	}

	@Override
	public void deleteSchedule(ScheduleDTO dto) {
		
		mapper.deleteSchedule(dto);
		
	}

}
