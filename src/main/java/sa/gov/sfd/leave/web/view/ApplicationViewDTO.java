package sa.gov.sfd.leave.web.view;

public class ApplicationViewDTO {

    private long id;
    private String startDateAh;
    private int duration;
    private String typeName;
    private String statusType;


    public ApplicationViewDTO(long id, String startDateAh, int duration, String typeName, String status) {
        this.id = id;
        this.startDateAh = startDateAh;
        this.duration = duration;
        this.typeName = typeName;
        this.statusType = status;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartDateAh() {
        return startDateAh;
    }

    public void setStartDateAh(String startDateAh) {
        this.startDateAh = startDateAh;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
}



