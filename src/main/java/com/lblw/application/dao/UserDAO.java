package com.lblw.application.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lblw.application.model.entity.UserEntity;

/**
 * 
 * @author rajesh.l.singh, perform database related operations
 *
 */
@Repository
public interface UserDAO extends MongoRepository<UserEntity, String> {

}
