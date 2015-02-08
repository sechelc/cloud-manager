package com.sechelc.cloud.manager.realtime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "log", path = "log")
public interface LogsRepository extends PagingAndSortingRepository<LogEntry, Long> {

    List<LogEntry> findByTruckNo(@Param("truckNo") String name);
    @Query(value = "select l from  LogEntry l  where l.company=:company and l.timestamp=(select max(ee.timestamp) from LogEntry ee where ee.truckNo=l.truckNo)")
    List<LogEntry> findLatestByCompany(@Param("company") String name);
    List<LogEntry> findByTruckNoAndTimestampGreaterThan(@Param("truckNo") String name, @Param("timestamp") Long timestamp);


}