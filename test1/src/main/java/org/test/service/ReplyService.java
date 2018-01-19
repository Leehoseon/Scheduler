package org.test.service;

import java.util.List;

import org.test.dto.ReplyDTO;

public interface ReplyService {
	
	public void replyRegister(ReplyDTO dto);
	
	public List<ReplyDTO> getReply(int tno);
	
	public void deleteReply(int rno);
	
	public void modifyReply(ReplyDTO dto);
	
}
