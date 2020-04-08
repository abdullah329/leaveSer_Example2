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

public class CancelApplication {

    private final ApplicationService applicationService;
    private final BalanceService balanceService;

    public CancelApplication(ApplicationService applicationService
            , BalanceService balanceService) {
        this.applicationService = applicationService;
        this.balanceService = balanceService;
    }

    /**
     * Cancel confirm application, and reverse balance transaction,
     * add status(4) which mean canceled application
     *
     * @param application is the application which required cancellation action
     */
    @Transactional
    public void cancel(Application application) {

        application.setCreatedDate(LocalDateTime.now());
        application.setWorkflowPriority(-2);
        application.setStatus((short) 4);

        applicationService.cancel(application);

        balanceService.addCreditBalanceTransaction(application.getId());

    }



}
