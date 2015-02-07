package com.sechelc.cloud.manager.delivery.site;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "ds", path = "ds")
public interface DeliverySiteRepository extends PagingAndSortingRepository<DeliverySite, Long> {

    List<DeliverySite> findByCompany(@Param("company") String name);

}