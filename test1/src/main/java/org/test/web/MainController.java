package org.test.web;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.dto.BoardDTO;
import org.test.dto.Criteria;
import org.test.dto.UserDTO;
import org.test.service.FileService;
import org.test.service.UserService;
import org.test.service.BoardService;

@Controller
@RequestMapping("/tomcat/*")
public class MainController {
	
	
	@Autowired
	BoardService service;
	@Autowired
	FileService fservice;
	@Autowired
	UserService uservice;
	
	@GetMapping("/scheduler")
	public void scheduler(HttpSession session,Model model){
		
		String uid = (String) session.getAttribute("userId");
		
		model.addAttribute("uid",uid);
		
	}
	
	@GetMapping("/register")
	public void register(HttpSession session,Model model){
		
		String uid = (String) session.getAttribute("userId");
		
		model.addAttribute("uid",uid);
		
	}
	
	@PostMapping("/register")
	public void registerPost(BoardDTO dto){
		
				
	}
	
	@GetMapping("/list")
	public void list(Criteria cri,Model model){
		
		model.addAttribute("list",service.getList(cri));
		model.addAttribute("count",service.getCount());
		
	}
	
	@GetMapping("/view")
	public void view(int tno,Model model,HttpSession session){
		String uid = (String) session.getAttribute("userId");
		
		model.addAttribute("uid",uid);
		model.addAttribute("view",service.getView(tno));
		
		
	}
	
	@GetMapping("/modify")
	public void modify(int tno,Model model){
					
		model.addAttribute("view",service.getView(tno));
		
	}
	
	@PostMapping("/modify")
	public String modifyPost(BoardDTO dto){
		System.out.println(dto);
		service.modify(dto);
		
		int tno = dto.getTno();
		
		return "redirect:/tomcat/view?tno="+tno+"";
	}
	
	@GetMapping("/remove")
	public String remove(int tno){
		fservice.delTno(tno);			
		service.remove(tno);
		
		return "redirect:/tomcat/list";
		
	}
	
	@GetMapping("/chkuid/{uid}")
	public @ResponseBody String checkUserId(@PathVariable("uid")String uid){
		
		System.out.println(uid);
		
		return uservice.userCheck(uid);
	}
	
	@GetMapping("/login")
	public void login(){
		
	}
	
	@PostMapping("/login")
	public String loginPost(UserDTO dto, Model model){
		
		String uid = dto.getUid();
		
		String getUid = uservice.loginUser(uid);
		
		UserDTO udto = new UserDTO();
		
		udto.setUid(getUid);
		
		System.out.println(dto);
		
		model.addAttribute("userId",udto);
		
		return "redirect:/tomcat/list";
						
	}
	
	
	@GetMapping("/userregister")
	public void userRegister(){
		
		
	}
	
	@PostMapping("/userregister")
	public String userRegisterPost(UserDTO dto){

		uservice.userRegister(dto);
		
		return "redirect:/tomcat/list";
		
	}
	
	@GetMapping("/logout")
	public void logout(){
		
		
	}
	

}
