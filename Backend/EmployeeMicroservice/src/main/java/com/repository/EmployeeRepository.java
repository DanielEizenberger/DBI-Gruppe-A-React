package com.repository;

import com.data.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
    List<EmployeeEntity> findByFirstname(String name);

    List<EmployeeEntity> findByLastname(String name);

    @Override
    <S extends EmployeeEntity> S save(S s);
}
