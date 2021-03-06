package org.test.service;

import java.util.List;

import org.test.dto.ScheduleDTO;

public interface ScheduleService {
	
	public void registSchedule(ScheduleDTO dto);
	
	public List<ScheduleDTO> getSchedule(String uid);
	
	public void modifySchedule(ScheduleDTO dto);
	
	public void deleteSchedule(ScheduleDTO dto);

}
