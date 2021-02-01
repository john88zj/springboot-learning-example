package com.cherrys.platform.core.dao;

import com.cherrys.platform.core.domain.TUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<TUser, Integer> {

}
