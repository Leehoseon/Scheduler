package org.test.web;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;


import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.test.dto.BoardDTO;
import org.test.dto.FileDTO;
import org.test.service.FileService;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.Attribute;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;

import org.test.service.BoardService;



@RestController
@RequestMapping("/file/*")
public class UploadController {
	
	@Autowired BoardService service;
	@Inject FileService fservice;
	
	@GetMapping("/getThumb/{thumbname:.+}")
	public byte[] getFlist(@PathVariable("thumbname")String thumbname ){
		
		/*int Idx = thumbname.lastIndexOf(".");
		System.out.println(Idx);
		int sdf = thumbname.length();
		System.out.println(sdf);
		
		String nthumbname = thumbname.substring(Idx, Idx+4);
		System.out.println(nthumbname);*/
		try
			{
			File file = new File("C:\\upload\\"+ thumbname );
			System.out.println(thumbname);
					
			return FileUtils.readFileToByteArray(file);
			
			}
		
		catch (Exception e) {
			
			return  null;
		}
	}
	
	@GetMapping("/getPthumb/{p_thumbname:.+}")
	
	public byte[] getPlist(@PathVariable("p_thumbname")String p_thumbname ){
		
		/*int Idx = thumbname.lastIndexOf(".");
		System.out.println(Idx);
		int sdf = thumbname.length();
		System.out.println(sdf);
		
		String nthumbname = thumbname.substring(Idx, Idx+4);
		System.out.println(nthumbname);*/
		try
			{
			File file = new File("C:\\upload\\"+ p_thumbname);
			System.out.println(p_thumbname);
					
			return FileUtils.readFileToByteArray(file);
			
			}
		
		catch (Exception e) {
			
			return  null;
		}
		
	}
	
	@PostMapping("/registfile")
	public void regFile(List<MultipartFile> file) throws IOException{
		
		int size = file.size();
		
		for(int i = 0; i < size; i++ ) {
			
			int filesize = (int) file.get(i).getSize();
			
			FileDTO dto = new FileDTO();
			
			String target = "C:\\upload\\";
			
			String contentType = file.get(i).getContentType().toString();
			
			System.out.println(contentType);
			
			String originalname = file.get(i).getOriginalFilename();
			
			int start = originalname.lastIndexOf(".");
			
			int last = originalname.length();
			
			String extension = originalname.substring(start, last);
			
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        Calendar c1 = Calendar.getInstance();

	        String strToday = sdf.format(c1.getTime());*/
			
			int fileCount = fservice.getFileCount();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fileCount ++;
			
			System.out.println(fileCount);
			
			String s = String.format("%02d", fileCount);
			int t = fservice.getTno();

			String uploadname = "B00000"+t+"_"+s+"_"+"MAIN";
			
			String thumbname = "B00000"+t+"_"+s+"_"+"THUM";
			
			String p_thumbname = "B00000"+t+"_"+s+"_"+"THUM";
			
			System.out.println(uploadname);
			
			System.out.println(originalname);
			
			OutputStream out = new FileOutputStream("C:\\upload\\"+ uploadname + extension);
			
			FileCopyUtils.copy(file.get(i).getInputStream(), out);
									
			if(contentType.equals("image/jpeg")) {
						
				BufferedImage origin = ImageIO.read(file.get(i).getInputStream());
				
				BufferedImage destImg = Scalr.resize(origin, 
					            Scalr.Method.AUTOMATIC, 
					            Scalr.Mode.FIT_TO_HEIGHT,50
					            );
				
				ImageIO.write(destImg, "jpg", new FileOutputStream("C:\\upload\\" + thumbname + extension ));
				dto.setThumbname(thumbname);
				
			}if (contentType.equals("video/mp4")){
				
				System.out.println("it is video file..");

				dto.setP_thumbname(p_thumbname);
				OutputStream pout = new FileOutputStream("C:\\upload\\"+ p_thumbname + extension);
				
				FileCopyUtils.copy(file.get(i).getInputStream(), pout);
				
			}
			
			dto.setOriginalname(originalname);
			dto.setUploadname(uploadname);
			dto.setFilesize(filesize);
			dto.setExtension(extension);
			System.out.println(filesize);
			System.out.println(extension);

			fservice.putFile(dto);
			
			/*endTarget = target+ uploadname + extension;
			
			String photo=endTarget;

	        AWSCredentials credentials;
	        
	        try {
	            credentials = new ProfileCredentialsProvider("C:/Users/eltove-ho/leehoseon.aws/test.txt","leehoseon").getCredentials();
	        } catch (Exception e) {
	            throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
	                    + "Please make sure that your credentials file is at the correct "
	                    + "location (/Usersuserid.aws/credentials), and is in a valid format.", e);
	        }
	        ByteBuffer imageBytes = null;
	        try  {
	        	
	        	InputStream inputStream = new FileInputStream(new File(photo));
	            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
	        }catch (Exception e) {
				
			}
	        System.out.println("request전====================");
	        System.out.println(imageBytes);
	        System.out.println(photo);
	        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder
	          		.standard()
	          		.withRegion(Regions.US_EAST_1)
	        		.withCredentials(new AWSStaticCredentialsProvider(credentials))
	        		.build();

	        DetectLabelsRequest request = new DetectLabelsRequest()
	                .withImage(new Image()
	                        .withBytes(imageBytes))
	                .withMaxLabels(30)
	                ;
	        
	        System.out.println("request다음====================");
	        
	        try {

	            DetectLabelsResult result = rekognitionClient.detectLabels(request);
	            List <Label> labels = result.getLabels();

	            System.out.println("Detected labels for " + photo);
	            for (Label label: labels) {
	               System.out.println(label.getName() + ": " + label.getConfidence().toString());
	            }

	        } catch (AmazonRekognitionException e) {
	            e.printStackTrace();
	        }*/
			
		}
		
	}

