package entityDTO.dto;

public class CustomerDTO {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String pwd;
    private String trackingId;

    public CustomerDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CustomerDTO(String name, String surname, String phone, String email, String trackingId) {
        super();
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.trackingId = trackingId;
    }

    public CustomerDTO(String trackerId) {
        // TODO Auto-generated constructor stub
        this.trackingId = trackerId;
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
     * @return the trackingId
     */
    public String getTrackingId() {
        return trackingId;
    }

    /**
     * @param trackingId the trackingId to set
     */
    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
