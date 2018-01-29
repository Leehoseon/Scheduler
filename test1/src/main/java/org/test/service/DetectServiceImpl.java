package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.dto.DetectDTO;
import org.test.mapper.DetectMapper;
@Service
public class DetectServiceImpl implements DetectService {

	@Autowired
	DetectMapper mapper;
	
	@Override
	public void regDetect(DetectDTO ddto) {
		mapper.regDetect(ddto);
	}

}
