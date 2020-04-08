package sa.gov.sfd.leave.action;

import org.springframework.transaction.annotation.Transactional;
import sa.gov.sfd.leave.domain.application.ApplicationService;
import sa.gov.sfd.leave.domain.balance.BalanceService;
import sa.gov.sfd.leave.model.Application;

import java.time.LocalDateTime;

/**
 * @author malsharhan@sfd.gov.sa
 * @version 1.0
 */

public class ConfirmApplication {

    private final ApplicationService applicationService;
    private final BalanceService balanceService;

    public ConfirmApplication(ApplicationService applicationService
            , BalanceService balanceService) {
        this.applicationService = applicationService;
        this.balanceService = balanceService;
    }

    /**
     * Confirm the employee leaving application by HR or permit employee.
     * the confirmation will change the employee leaving balance.
     * status(2) is represent the confirmation status
     * priority(100) represent nothing in workflow table, but it is here because the workflow priority is one of composite primary key
     *
     * @param application is the leaving application which required an action from permit employee.
     */
    @Transactional
    public void confirm(Application application) {

        application.setStatus((short) 2);
        application.setCreatedDate(LocalDateTime.now());
        application.setWorkflow(null);
        application.setWorkflowPriority(100);
        applicationService.confirm(application);


        balanceService.removeBalanceByApplicationId(application.getId());

        balanceService.addDebitBalanceTransaction(application.getId()
                , application.getUserNid()
                , application.getDuration()
                , application.getType());
    }


}
