package com.lblw.application.model.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document
@Data
public class PhoneEntity {

	@Id
	private String phoneId;

	@NotNull(message = "Phone type should not be blank")
	@Field("phone_type")
	private String phoneType;

	@NotNull(message="Phone number should not be blank")
	@Size(min = 10, max = 13, message = "Phone number length must be between 10 to 13 digits")
	@Field("phone_no")
	private String phoneNo;

}
