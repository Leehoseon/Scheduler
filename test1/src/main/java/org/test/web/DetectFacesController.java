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

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;

@RestController
@RequestMapping("/detectfaces/*")
public class DetectFacesController {

	@Inject FileService fservice;
	
	String endTarget;
	
	@PostMapping("/regfaces")
	public String regDetectFaces(List<MultipartFile> file) throws IOException{
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
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fileCount ++;
			
			System.out.println(fileCount);
			
			String s = String.format("%02d", fileCount);
			/*int t = fservice.getTno();*/
			int t = 00000;
			String uploadname = "F00000"+t+"_"+s+"_"+"MAIN";
			
			String thumbname = "F00000"+t+"_"+s+"_"+"THUM";
			
			String p_thumbname = "F00000"+t+"_"+s+"_"+"THUM";
			
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
				
			}if (contentType.equals("video/mp4")){
				
				System.out.println("it is video file..");

				dto.setP_thumbname(p_thumbname);
				OutputStream pout = new FileOutputStream("C:\\face\\"+ p_thumbname + extension);
				
				FileCopyUtils.copy(file.get(i).getInputStream(), pout);
				
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
}
