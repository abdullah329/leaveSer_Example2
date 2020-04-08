package sa.gov.sfd.leave.domain.workflow;

import org.springframework.jdbc.core.RowMapper;
import sa.gov.sfd.leave.model.Workflow;

import java.sql.ResultSet;
import java.sql.SQLException;

 class WorkflowMapper implements RowMapper<Workflow> {

    @Override
    public Workflow mapRow(ResultSet resultSet, int i) throws SQLException {
        Workflow model = new Workflow();

        model.setId(resultSet.getLong("ID"));
        model.setPermissionsGroup(resultSet.getLong("GROUP_ID"));
        model.setPriority(resultSet.getInt("PRIORITY"));
        model.setAction(resultSet.getString("ACTION"));
        return model;
    }
}
