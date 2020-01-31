package entityDTO;

public class CommandConfDTO {

    private String  commandLine;
    private String  typeOfconfiguration;
    private Integer place;
    private String  os;

    public CommandConfDTO(String commandLine, String typeOfconfiguration, Integer place, String os) {
        super();
        this.commandLine = commandLine;
        this.typeOfconfiguration = typeOfconfiguration;
        this.place = place;
        this.os = os;
    }

    public CommandConfDTO() {
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public String getTypeOfconfiguration() {
        return typeOfconfiguration;
    }

    public void setTypeOfconfiguration(String typeOfconfiguration) {
        this.typeOfconfiguration = typeOfconfiguration;
    }

}
