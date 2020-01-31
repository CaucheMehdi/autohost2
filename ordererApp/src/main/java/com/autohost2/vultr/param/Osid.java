package com.autohost2.vultr.param;

 
/**
 * Liste des OS Vultr
 * @author mehdi
 *
 */
public enum Osid {
	UBUNTU1804("270"),
	COREOS("179");
	private String osid;
	
	Osid(String osid){
		this.osid = osid;
	}
	
	public String toString(){
	    return osid;
	  }


}
