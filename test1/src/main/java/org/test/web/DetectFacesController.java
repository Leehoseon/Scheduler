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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.test.dto.DetectDTO;
import org.test.dto.FileDTO;
import org.test.service.DetectService;
import org.test.service.FileService;

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
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/detectfaces/*")
public class DetectFacesController {

	@Inject FileService fservice;
	@Inject DetectService dservice;
	
	String endTarget;
	
	@PostMapping("/regfaceslocal")
	public String regDetectFaces(List<MultipartFile> file) throws IOException{
		
		File delfile = new File("C:\\upload\\"+ "DETECT0__THUM.jpg" );
		
		if( delfile.exists() ){
            if(delfile.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
		
		String text = "";
		List <Label> labels = null;
		
		int size = file.size();
		
		for(int i = 0; i < size; i++ ) {
			
			int filesize = (int) file.get(i).getSize();
			
			FileDTO dto = new FileDTO();
			
			String target = "C:\\face\\";
			
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
			
			fileCount ++;
			
			System.out.println(fileCount);
			
			/*String s = String.format("%02d", fileCount);*/
			/*int t = fservice.getTno();*/
			int t = 00000;
			String uploadname = "DETECT"+t+"_"+"_"+"MAIN";
			
			String thumbname = "DETECT"+t+"_"+"_"+"THUM";
			
			System.out.println(uploadname);
			
			System.out.println(originalname);
			
			OutputStream out = new FileOutputStream("C:\\face\\"+ uploadname + extension);
			
			FileCopyUtils.copy(file.get(i).getInputStream(), out);
									
			if(contentType.equals("image/jpeg")) {
						
				BufferedImage origin = ImageIO.read(file.get(i).getInputStream());
				
				BufferedImage destImg = Scalr.resize(origin, 
					            Scalr.Method.AUTOMATIC, 
					            Scalr.Mode.FIT_TO_HEIGHT,50
					            );
				
				ImageIO.write(destImg, "jpg", new FileOutputStream("C:\\face\\" + thumbname + extension ));
				dto.setThumbname(thumbname);
				
			}
			
			/*dto.setOriginalname(originalname);
			dto.setUploadname(uploadname);
			dto.setFilesize(filesize);
			dto.setExtension(extension);*/
			System.out.println(filesize);
			System.out.println(extension);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*fservice.putFile(dto);*/
			
			endTarget = target+ uploadname + extension;
			
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
	            labels = result.getLabels();

	            System.out.println("Detected labels for " + photo);
	            for (Label label: labels) {
	               text += label.getName() + ": " + label.getConfidence().toString()+"\r\n";
	               System.out.println(label.getName() + ": " + label.getConfidence().toString());
	               System.out.println(text);
	            }

	        } catch (AmazonRekognitionException e) {
	            e.printStackTrace();
	        }
			
		}
		System.out.println(text+"last");
		return text;
	}
	
	@PostMapping("/regfaces")
	public DetectDTO regDetectFacesForS3(List<MultipartFile> file) throws IOException{
		
		List<FaceDetail> retList = null;
		String retString = null;
		List < FaceDetail > faceDetails =null;
		DetectDTO ddto = new DetectDTO();
		
		File delfile = new File("C:\\upload\\"+ "DETECT0__THUM.jpg" );
		
		if( delfile.exists() ){
            if(delfile.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
		
		String text = "";
		List <Label> labels = null;
		
		int size = file.size();
		
		for(int i = 0; i < size; i++ ) {
			
			int filesize = (int) file.get(i).getSize();
			
			FileDTO dto = new FileDTO();
			
			String target = "C:\\face\\";
			
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
			
			fileCount ++;
			
			System.out.println(fileCount);
			
			/*String s = String.format("%02d", fileCount);*/
			/*int t = fservice.getTno();*/
			int t = 00000;
			String uploadname = "DETECT"+t+"_"+"_"+"MAIN";
			
			String thumbname = "DETECT"+t+"_"+"_"+"THUM";
			
			System.out.println(uploadname);
			
			System.out.println(originalname);
			
			OutputStream out = new FileOutputStream("C:\\face\\"+ uploadname + extension);
			
			FileCopyUtils.copy(file.get(i).getInputStream(), out);
									
			if(contentType.equals("image/jpeg")) {
						
				BufferedImage origin = ImageIO.read(file.get(i).getInputStream());
				
				BufferedImage destImg = Scalr.resize(origin, 
					            Scalr.Method.AUTOMATIC, 
					            Scalr.Mode.FIT_TO_HEIGHT,50
					            );
				
				ImageIO.write(destImg, "jpg", new FileOutputStream("C:\\face\\" + thumbname + extension ));
				dto.setThumbname(thumbname);
				
			}
			
			/*dto.setOriginalname(originalname);
			dto.setUploadname(uploadname);
			dto.setFilesize(filesize);
			dto.setExtension(extension);*/
			System.out.println(filesize);
			System.out.println(extension);
			/*fservice.putFile(dto);*/
			
			endTarget = target+ uploadname + extension;
			
			String photo=endTarget;

		      String bucket = "facerekognitiontest";

		      AWSCredentials credentials;
		      File s3File = new File("C:\\face\\"+ uploadname + extension);
		      
		      try {
		         credentials = new ProfileCredentialsProvider("C:/Users/eltove-ho/leehoseon.aws/test.txt","leehoseon").getCredentials();
		      } catch (Exception e) {
		         throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
		            + "Please make sure that your credentials file is at the correct "
		            + "location (/Users/userid.aws/credentials), and is in a valid format.", e);
		      }
		      
		      AmazonS3 s3 = new AmazonS3Client(credentials);
		      s3.putObject(new PutObjectRequest(bucket, uploadname+extension, s3File));
		      
		      AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder
		         .standard()
		         .withRegion(Regions.US_EAST_1)
		         .withCredentials(new AWSStaticCredentialsProvider(credentials))
		         .build();

		      DetectFacesRequest request = new DetectFacesRequest()
		         .withImage(new Image()
		            .withS3Object(new S3Object()
		               .withName(uploadname + extension)
		               .withBucket(bucket)))
		         .withAttributes(Attribute.ALL);
		      // Replace Attribute.ALL with Attribute.DEFAULT to get default values.

		      try {
		         DetectFacesResult result = rekognitionClient.detectFaces(request);
		         faceDetails = result.getFaceDetails();
		         retList = result.getFaceDetails();

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
		      
		      for (FaceDetail rere : faceDetails) {
		    	  
		    	  String smile ="";
		    	  int ageHigh = rere.getAgeRange().getHigh();
		    	  int ageLow = rere.getAgeRange().getLow();
		    	  boolean checkSmile = rere.getSmile().getValue();
		    	  if(checkSmile) {
		    		   smile ="smile";
		    	  }else {
		    		  smile ="not"; 
		    	  }
		    	  String gender = rere.getGender().getValue(); 
		    	  ddto.setAgeHigh(ageHigh);
		    	  ddto.setAgeLow(ageLow);
		    	  ddto.setSmile(smile);
		    	  ddto.setGender(gender);
		    	  dservice.regDetect(ddto);
			}
		}
		return ddto;
	}
	
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
			File file = new File("C:\\face\\"+ "DETECT0__MAIN.jpg" );
			System.out.println(thumbname);
					
			return FileUtils.readFileToByteArray(file);
			
			}
		
		catch (Exception e) {
			
			return  null;
		}
	}
}
