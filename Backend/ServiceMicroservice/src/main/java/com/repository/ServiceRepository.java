package com.repository;

import com.data.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ServiceRepository extends CrudRepository<ServiceEntity, Integer> {
    List<ServiceEntity> findByName(String name);

    List<ServiceEntity> findByEmployeeId(int employeeID);

    List<ServiceEntity> findByDate(Date date);

    @Override
    <S extends ServiceEntity> S save(S s);
}
