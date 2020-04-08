package sa.gov.sfd.leave.domain.permissions;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sa.gov.sfd.leave.model.PermissionsGroup;

import java.util.List;
import java.util.Optional;

@Repository
class PermissionsGroupDao {

    final
    JdbcTemplate template;

    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PermissionsGroupDao(JdbcTemplate template, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = template;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    
    public List<PermissionsGroup> findAll() {
        return template.query("select id, group_name, employees from GROUP_PERMISSIONS", new PermissionsGroupMapper());
    }

    
    public Optional<PermissionsGroup> findById(Long permissionsGroup) {
        return Optional.ofNullable(template.queryForObject("select * from GROUP_PERMISSIONS where ID = ?", new Object[]{permissionsGroup}, new PermissionsGroupMapper()));
    }

    
    public List<PermissionsGroup> findByUserNid(Integer userNid) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user", "%" + userNid + "%");
        return namedParameterJdbcTemplate.query("select ID,GROUP_NAME,EMPLOYEES" +
                        " from GROUP_PERMISSIONS where EMPLOYEES like :user", parameters
                , new PermissionsGroupMapper());
    }
}
