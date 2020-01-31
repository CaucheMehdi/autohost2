package com.autohost.configurator.config.type.ubuntu;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Configuration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String  commandLine;
    private String  typeOfconfiguration;
    private Integer orderOfCommand;
    private String  os;

    public Configuration(String commandLine, String typeOfconfiguration, Integer orderOfCommand, String os) {
        super();
        this.commandLine = commandLine;
        this.typeOfconfiguration = typeOfconfiguration;
        this.orderOfCommand = orderOfCommand;
        this.os = os;
    }

    public Configuration() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the commandLine
     */
    public String getCommandLine() {
        return commandLine;
    }

    /**
     * @return the typeOfconfiguration
     */
    public String getTypeOfconfiguration() {
        return typeOfconfiguration;
    }

    /**
     * @return the orderOfCommand
     */
    public Integer getOrderOfCommand() {
        return orderOfCommand;
    }

    /**
     * @return the os
     */
    public String getOs() {
        return os;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param commandLine the commandLine to set
     */
    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    /**
     * @param typeOfconfiguration the typeOfconfiguration to set
     */
    public void setTypeOfconfiguration(String typeOfconfiguration) {
        this.typeOfconfiguration = typeOfconfiguration;
    }

    /**
     * @param orderOfCommand the orderOfCommand to set
     */
    public void setOrderOfCommand(Integer orderOfCommand) {
        this.orderOfCommand = orderOfCommand;
    }

    /**
     * @param os the os to set
     */
    public void setOs(String os) {
        this.os = os;
    }

}
