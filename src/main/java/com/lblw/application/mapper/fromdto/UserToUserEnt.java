package com.lblw.application.mapper.fromdto;

import org.modelmapper.PropertyMap;

import com.lblw.application.model.dto.UserDTO;
import com.lblw.application.model.entity.UserEntity;

/**
 * 
 * @author rajesh.l.singh, transfer user data to persist data object
 *
 */
public class UserToUserEnt extends PropertyMap<UserDTO, UserEntity>{

	@Override
	protected void configure() {
		map().setUserId(source.getUserId());
		map().setName(source.getName());
		map().setPhoneDetails(source.getPhoneDetails());
		map().setAddressDetail(source.getAddressDetail());
		map().setCreationDate(source.getCreationDate());
		map().setUserSettings(source.getUserSettings());
	}

}
