package sa.gov.sfd.leave.domain.application;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sa.gov.sfd.leave.model.Application;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
@Repository
class ApplicationDao  {

    private final
    JdbcTemplate template;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public ApplicationDao(JdbcTemplate template, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = template;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    
    public List<Application> findByUserNid(Integer userNid) {
        return template.query("SELECT ID,\n" +
                "       PRIORITY_ORDER,\n" +
                "       USER_NID,\n" +
                "       START_DATE_AG,\n" +
                "       START_DATE_AH,\n" +
                "       DURATION,\n" +
                "       REQUEST_DATE,\n" +
                "       workflow,\n" +
                "       TYPE,\n" +
                "       STATUS\n" +
                "FROM APPLICATION_TRANSACTION app \n" +
                "WHERE USER_NID = ? " +
                "and PRIORITY_ORDER = (select max(PRIORITY_ORDER) from APPLICATION_TRANSACTION pr where app.ID = pr.ID)", new Object[]{userNid}, new ApplicationMapper());
    }

    
    public Application findByIdLatestTransaction(Long id) {
        return template.queryForObject("select id,\n" +
                "       priority_order,\n" +
                "       user_nid,\n" +
                "       start_date_ag,\n" +
                "       start_date_ah,\n" +
                "       duration,\n" +
                "       request_date,\n" +
                "       WORKFLOW,\n" +
                "       type,\n" +
                "       status\n" +
                "from APPLICATION_TRANSACTION app\n" +
                "where ID = ?\n" +
                "  and PRIORITY_ORDER = (select max(PRIORITY_ORDER) from APPLICATION_TRANSACTION pr where app.ID = pr.ID)", new Object[]{id}, new ApplicationMapper());
    }

    
    public List<Application> findAll() {
        return template.query("SELECT id,\n" +
                "       priority_order,\n" +
                "       user_nid,\n" +
                "       start_date_ag,\n" +
                "       start_date_ah,\n" +
                "       duration,\n" +
                "       request_date,\n" +
                "       WORKFLOW,\n" +
                "       type,\n" +
                "       status\n" +
                "FROM APPLICATION_TRANSACTION", new ApplicationMapper());
    }

    
    public Optional<Application> findById(Long id, Integer priority) {
        String sql = "select id,\n" +
                "       priority_order,\n" +
                "       user_nid,\n" +
                "       start_date_ag,\n" +
                "       start_date_ah,\n" +
                "       duration,\n" +
                "       request_date,\n" +
                "       WORKFLOW,\n" +
                "       type,\n" +
                "       status\n" +
                "from APPLICATION_TRANSACTION\n" +
                "where id = ? and PRIORITY_ORDER = ?";
        return Optional.ofNullable(template.queryForObject(sql, new Object[]{id, priority}, new ApplicationMapper()));
    }

    
    public boolean findByIdPriorityStatus(Long id, Integer priority, Short status) {
        String sql = "select id,\n" +
                "       priority_order,\n" +
                "       user_nid,\n" +
                "       start_date_ag,\n" +
                "       start_date_ah,\n" +
                "       duration,\n" +
                "       request_date,\n" +
                "       WORKFLOW,\n" +
                "       type,\n" +
                "       status\n" +
                "from APPLICATION_TRANSACTION\n" +
                "where id = ? and PRIORITY_ORDER = ? and STATUS = ?";
        List<Application> app = template.query(sql, new Object[]{id, priority, status}, new ApplicationMapper());
        return app.isEmpty();
    }

    @Transactional
    
    public long insertNewApplication(Application application) {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        template.update(new PreparedStatementCreator() {
            
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO APPLICATION_TRANSACTION (PRIORITY_ORDER, USER_NID, START_DATE_AG, START_DATE_AH, DURATION, REQUEST_DATE,\n" +
                        "                                     WORKFLOW, TYPE, STATUS)\n" +
                        "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, application.getWorkflowPriority());
                statement.setInt(2, application.getUserNid());
                statement.setDate(3, java.sql.Date.valueOf(application.getStartDateAg().toString()));
                statement.setString(4, application.getStartDateAh());
                statement.setInt(5,application.getDuration());
                statement.setTimestamp(6, Timestamp.valueOf(application.getRequestDateAndTime()));
                statement.setLong(7, application.getWorkflow());
                statement.setInt(8, application.getType());
                statement.setInt(9, application.getStatus());

                return statement;

            }
        }, holder);

        Long id = Long.valueOf(holder.getKeyList().get(0).get("ID").toString());
        return id;
//        return template.update("INSERT INTO APPLICATION_TRANSACTION (PRIORITY_ORDER, USER_NID, START_DATE_AG, START_DATE_AH, DURATION, REQUEST_DATE,\n" +
//                        "                                     WORKFLOW, TYPE, STATUS)\n" +
//                        "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ",
////                application.getId(),
//                application.getWorkflowPriority(),
//                application.getUserNid(),
//                application.getStartDateAg(),
//                application.getStartDateAh(),
//                application.getDuration(),
//                application.getRequestDateAndTime(),
//                application.getWorkflow(),
//                application.getType(),
//                application.getStatus());
    }

    @Transactional
    
    public void insertNewAction(Application application) {
        template.update("INSERT INTO APPLICATION_TRANSACTION (id,PRIORITY_ORDER, USER_NID, START_DATE_AG, START_DATE_AH, DURATION, REQUEST_DATE,\n" +
                        "                                     WORKFLOW, TYPE, STATUS)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ",
                application.getId(),
                application.getWorkflowPriority(),
                application.getUserNid(),
                application.getStartDateAg(),
                application.getStartDateAh(),
                application.getDuration(),
                application.getRequestDateAndTime(),
                application.getWorkflow(),
                application.getType(),
                application.getStatus());

    }


    
    public List<Application> findByWorkflowId(List<Long> permissionsId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("pId", permissionsId);
        return namedParameterJdbcTemplate.query("select id,\n" +
                "       priority_order,\n" +
                "       user_nid,\n" +
                "       start_date_ag,\n" +
                "       start_date_ah,\n" +
                "       duration,\n" +
                "       request_date,\n" +
                "       WORKFLOW,\n" +
                "       type,\n" +
                "       status\n" +
                "from APPLICATION_TRANSACTION app\n" +
                "where WORKFLOW in (:pId)\n" +
                "  and PRIORITY_ORDER = (select max(PRIORITY_ORDER) from APPLICATION_TRANSACTION pr where app.ID = pr.ID)\n" +
                "  and PRIORITY_ORDER not in (100,4, 3)\n" +
                "  and STATUS not in (3, -1)", parameters, new ApplicationMapper());
    }

    
    public boolean findByStartDateAndDuration(Application application) {
        return template.query("select id,\n" +
                        "       priority_order,\n" +
                        "       user_nid,\n" +
                        "       start_date_ag,\n" +
                        "       start_date_ah,\n" +
                        "       duration,\n" +
                        "       request_date,\n" +
                        "       WORKFLOW,\n" +
                        "       type,\n" +
                        "       status\n" +
                        "from APPLICATION_TRANSACTION app\n" +
                        "where USER_NID = ?\n" +
                        "  " +
                        "and START_DATE_AG = ?\n" +
                        "  " +
                        "and DURATION = ?\n" +
                        "  and PRIORITY_ORDER = (select max(PRIORITY_ORDER) from APPLICATION_TRANSACTION pr where app.ID = pr.ID)\n" +
                        "  and STATUS not in (4, 3)"
                , new Object[]{application.getUserNid(), application.getStartDateAg(), application.getDuration()}
                , new ApplicationMapper()).isEmpty();
    }

    
    public List<Application> findByStartDate(Application application) {
        return null;
    }

    
    public void updateApplication(Application application) {
        template.update("update APPLICATION_TRANSACTION\n" +
                        "set STATUS = ?, modifiedOn = ?\n" +
                        "where id = ?\n" +
                        "  and PRIORITY_ORDER = ?"
                , new Object[]{application.getStatus(), application.getModifiedDate(), application.getId(), application.getWorkflowPriority()});
    }
}
