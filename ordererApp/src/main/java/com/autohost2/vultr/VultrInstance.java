package com.autohost2.vultr;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * represente une instance Vultr
 * @author mehdi
 *
 */
@Entity
public class VultrInstance implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	private int id;
	@JsonIgnore
	private String configuration;
	@JsonProperty("SUBID")
	private String subid;
	private String os;
	private String ram;
	private String disk;
	private String status;
	private String mainIp;
	private String vcpuCount;
	private String location;
	@JsonProperty("DCID")
	private String dcid;
	@JsonProperty("default_password")
	private String defaultpassword;
	@JsonProperty("date_created")
	private String datecreated;
	@JsonProperty("pending_charges")
	private String pendingcharges;
	@JsonProperty("cost_per_month")
	private String costpermonth;
	@JsonProperty("current_bandwidth_gb")
	private String currentbandwidthgb;
	@JsonProperty("allowed_bandwidth_gb")
	private String allowedbandwidthgb;
	@JsonProperty("netmask_v4")
	private String netmaskv4;
	@JsonProperty("gateway_v4")
	private String gatewayv4;
	@JsonProperty("power_status")
	private String powerstatus;
	@JsonProperty("server_state")
	private String serverstate;
	@JsonProperty("VPSPLANID")
	private String vpsplanid;
	@JsonProperty("OSID")
	private String osid;
	
		
	
	public VultrInstance() {
		super();
	}

	public VultrInstance(String subid) {
		super();
		this.subid = subid;
	}
	
	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubid() {
		return subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMain_ip() {
		return mainIp;
	}

	public void setMain_ip(String main_ip) {
		this.mainIp = main_ip;
	}

	public String getVcpu_count() {
		return vcpuCount;
	}

	public void setVcpu_count(String vcpu_count) {
		this.vcpuCount = vcpu_count;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDcid() {
		return dcid;
	}

	public void setDcid(String dcid) {
		this.dcid = dcid;
	}

	public String getDefaultpassword() {
		return defaultpassword;
	}

	public void setDefaultpassword(String defaultpassword) {
		this.defaultpassword = defaultpassword;
	}

	public String getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}

	public String getPendingcharges() {
		return pendingcharges;
	}

	public void setPendingcharges(String pendingcharges) {
		this.pendingcharges = pendingcharges;
	}

	public String getCostpermonth() {
		return costpermonth;
	}

	public void setCostpermonth(String costpermonth) {
		this.costpermonth = costpermonth;
	}

	public String getCurrentbandwidthgb() {
		return currentbandwidthgb;
	}

	public void setCurrentbandwidthgb(String currentbandwidthgb) {
		this.currentbandwidthgb = currentbandwidthgb;
	}

	public String getAllowedbandwidthgb() {
		return allowedbandwidthgb;
	}

	public void setAllowedbandwidthgb(String allowedbandwidthgb) {
		this.allowedbandwidthgb = allowedbandwidthgb;
	}

	public String getNetmaskv4() {
		return netmaskv4;
	}

	public void setNetmaskv4(String netmaskv4) {
		this.netmaskv4 = netmaskv4;
	}

	public String getGatewayv4() {
		return gatewayv4;
	}

	public void setGatewayv4(String gatewayv4) {
		this.gatewayv4 = gatewayv4;
	}

	public String getPowerstatus() {
		return powerstatus;
	}

	public void setPowerstatus(String powerstatus) {
		this.powerstatus = powerstatus;
	}

	public String getServerstate() {
		return serverstate;
	}

	public void setServerstate(String serverstate) {
		this.serverstate = serverstate;
	}

	public String getVpsplanid() {
		return vpsplanid;
	}

	public void setVpsplanid(String vpsplanid) {
		this.vpsplanid = vpsplanid;
	}

	public String getOsid() {
		return osid;
	}

	public void setOsid(String osid) {
		this.osid = osid;
	}	
	
}
