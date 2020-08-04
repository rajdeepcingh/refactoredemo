package com.lblw.application.service;

import java.util.List;

import com.lblw.application.model.dto.UserDTO;

public interface UserService {

	List<UserDTO> getAllUsers();

	UserDTO getUserById(String userId);

	String addNewUser(UserDTO user);

	Object getAllUserSettings(String userId);

	String getUserSetting(String userId, String key);

	String addUserSetting(String userId, String key, String value);
}
