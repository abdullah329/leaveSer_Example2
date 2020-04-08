package sa.gov.sfd.leave.domain.permissions;

import org.springframework.stereotype.Service;
import sa.gov.sfd.leave.model.PermissionsGroup;

import java.util.List;

@Service
public class PermissionsGroupService{

    private final PermissionsGroupDao dao;

    public PermissionsGroupService(PermissionsGroupDao dao) {
        this.dao = dao;
    }

    
    public List<PermissionsGroup> findAllPermissions() {
        return dao.findAll();
    }

    
    public String getAllEmployeesByPermission(Long permissionsGroup) {
        return dao.findById(permissionsGroup).orElseThrow(IllegalArgumentException::new).getEmployees();
    }

    
    public PermissionsGroup findByPermissionId(Long id) {
        return dao.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    
    public List<PermissionsGroup> findByEmployeeId(Integer userNid) {
        return dao.findByUserNid(userNid);
    }
}
