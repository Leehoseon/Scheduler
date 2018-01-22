package org.test.service;

import java.util.List;

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

	@Override
	public List<ReplyDTO> getReply(ReplyDTO dto) {
		return mapper.getReply(dto);
	}

	@Override
	public void deleteReply(int rno) {
		mapper.deleteReply(rno);
	}

	@Override
	public void modifyReply(ReplyDTO dto) {
		mapper.modifyReply(dto);
		
	}

}
