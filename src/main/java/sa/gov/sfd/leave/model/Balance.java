package sa.gov.sfd.leave.model;

import java.time.LocalDate;

/**
 * Balance Transaction Object
 *
 * @author malsharhan@sfd.gov.sa
 * @version 1.0
 */
public class Balance extends BaseObject {

    private Long applicationID;
    private Integer employeeID;
    private Integer year;
    private LocalDate startDateAg;
    private LocalDate endDateAg;
    private String startDateAh;
    private String endDateAh;
    private Integer value;
    private Short creditOrDebit;
    private Short type;
    private Integer status;

    public Long getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(Long applicationID) {
        this.applicationID = applicationID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getStartDateAg() {
        return startDateAg;
    }

    public void setStartDateAg(LocalDate startDateAg) {
        this.startDateAg = startDateAg;
    }

    public LocalDate getEndDateAg() {
        return endDateAg;
    }

    public void setEndDateAg(LocalDate endDateAg) {
        this.endDateAg = endDateAg;
    }

    public String getStartDateAh() {
        return startDateAh;
    }

    public void setStartDateAh(String startDateAh) {
        this.startDateAh = startDateAh;
    }

    public String getEndDateAh() {
        return endDateAh;
    }

    public void setEndDateAh(String endDateAh) {
        this.endDateAh = endDateAh;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Short getCreditOrDebit() {
        return creditOrDebit;
    }

    public void setCreditOrDebit(Short creditOrDebit) {
        this.creditOrDebit = creditOrDebit;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
