package com.autohost.configurator.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.autohost.configurator.config.type.ubuntu.Configuration;

public interface ConfigurationRepository extends CrudRepository<Configuration, Integer> {

    List<Configuration> findAllByTypeOfconfigurationAndOsOrderByOrderOfCommand(String configuration, String os);

}
