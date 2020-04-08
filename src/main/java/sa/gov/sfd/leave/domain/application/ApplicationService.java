package sa.gov.sfd.leave.domain.application;

import org.springframework.stereotype.Service;
import sa.gov.sfd.leave.model.Application;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationDao dao;
    private final ApplicationValidation validation;

    public ApplicationService(ApplicationDao dao, ApplicationValidation validation) {
        this.dao = dao;
        this.validation = validation;
    }

    
    public List<Application> findAll() {
        return dao.findAll();
    }

    
    public List<Application> findByUserNid(Integer userNid) {
        return dao.findByUserNid(userNid);
    }

    
    public Application findById(Long id, Integer priority) {
        return dao.findById(id, priority).orElseThrow(IllegalArgumentException::new);
    }

    
    public Long addNewApplication(Application application) {
        validation.validate(application);
        return dao.insertNewApplication(application);
    }



    
    public void confirm(Application application) {
        validation.validateConfirm(application);
        dao.insertNewAction(application);
    }

    
    public void approve(Application application) {
        validation.validateApprove(application);
        dao.insertNewAction(application);
    }

    
    public void cancel(Application application) {
        validation.validateCancel(application);
        dao.insertNewAction(application);
    }

    
    public void reject(Application application){
        validation.validateReject(application);
        dao.updateApplication(application);
    }

    
    public List<Application> findByWorkflowId(List<Long> permissionsId) {
        return dao.findByWorkflowId(permissionsId);
    }
}
