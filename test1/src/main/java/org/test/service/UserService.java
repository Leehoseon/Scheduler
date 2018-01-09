package org.test.service;

import org.test.dto.UserDTO;

public interface UserService {
	
	public void userRegister(UserDTO dto);
	
	public String userCheck(String uid);
	
	public String loginUser(String uid);
	
	public UserDTO getUemail(UserDTO dto);

}
