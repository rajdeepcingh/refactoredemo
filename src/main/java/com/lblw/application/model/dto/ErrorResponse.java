package com.lblw.application.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private List<String> details;

}
