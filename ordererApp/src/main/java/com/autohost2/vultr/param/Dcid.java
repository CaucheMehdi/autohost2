package com.autohost2.vultr.param;
/**
 * liste des DCID Vultr
 * @author mehdi
 *
 */
public enum Dcid {
	PARIS("24"),
	AMS("7"),
	LONDON("8");

	private final String dcidId;
	
	Dcid(String dcidID){
		this.dcidId = dcidID;	
	}
	
	public String toString(){
	    return dcidId;
	  }
}
