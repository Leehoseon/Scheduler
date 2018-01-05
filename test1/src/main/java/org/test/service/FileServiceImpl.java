package org.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.dto.BoardDTO;
import org.test.dto.FileDTO;
import org.test.mapper.FileMapper;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper mapper;
	
	@Override
	public void putFile(FileDTO dto) {
		
		mapper.putFile(dto);

	}

	@Override
	public List<FileDTO> getFlist(int tno) {
		return mapper.getFlist(tno);
	}

	@Override
	public void delFile(FileDTO dto) {
		System.out.println(dto+"asdASD?ASD?ASD?");
		mapper.delFile(dto);
		
	}

	@Override
	public void delTno(int tno) {
		mapper.delTno(tno);	
	}

	@Override
	public void addFile(FileDTO dto) {
		
		mapper.addFile(dto);
		
	}

	@Override
	public int getFileCount() {
		return mapper.getFileCount();
	}

	@Override
	public int getTno() {
		return mapper.getTno();
	}


	

}
