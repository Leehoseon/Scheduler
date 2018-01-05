package org.test.service;

import java.util.List;


import org.test.dto.FileDTO;

public interface FileService {
	
	public void putFile(FileDTO dto);
	
	public List<FileDTO> getFlist(int tno);
	
	public void delFile(FileDTO dto);
	
	public void delTno(int tno);
	
	public void addFile(FileDTO dto);
	
	public int getFileCount();
	
	public int getTno();

}
