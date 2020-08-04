package com.lblw.application.model.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lblw.application.model.entity.AddressEntity;
import com.lblw.application.model.entity.PhoneEntity;

import lombok.Data;

/**
 * 
 * @author rajesh.l.singh, consuming data from user
 *
 */
@Data
public class UserDTO {

	private String userId;

	@NotBlank(message = "Name should not be empty")
	private String name;

	@NotNull(message = "phone details must be available")
	@Valid
	private List<PhoneEntity> phoneDetails;

	@NotNull(message = "Address details should not be blank")
	@Valid
	private AddressEntity addressDetail;

	@JsonIgnore
	private Date creationDate = new Date();

	@NotNull(message = "User settings Should not be blank")
	@Valid
	private Map<String, String> userSettings = new HashMap<>();

}
