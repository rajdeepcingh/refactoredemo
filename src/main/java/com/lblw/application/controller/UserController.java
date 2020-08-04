package com.lblw.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lblw.application.exception.UserNotFoundException;
import com.lblw.application.model.dto.UserDTO;
import com.lblw.application.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rajesh.l.singh, accept HTTP request for user data operations
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/demo")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addNewUsers(@Valid @RequestBody UserDTO user) {
		log.info("Recieving request for saving user data..");
		return new ResponseEntity<String>("User Added successfully with ID: " + userService.addNewUser(user),
				HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		log.info("Recieving request for getting all users");
		return new ResponseEntity<List<UserDTO>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUser(@Valid @PathVariable String userId) {
		log.info("Recieving request for getting user with ID: {}", userId);
		UserDTO user = userService.getUserById(userId);
		if (null != user) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
		}
		log.error("Error in getting the user with ID : {}", userId);
		throw new UserNotFoundException("User not found with given ID");
	}

	/*
	 * @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	 * public Object getAllUserSettings(@PathVariable String userId) { User user =
	 * userRepository.findById(userId).get(); if (user != null) { return
	 * user.getUserSettings(); } else { return "User not found."; } }
	 */

	@RequestMapping(value = "/getAllUserSettings/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllUserSettings(@PathVariable String userId) {
		log.info("Recieving request for getting all user settings with ID: {}", userId);
		ResponseEntity<Object> allUserDetails = null;
		UserDTO user = userService.getUserById(userId);
		try {
			if (user != null) {
				allUserDetails = new ResponseEntity<Object>(userService.getAllUserSettings(userId), HttpStatus.OK);
			}
			return allUserDetails;
		} catch (Exception e) {
			log.error("User settings not found for ID: {}", userId);
			throw new UserNotFoundException("User setting not found for the given ID");
		}

	}

	/*
	 * @RequestMapping(value = "/settings/{userId}/{key}", method =
	 * RequestMethod.GET) public String getUserSetting(@PathVariable String
	 * userId, @PathVariable String key) { User user =
	 * userRepository.findOne(userId); String setting =
	 * userDAL.getUserSetting(userId, key); LOG.info("Setting = " + setting); if
	 * (setting != null) { return setting; } else { return "Setting not found."; } }
	 */

	@RequestMapping(value = "/getUserSettings/{userId}/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUserSetting(@PathVariable String userId, @PathVariable String key) {
		log.info("Recieving request for getting user setting with userId: {} and key: {}", userId, key);
		return new ResponseEntity<String>(userService.getUserSetting(userId, key), HttpStatus.OK);
	}

	@RequestMapping(value = "/addUserSettings/{userId}/{key}/{value}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUserSetting(@PathVariable String userId, @PathVariable String key,
			@PathVariable String value) {
		log.info("Recieving request for create user setting with userId: {} , key: {} and value: {}", userId, key,
				value);
		return new ResponseEntity<String>(userService.addUserSetting(userId, key, value), HttpStatus.ACCEPTED);

	}
}