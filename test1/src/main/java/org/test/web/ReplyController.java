package org.test.web;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.dto.ReplyDTO;
import org.test.service.ReplyService;

@RestController
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Autowired
	ReplyService service;
	
	@PostMapping("/register")
	public String replyRegister(ReplyDTO dto) {
		System.out.println(dto);
		service.replyRegister(dto);
		String yes = "Y";
		return "yes";
	}
	
	@GetMapping("getrlist/{tno}")
	public List<ReplyDTO> getReplyList(@PathVariable int tno) {
		List<ReplyDTO> tdto ;
		
		tdto = service.getReply(tno);
		
		for (int i = 0; i < tdto.size(); i++) {
			
			String getDate = tdto.get(i).getRegdate();
			
			int first = getDate.length();
			int last = getDate.lastIndexOf(".");
			
			String target = getDate.substring(0,last);
			
			tdto.get(i).setRegdate(target);
			
		}
		
		System.out.println(tdto);
		
		System.out.println(tno);
		
		return tdto;
		
	}
	
	@GetMapping("delete/{rno}")
	public void deleteReply(@PathVariable int rno) {
		
		System.out.println(rno);
		
		service.deleteReply(rno);
		
	}
	
	@PostMapping("/modify")
	public void modifyReply(ReplyDTO dto) {
		
		System.out.println(dto);
		
		service.modifyReply(dto);
		
	}
	
}
