package com.autohost2.vultr.param;


/**
 * Liste des plan Vultr
 * @author mehdi
 *
 */
public enum Vpsplanid {
	SMALL("400"),
	MIDDLE("402"),
	BIG("403");
	private String vpsplanid;
	
	Vpsplanid(String vpsplanid){
		this.vpsplanid = vpsplanid;
	}
	
	public String toString() {
		return this.vpsplanid;
	}
	
}



