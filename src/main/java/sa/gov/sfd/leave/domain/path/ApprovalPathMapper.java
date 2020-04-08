package sa.gov.sfd.leave.domain.path;

import org.springframework.jdbc.core.RowMapper;
import sa.gov.sfd.leave.model.EmployeeApprovalPath;

import java.sql.ResultSet;
import java.sql.SQLException;

 class ApprovalPathMapper implements RowMapper<EmployeeApprovalPath> {

    @Override
    public EmployeeApprovalPath mapRow(ResultSet resultSet, int i) throws SQLException {
        EmployeeApprovalPath model = new EmployeeApprovalPath();
        model.setUserNid(resultSet.getInt("USER_NID"));
        model.setWorkflow(resultSet.getLong("WORKFLOW"));
        return model;
    }
}
