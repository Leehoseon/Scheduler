package org.test.web;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.dto.BoardDTO;
import org.test.dto.Criteria;
import org.test.dto.UserDTO;
import org.test.service.FileService;
import org.test.service.UserService;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AgeRange;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.Attribute;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/scheduler")
	public void scheduler(HttpSession session,Model model){
		
		String uid = (String) session.getAttribute("userId");
		
		model.addAttribute("uid",uid);

	      String photo = "jo.jpg";
	      String bucket = "facerekognitiontest";

	      AWSCredentials credentials;
	      try {
	         credentials = new ProfileCredentialsProvider("C:/Users/eltove-ho/leehoseon.aws/test.txt","leehoseon").getCredentials();
	      } catch (Exception e) {
	         throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
	            + "Please make sure that your credentials file is at the correct "
	            + "location (/Users/userid.aws/credentials), and is in a valid format.", e);
	      }

	      AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder
	         .standard()
	         .withRegion(Regions.US_EAST_1)
	         .withCredentials(new AWSStaticCredentialsProvider(credentials))
	         .build();

	      DetectFacesRequest request = new DetectFacesRequest()
	         .withImage(new Image()
	            .withS3Object(new S3Object()
	               .withName(photo)
	               .withBucket(bucket)))
	         .withAttributes(Attribute.ALL);
	      // Replace Attribute.ALL with Attribute.DEFAULT to get default values.

	      try {
	         DetectFacesResult result = rekognitionClient.detectFaces(request);
	         List < FaceDetail > faceDetails = result.getFaceDetails();

	         for (FaceDetail face: faceDetails) {
	            if (request.getAttributes().contains("ALL")) {
	               AgeRange ageRange = face.getAgeRange();
	               System.out.println("The detected face is estimated to be between "
	                  + ageRange.getLow().toString() + " and " + ageRange.getHigh().toString()
	                  + " years old.");
	               System.out.println("Here's the complete set of attributes:");
	            } else { // non-default attributes have null values.
	               System.out.println("Here's the default set of attributes:");
	            }

	            ObjectMapper objectMapper = new ObjectMapper();
	            try {
					System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(face));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }

	      } catch (AmazonRekognitionException e) {
	         e.printStackTrace();
	      }
		
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
	public void list(Criteria cri,Model model,HttpSession session){
		String uid = (String) session.getAttribute("userId");
		
		model.addAttribute("uid",uid);
		
		model.addAttribute("list",service.getList(cri));
		model.addAttribute("count",service.getCount());
		
		int page = cri.getPage();
		if(page == 0) {
			page=1;
		}
		
		model.addAttribute("tno", page);
		
	}
	
	@GetMapping("/view")
	public void view(int tno,Model model,HttpSession session){
		String uid = (String) session.getAttribute("userId");
		
		model.addAttribute("uid",uid);
		model.addAttribute("view",service.getView(tno));
		
		
	}
	
	@GetMapping("/modify")
	public void modify(int tno,Model model,HttpSession session){
		String uid = (String) session.getAttribute("userId");
		
		model.addAttribute("uid",uid);
					
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
	
	@PostMapping("/findpassword")
	public void findPasswordPost(UserDTO dto){
		
		System.out.println(dto);
		
		UserDTO udto = uservice.getUemail(dto);
		
		System.out.println(udto);
		
		String upw = udto.getUpw();
		String uemail = udto.getUemail();
		
		if(upw != null) {
			SimpleMailMessage message = new SimpleMailMessage();
			
			  message.setFrom("zzdlghtjs@naver.com");

			  message.setTo("zzdlghtjs@naver.com");

			  message.setSubject("password");

			  message.setText("비밀번호 안내해드립니다"+upw);
			  
			  mailSender.send(message);
			  
		}else if (upw ==null) {
			
		}
		
	}
	@GetMapping("/findpassword")
	public void findPassword(UserDTO dto){
		
	}
	
	@GetMapping("/chkuid/{uid}")
	public @ResponseBody String checkUserId(@PathVariable("uid")String uid){
		
		System.out.println(uid);
		
		String check = uservice.userCheck(uid);
		
		if(check != null) {
			return "can't";
		}else {
			return "can";
		}
		
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
		
		return "redirect:/tomcat/login";
		
	}
	
	@GetMapping("/logout")
	public void logout(){
		
		
	}
	

}
