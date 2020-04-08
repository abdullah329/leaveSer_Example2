package sa.gov.sfd.leave.action;


import org.springframework.transaction.annotation.Transactional;
import sa.gov.sfd.leave.domain.application.ApplicationService;
import sa.gov.sfd.leave.domain.balance.BalanceService;
import sa.gov.sfd.leave.domain.path.ApprovalPathService;
import sa.gov.sfd.leave.domain.workflow.WorkflowService;
import sa.gov.sfd.leave.model.Application;
import sa.gov.sfd.leave.model.EmployeeApprovalPath;
import sa.gov.sfd.leave.model.Workflow;

import java.time.LocalDateTime;

/**
 * @author malsharhan@sfd.gov.sa
 * @version 1.0
 */


public class AddNewApplication {

    private final ApplicationService applicationService;

    private final BalanceService balanceService;

    private final ApprovalPathService approvalPathService;

    private final WorkflowService workflowService;


    public AddNewApplication(ApplicationService applicationService
            , ApprovalPathService approvalPathService, WorkflowService workflowService
            , BalanceService balanceService) {
        this.applicationService = applicationService;
        this.approvalPathService = approvalPathService;
        this.workflowService = workflowService;
        this.balanceService = balanceService;

    }

    /**
     * Create a leaving application for employee with initial status(0) and workflow priority(1) which mean the first
     * one in approval process.
     * Next required permission group will add to this application to allow them to take an action for this application
     *
     * @param application is an initial leaving request from an employee
     */
    @Transactional
    public Long addNewApplication(Application application) {

        EmployeeApprovalPath path = approvalPathService.findByUserNid(application.getUserNid());
        Workflow workflow = workflowService.findWorkflowById(path.getWorkflow(), 1);

        application.setStatus((short) 0);
        application.setWorkflow(workflow.getId());
        application.setWorkflowPriority(1);
        application.setCreatedDate(LocalDateTime.now());
        application.setRequestDateAndTime(LocalDateTime.now());

        Long appId = applicationService.addNewApplication(application);

        balanceService.addHoldBalanceTransaction(appId
                , application.getUserNid()
                , application.getDuration()
                , application.getType());

        return appId;
    }


}
