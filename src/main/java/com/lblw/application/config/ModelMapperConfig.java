package com.lblw.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.lblw.application.mapper.fromdto.UserToUserEnt;

@Configuration
public class ModelMapperConfig {
	@Bean
	@Primary
	public ModelMapper setMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.addMappings(new UserToUserEnt());
		return modelMapper;
	}

}
