package sa.gov.sfd.leave.domain.path;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sa.gov.sfd.leave.model.EmployeeApprovalPath;
import sa.gov.sfd.leave.model.Workflow;

import java.util.List;
import java.util.Optional;

@Repository
 class ApprovalPathDao {

    private final JdbcTemplate template;


    public ApprovalPathDao(JdbcTemplate template) {
        this.template = template;
    }

    
    public List<EmployeeApprovalPath> findByWorkflow(Workflow workflow) {
        return template.query("SELECT USER_NID, WORKFLOW FROM APPROVAL_PATH" +
                " WHERE WORKFLOW = ?", new Object[]{workflow}, new ApprovalPathMapper());
    }

    
    public Optional<EmployeeApprovalPath> findById(Integer userNid) {
        return Optional.ofNullable(template.queryForObject("select USER_NID, WORKFLOW\n" +
                "from APPROVAL_PATH\n" +
                "where USER_NID = ?", new Object[]{userNid}, new ApprovalPathMapper()));
    }
}