	@PostMapping("/addfile/{tno}")
	public void addFile(List<MultipartFile> file,@PathVariable("tno") int tno) throws IOException{
		
		int size = file.size();
		
		for(int i = 0; i < size; i++ ) {
			
			int filesize = (int) file.get(i).getSize();
			
			FileDTO dto = new FileDTO();
			
			String contentType = file.get(i).getContentType().toString();
			
			System.out.println(contentType);
			
			String originalname = file.get(i).getOriginalFilename();
			
			int start = originalname.lastIndexOf(".");
			
			int last = originalname.length();
			
			String extension = originalname.substring(start, last);
			
			int fileCount = fservice.getFileCount();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fileCount ++;
			
			System.out.println(fileCount);
			
			String s = String.format("%02d", fileCount);
			int t = fservice.getTno();

			String uploadname = "B00000"+t+"_"+s+"_"+"MAIN";
			
			String thumbname = "B00000"+t+"_"+s+"_"+"THUM";
			
			String p_thumbname = "B00000"+t+"_"+s+"_"+"THUM";
			
			OutputStream out = new FileOutputStream("C:\\upload\\"+ uploadname + extension);
			
			FileCopyUtils.copy(file.get(i).getInputStream(), out);
									
			if(contentType.equals("image/jpeg")) {
						
				BufferedImage origin = ImageIO.read(file.get(i).getInputStream());
				
				BufferedImage destImg = Scalr.resize(origin, 
					            Scalr.Method.AUTOMATIC, 
					            Scalr.Mode.FIT_TO_HEIGHT,50
					            );
				
				ImageIO.write(destImg, "jpg", new FileOutputStream("C:\\upload\\" + thumbname + extension));
				dto.setThumbname(thumbname);
				
			}if (contentType.equals("video/mp4")){
				
				System.out.println("it is video file..");

				dto.setP_thumbname(p_thumbname);
				OutputStream pout = new FileOutputStream("C:\\upload\\"+ p_thumbname + extension);
				
				FileCopyUtils.copy(file.get(i).getInputStream(), pout);
				
			}
			
			dto.setOriginalname(originalname);
			dto.setUploadname(uploadname);
			dto.setFilesize(filesize);
			dto.setExtension(extension);
			dto.setTno(tno);

			fservice.addFile(dto);
						
		}
		
	}
	
	@PostMapping("/register")
	public void registerPost(BoardDTO dto){
		
		System.out.println(dto);
		service.register(dto);
					
	}
	
	@GetMapping("/getflist/{tno}")
	public List<FileDTO> getFileList(@PathVariable("tno")int tno){
		System.out.println(tno);
		return fservice.getFlist(tno);

	
	}
	
	@DeleteMapping("/delflist")
	public void delFileList(@RequestBody FileDTO dto){
		System.out.println(dto);
		
		String uploadname = dto.getUploadname();
		
		int start = uploadname.lastIndexOf(".");
		
		int last = uploadname.length();
		
		String newUploadName = uploadname.substring(0, start);
		
		/*String extension = uploadname.substring(start, last);*/
		
		File file = new File("C:\\upload\\"+ uploadname);
        
        if( file.exists() ){
            if(file.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
        
		dto.setUploadname(newUploadName);
		
		fservice.delFile(dto);
		
	}
	
	@GetMapping("/download/{uploadname:.+}")
	public void download(@PathVariable("uploadname") String uploadname,HttpServletRequest req,
			HttpServletResponse resp) throws IOException{

		System.out.println(uploadname);
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\upload\\" + uploadname));
	     
		resp.setHeader( "Content-Disposition", "attachment;filename=" + uploadname );
	    resp.setContentType("application/octet-stream");
	    resp.setContentLength(fileByte.length);
	    resp.setHeader("Content-Transfer-Encoding", "binary");
	    resp.getOutputStream().write(fileByte);
	    resp.getOutputStream().flush();
	    resp.getOutputStream().close();
	}
	
}
