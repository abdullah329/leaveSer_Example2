package sa.gov.sfd.leave.domain.application.exception;

public class DuplicateApplication extends IllegalArgumentException {
    @Override
    public String getMessage() {
        return "يوجد اجازة بنس التاريخ";
    }
}
