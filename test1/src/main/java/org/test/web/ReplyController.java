package org.test.web;

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
	public void replyRegister(ReplyDTO dto) {
		System.out.println(dto);
		service.replyRegister(dto);
		
	}
	
	@GetMapping("getrlist/{tno}")
	public void getReplyList(@PathVariable int tno) {
		
		
		
	}
	
}
