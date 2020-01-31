package com.autohost.managerApp.listener;

import java.io.Serializable;

import org.springframework.context.ApplicationEvent;

import entityDTO.RessourceDTO;

public class CustomSpringEvent extends ApplicationEvent implements Serializable {
    /**
     * Signaux envoyé qui permettent de démarrer un job
     */
    private static final long serialVersionUID = 1620606404925535313L;
    private Signal            message;
    private RessourceDTO      ressource;
    private Object            source;

    public enum Signal {
        CHECK_RESSOURCE_AVAILABILITY, ORDER_RESSOURCE, SEND_RESSOURCE, CONFIGURE_INSTANCE
    }

    // constructor
    public CustomSpringEvent(Object source, Signal signal, RessourceDTO ressource2) {
        super(source);
        this.message = signal;
        this.source = source;
        this.ressource = ressource2;
    }

    public Signal getMessage() {
        return message;
    }

    public void setMessage(Signal message) {
        this.message = message;
    }

    public RessourceDTO getRessource() {
        return ressource;
    }

    public void setRessource(RessourceDTO ressource) {
        this.ressource = ressource;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

}
