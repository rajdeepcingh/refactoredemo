package com.lblw.application.model.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class AddressEntity {
	@Id
	private String addressNo;

	@NotNull(message = "City name should not be blank")
	@Size(min = 2, message = "City name should be at least two charecter")
	private String city;

	@NotNull(message = "State name should be blank")
	@Size(min = 2, message = "State name should be at least two charecter")
	private String state;

	@NotNull(message = "Pincode name should be blank")
	@Size(min = 4, message = "Pincode name should be at least four charecter")
	private String pincode;

}
