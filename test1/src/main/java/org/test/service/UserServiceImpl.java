package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.dto.UserDTO;
import org.test.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public void userRegister(UserDTO dto) {
		
		mapper.registerUser(dto);

	}

	@Override
	public String userCheck(String uid) {

		return mapper.checkUser(uid);
		
	}

	@Override
	public String loginUser(String uid) {
		
		return mapper.loginUser(uid);
	}

}
