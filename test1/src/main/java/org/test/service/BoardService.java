package org.test.service;

import java.util.List;

import org.test.dto.BoardDTO;
import org.test.dto.Criteria;

public interface BoardService {
	
	public void register(BoardDTO dto);
	
	public List<BoardDTO> getList(Criteria cri);
	
	public BoardDTO getView(int tno);
	
	public void remove(int tno);
	
	public void modify(BoardDTO dto);
	
	public int getCount(Criteria cri);

}
