package sa.gov.sfd.leave.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sa.gov.sfd.leave.action.*;
import sa.gov.sfd.leave.domain.application.ApplicationService;
import sa.gov.sfd.leave.domain.balance.BalanceService;
import sa.gov.sfd.leave.domain.path.ApprovalPathService;
import sa.gov.sfd.leave.domain.permissions.PermissionsGroupService;
import sa.gov.sfd.leave.domain.workflow.WorkflowService;


@Configuration
public class BeanConfiguration {


    //--------------------- LeaveApplicationsDao Registration ------------------------

    @Bean
    public AddNewApplication addEmployeeNewApplication(ApplicationService applicationService
            , ApprovalPathService approvalPathService
            , WorkflowService workflowService, BalanceService balanceService) {

        return new AddNewApplication(applicationService
                , approvalPathService, workflowService, balanceService
        );
    }

    @Bean
    public ApproveApplication approveApplication(ApplicationService applicationService
            , ApprovalPathService approvalPathService
            , WorkflowService workflowService) {

        return new ApproveApplication(applicationService
                , approvalPathService
                , workflowService);
    }


    @Bean
    public CancelApplication cancelApplication(ApplicationService applicationService
            , BalanceService balanceService) {
        return new CancelApplication(applicationService
                , balanceService);
    }


    @Bean
    public ConfirmApplication confirmApplication(ApplicationService applicationService
            , BalanceService balanceService) {
        return new ConfirmApplication(applicationService
                , balanceService);
    }


    @Bean
    public RejectApplication rejectApplication(ApplicationService applicationService, BalanceService balanceService) {
        return new RejectApplication(applicationService, balanceService);
    }


    @Bean
    public GetCurrentBalance getCurrentBalance(BalanceService balanceService) {
        return new GetCurrentBalance(balanceService);
    }

    @Bean
    public GetApplicationsToMakeAction getApplicationToMakeAction(ApplicationService applicationService
            , PermissionsGroupService groupService, WorkflowService workflowService) {
        return new GetApplicationsToMakeAction(applicationService, groupService, workflowService);
    }

    @Bean
    public GetOneApplicationToAction getOneApplicationToAction(ApplicationService applicationService) {
        return new GetOneApplicationToAction(applicationService);
    }

    @Bean
    public GetPermissionsByID getPermissionsByID(PermissionsGroupService permissionsGroupService) {
        return new GetPermissionsByID(permissionsGroupService);
    }

    @Bean
    public GetAllEmployeeApplication getAllEmployeeApplication(ApplicationService applicationService) {
        return new GetAllEmployeeApplication(applicationService);
    }
}
