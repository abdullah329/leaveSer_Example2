package sa.gov.sfd.leave.model;


/**
 * Permissions Type and Group name with employee list
 *
 * @author malsharhan@sfd.gov.sa
 * @version 1.0
 */

public class PermissionsGroup extends BaseObject {


    private String groupName;

    private String employees;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

}
