package sa.gov.sfd.leave.domain.workflow;


import org.springframework.stereotype.Service;
import sa.gov.sfd.leave.model.Workflow;

import java.util.List;

@Service
public class WorkflowService  {

    private final WorkflowDao dao;

    public WorkflowService(WorkflowDao dao) {
        this.dao = dao;
    }

    
    public List<Workflow> findAllWorkflow() {
        return dao.findAll();
    }

    
    public void save(Workflow workflow) {
        dao.save(workflow);
    }

    
    public Workflow findWorkflowById(Long id, int priority) {
        return dao.findByIdAndPriority(id, priority).orElseThrow(IllegalArgumentException::new);
    }

    
    public List<Workflow> findByGroupIds(List<Long> groupIds) {
        return dao.findByGroupIds(groupIds);
    }

}
