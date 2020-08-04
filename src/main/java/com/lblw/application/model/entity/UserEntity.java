package com.lblw.application.model.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * 
 * @author rajesh.l.singh, entity data persist in USER collection
 *
 */
@Document(collection = "USER")
@Data
public class UserEntity {
	@Id
	private String userId;

	@Field("user_name")
	private String name;

	@Field("user_phone")
	private List<PhoneEntity> phoneDetails;

	@Field("user_add")
	private AddressEntity addressDetail;

	@Field("date")
	private Date creationDate = new Date();

	@Field("user_settings")
	private Map<String, String> userSettings = new HashMap<>();

}
