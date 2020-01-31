package entityDTO;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Map;

public class RessourceDTO implements Serializable {

    /**
     *
     */
    private static final long      serialVersionUID = 6816224533717420837L;
    private String                 size;
    private String                 application;
    private String                 os;
    private String                 place;
    private String                 provider;
    private String                 ip;
    private Map<LocalTime, String> events;
    private String                 clientTid;
    private String                 trackingId;
    private String                 status;
    private String                 pwd;

    public RessourceDTO() {
        super();
    }

    /**  Numero aléatoire identifiant la ressource
     * @return the trackingId
     */
    public String getTrackingId() {
        return trackingId;
    }

    /** Numero aléatoire identifiant la ressource
     * @param trackingId the trackingId to set
     */
    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @return the application
     */
    public String getApplication() {
        return application;
    }

    /**
     * @return the os
     */
    public String getOs() {
        return os;
    }

    /**
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @return the clientTid
     */
    public String getClientTid() {
        return clientTid;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * @param os the os to set
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @param clientTid the clientTid to set
     */
    public void setClientTid(String clientTid) {
        this.clientTid = clientTid;
    }

    /**
     * Map historique de vie de la ressource
     * Heure / Point de réception
     * @return
     */
    public Map<LocalTime, String> getEvents() {
        return events;
    }

    public void setEvents(Map<LocalTime, String> events) {
        this.events = events;
    }

    /**
     * Renvoie le statut de la ressource
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Statut de la ressource
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
