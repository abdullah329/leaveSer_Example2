package sa.gov.sfd.leave.web.view;

public class HijriDateDTO {

    private int year;
    private int month;
    private int day;


    public HijriDateDTO() {
    }

    public HijriDateDTO(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getHijriDate(){
        return this.year +
                "-" + (this.month < 10 ? "0" + this.month :  this.month) +
                "-" + (this.day < 10 ? "0" + this.day :  this.day);
    }
}
