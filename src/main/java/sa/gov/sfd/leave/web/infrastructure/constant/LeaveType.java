package sa.gov.sfd.leave.web.infrastructure.constant;

import org.springframework.stereotype.Component;

@Component
public class LeaveType {

    public String getType(short type){
        if (type == 1) {
            return "اعتيادية";
        }
        return "";
    }


    public String getStatus(short type){
        if (type == 0)
            return "مبدئية";
        if (type == 1)
            return "تمت الموافقة";
        if (type == 2)
            return "معتمد";
        if (type == 3)
            return "مرفوض";
        if (type == 4)
            return "ملغى";
        return "";
    }
}
