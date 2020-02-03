package com.autohost.managerApp.exitstatus;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomStatus {

    public static final String RESSOURCENOTFOUND = "1";
    public static final String RESSOURCEFOUND    = "2";
    public static final String COMPLETED         = "3";
    public static final String RESSOURCEORDERED  = "4";

}
