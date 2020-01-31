package com.autohost2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.autohost2.vultr.VultrInstance;

public interface HostRepository extends CrudRepository<VultrInstance, Integer> {

    VultrInstance findBySubid(String subid);

    @Query(value = "UPDATE VultrInstance SET status= :status WHERE subid = :subid")
    @Modifying
    void updateStatusVultrHost(@Param("subid") String subid, @Param("status") String status);

    List<VultrInstance> findAllByConfiguration(String string);

}
