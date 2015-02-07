package com.sechelc.cloud.manager.plants;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "bp", path = "bp")
public interface BatchingPlantRepository extends PagingAndSortingRepository<BatchingPlant, Long> {

    List<BatchingPlant> findByCompany(@Param("company") String name);

}