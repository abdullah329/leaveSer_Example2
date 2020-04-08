package sa.gov.sfd.leave.domain.application;

import org.springframework.stereotype.Component;
import sa.gov.sfd.leave.domain.application.exception.*;
import sa.gov.sfd.leave.model.Application;

@Component
class ApplicationValidation {

    private final ApplicationDao dao;

    public ApplicationValidation(ApplicationDao dao) {
        this.dao = dao;
    }

    public void validate(Application application) {
        if (duplicateApplication(application))
            throw new DuplicateApplication();
    }

    public void validateConfirm(Application subject){
        Application application = getCurrentTransaction(subject.getId());
        if (application.getStatus() != 1)
            throw new ConfirmException();
        validateAction(subject);
    }

    public void validateApprove(Application subject){
        Application application = getCurrentTransaction(subject.getId());
        if (application.getStatus() != 0 && application.getStatus()!=1)
            throw new ApproveException();

        validateAction(subject);
    }

    public void validateCancel(Application subject){
        Application application = getCurrentTransaction(subject.getId());
        if (application.getStatus() != 2)
            throw new CancelException();
        validateAction(subject);
    }


    public void validateReject(Application subject){
        Application application = getCurrentTransaction(subject.getId());
        if (application.getStatus() != 1 && application.getStatus() != 0)
            throw new RejectException();
        validateAction(subject);
    }

    public Application getCurrentTransaction(Long id){
        return dao.findByIdLatestTransaction(id);
    }

    public void validateAction(Application application) {
        if (duplicationAction(application))
            throw new DuplicateAction();
    }

    private boolean duplicationAction(Application application) {
        return !dao.findByIdPriorityStatus(application.getId(), application.getWorkflowPriority(), application.getStatus());
    }


    private boolean duplicateApplication(Application application) {
        return !dao.findByStartDateAndDuration(application);
    }
}
