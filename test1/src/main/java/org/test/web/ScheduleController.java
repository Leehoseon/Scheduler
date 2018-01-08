package org.test.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.dto.ScheduleDTO;
import org.test.service.ScheduleService;

@RestController
@RequestMapping("/schedule/*")
public class ScheduleController {
	
	@Autowired
	ScheduleService service;

	@PostMapping("/regist")
	public void  registSchedule(@RequestBody ScheduleDTO dto) {
		
		System.out.println(dto);
		service.registSchedule(dto);
	}
	
	@PutMapping("/modify")
	public void  modifySchedule(@RequestBody ScheduleDTO dto) {
		
		System.out.println(dto+"modmodmodmod");
		service.modifySchedule(dto);
	}
	
	@GetMapping("/getSchedule/{uid}")
	public List<ScheduleDTO> getSchedule(@PathVariable String uid) {
		
		System.out.println(uid);
		
		return service.getSchedule(uid);
		
	}
	
	@DeleteMapping("/delete")
	public void deleteSchedule(@RequestBody ScheduleDTO dto) {
		
		System.out.println("del"+dto);
		
		service.deleteSchedule(dto);
		
	}
	
	
	
	
}
