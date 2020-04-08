package sa.gov.sfd.leave.domain.path;


import org.springframework.stereotype.Service;
import sa.gov.sfd.leave.model.EmployeeApprovalPath;
import sa.gov.sfd.leave.model.Workflow;

import java.util.List;

@Service
public class ApprovalPathService{

    private final ApprovalPathDao dao;

    public ApprovalPathService(ApprovalPathDao dao) {
        this.dao = dao;
    }

    
    public List<EmployeeApprovalPath> findByWorkflowId(Workflow workflow) {
        return dao.findByWorkflow(workflow);
    }

    
    public EmployeeApprovalPath findByUserNid(Integer userNid) {
        return dao.findById(userNid).orElseThrow(IllegalArgumentException::new);
    }
}
