package com.autohost.configurator.config.sysutils;

import java.io.IOException;
import java.net.InetAddress;

public class PingHost {

	
	public static Boolean pingIt(String ip) {
		try {
			InetAddress inet = InetAddress.getByName(ip);
			if (inet.isReachable(5000)) {
				System.out.println(ip + " is reachable.");
			} else {
				System.out.println(ip + " NOT reachable.");
				return false;
			}
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
