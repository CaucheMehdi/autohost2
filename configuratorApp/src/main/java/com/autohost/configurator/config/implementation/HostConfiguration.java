package com.autohost.configurator.config.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autohost.configurator.config.type.ubuntu.Configuration;
import com.autohost.configurator.repository.ConfigurationRepository;

@Service
public class HostConfiguration {

    @Autowired
    ConfigurationRepository ubuntuConfigRepo;

    public List<Configuration> listOfCommand(String typeOfConfiguration, String hostOS) {
        return ubuntuConfigRepo.findAllByTypeOfconfigurationAndOsOrderByOrderOfCommand(typeOfConfiguration, hostOS);
    }

    public void saveListOfCommand(List<Configuration> l) {
        for (Configuration conf : l) {
            ubuntuConfigRepo.save(conf);
        }

    }

}
