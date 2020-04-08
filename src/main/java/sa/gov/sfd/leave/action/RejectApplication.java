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

public class RejectApplication {

    private final ApplicationService applicationService;

    private final BalanceService balanceService;


    public RejectApplication(ApplicationService applicationService, BalanceService balanceService) {
        this.applicationService = applicationService;
        this.balanceService = balanceService;
    }

    /**
     * Reject leaving application from permit employee
     *
     * @param application
     */
    @Transactional
    public void reject(Application application) {

        application.setModifiedDate(LocalDateTime.now());

//        application.setWorkflowPriority(-1);
        application.setStatus((short) 3);
        applicationService.reject(application);

        balanceService.removeBalanceByApplicationId(application.getId());


    }

}
