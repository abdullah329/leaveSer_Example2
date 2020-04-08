package sa.gov.sfd.leave.web.view;

public class BalanceDTO {

    private short year;
    private String startDate;
    private String endDate;
    private String type;
    private int balance;

    public BalanceDTO(short year, String startDate, String endDate, String type, int balance) {
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.balance = balance;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
