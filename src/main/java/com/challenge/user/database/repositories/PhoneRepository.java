package com.challenge.user.database.repositories;

import com.challenge.user.database.entitites.PhoneEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<PhoneEntity, Integer> {

}