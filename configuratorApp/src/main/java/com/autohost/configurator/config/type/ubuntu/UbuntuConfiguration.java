package com.autohost.configurator.config.type.ubuntu;

import javax.persistence.Entity;
/**
 * Commande à éxecuter sur un hote ubuntu
 * @param commandLine la commande à executer (ex: ls -al)
 * @param typeOfConfiguration permet de spécifier à quelle configuration cette commande s' apllique et dans quelle ordre
 * @param place gràce à ce paramètre
 * @param os l'os destiné à recevoir cette commande
 * @author mehdi
 *
 */
@Entity
public class UbuntuConfiguration extends Configuration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5121835713194213552L;

	public UbuntuConfiguration() {
		super();
	}
	
	public UbuntuConfiguration(String commandLine, String typeOfconfiguration, Integer place, String os) {
		super(commandLine, typeOfconfiguration, place, os);
		// TODO Auto-generated constructor stub
	}

}
