package com.lblw.application.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.lblw.application.dao.UserDAO;
import com.lblw.application.model.dto.UserDTO;
import com.lblw.application.model.entity.UserEntity;
import com.lblw.application.service.UserService;

/**
 * 
 * @author rajesh.l.singh, perform business logic on user data
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserDAO userDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> userList = new ArrayList<>();
		userDao.findAll().stream().forEach(x -> {
			UserDTO user = new UserDTO();
			modelMapper.map(x, user);
			userList.add(user);
		});

		return userList;
	}

	@Override
	public UserDTO getUserById(String userId) {
		return userDao.findById(userId).map(x -> modelMapper.map(x, UserDTO.class)).orElse(null);
	}

	@Override
	public String addNewUser(UserDTO user) {
		UserEntity userEn = new UserEntity();
		modelMapper.map(user, userEn);
		// Now, user object will contain the ID as well
		return userDao.save(userEn).getUserId();
	}

	@Override
	public Object getAllUserSettings(String userId) {
		Optional<UserEntity> userEn = userDao.findById(userId);
		return userEn.isPresent() ? userEn.get().getUserSettings() : "User not found.";
	}

	@Override
	public String getUserSetting(String userId, String key) {
		Query query = new Query();
		query.fields().include("userSettings");
		query.addCriteria(
				Criteria.where("userId").is(userId).andOperator(Criteria.where("userSettings." + key).exists(true)));
		UserEntity userEn = mongoTemplate.findOne(query, UserEntity.class);
		return userEn != null ? userEn.getUserSettings().get(key) : "Not found.";
	}

	@Override
	public String addUserSetting(String userId, String key, String value) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		UserEntity userEn = mongoTemplate.findOne(query, UserEntity.class);
		if (userEn != null) {
			userEn.getUserSettings().put(key, value);
			mongoTemplate.save(userEn);
			return "Key added.";
		} else {
			return "User not found.";
		}
	}
}
