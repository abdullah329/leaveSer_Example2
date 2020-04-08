package sa.gov.sfd.leave.domain.workflow;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sa.gov.sfd.leave.model.Workflow;

import java.util.List;
import java.util.Optional;

@Repository
class WorkflowDao {

    final
    JdbcTemplate template;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public WorkflowDao(JdbcTemplate template, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = template;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    
    public Optional<Workflow> findByIdAndPriority(Long id, int priority) {
        return Optional.ofNullable(template.queryForObject("SELECT ID, PRIORITY, GROUP_ID, ACTION\n" +
                "FROM WORKFLOW\n" +
                "WHERE ID =? AND PRIORITY = ?", new Object[]{id, priority}, new WorkflowMapper()));
    }

    
    public Optional<Workflow> findById(long id) {
        return Optional.empty();
    }

    
    public List<Workflow> findAll() {
        return null;
    }

    
    public void save(Workflow workflow) {

    }

    
    public List<Workflow> findByGroupIds(List<Long> groupIds) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("gId", groupIds);
        return namedParameterJdbcTemplate.query("select ID, PRIORITY, GROUP_ID, ACTION from WORKFLOW where GROUP_ID in (:gId)", parameters, new WorkflowMapper());
    }
}
