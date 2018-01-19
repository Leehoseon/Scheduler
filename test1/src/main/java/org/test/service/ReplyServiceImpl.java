package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.dto.ReplyDTO;
import org.test.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper mapper;
	
	@Override
	public void replyRegister(ReplyDTO dto) {
		
		mapper.replyRegister(dto);

	}

}
