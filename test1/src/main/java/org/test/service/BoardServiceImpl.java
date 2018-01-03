package org.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.dto.BoardDTO;
import org.test.dto.Criteria;
import org.test.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;

	@Override
	public void register(BoardDTO dto) {
		
		mapper.putBoard(dto);
		
	}

	

	@Override
	public BoardDTO getView(int tno) {
		return mapper.getView(tno);
	}

	@Override
	public void remove(int tno) {
		
		mapper.removeBoard(tno);
		
	}

	@Override
	public void modify(BoardDTO dto) {
		
		mapper.modifyBoard(dto);
		
	}

	@Override
	public List<BoardDTO> getList(Criteria cri) {
		
		return mapper.getList(cri);
	}



	@Override
	public int getCount() {
		
		return mapper.getCount();
	}
	
}
