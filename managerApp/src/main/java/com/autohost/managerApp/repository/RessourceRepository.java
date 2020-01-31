package com.autohost.managerApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.autohost.managerApp.entity.Ressource;

public interface RessourceRepository extends CrudRepository<Ressource, Integer> {
    Ressource findBySizeAndOsAndProviderAndConfigurationAndAttribuedAndPlace(String size, String os, String provider, String configuration, Boolean attribued,
                    String place);

}
