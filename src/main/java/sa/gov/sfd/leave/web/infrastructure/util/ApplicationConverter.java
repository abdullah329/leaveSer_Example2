package sa.gov.sfd.leave.web.infrastructure.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sa.gov.sfd.leave.web.infrastructure.constant.LeaveType;
import sa.gov.sfd.leave.model.Application;
import sa.gov.sfd.leave.model.ApplicationWithPermissionDTO;
import sa.gov.sfd.leave.web.view.ApplicationDTO;
import sa.gov.sfd.leave.web.view.ApplicationTransactionDTO;
import sa.gov.sfd.leave.web.view.ApplicationViewDTO;

import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ApplicationConverter {

    @Autowired
    private LeaveType leaveType;

    public Application convertToModel(ApplicationDTO dto) {
        Application model = new Application();
        HijrahDate temp = HijrahDate.of(
                dto.getStartDateAh().getYear(),
                dto.getStartDateAh().getMonth(),
                dto.getStartDateAh().getDay()
        );
        dto.setStartDateAg(LocalDate.from(temp));
        model.setType((short) dto.getType());
        model.setDuration((short) dto.getDuration());
        model.setStartDateAg(dto.getStartDateAg());
        model.setStartDateAh(dto.getStartDateAh().getHijriDate());
        return model;
    }

    public List<ApplicationTransactionDTO> convertToTransactionDTO(List<ApplicationWithPermissionDTO> model) {

        return model.stream().map(x ->
                new ApplicationTransactionDTO(x.getId(),x.getWorkflowPriority(),
                        x.getUserNid()
                        , x.getStartDateAh()
                        , x.getDuration()
                        , leaveType.getType(x.getType())
                        , x.getAction())).collect(Collectors.toList());
    }


    public List<ApplicationViewDTO> convertoapplicationdto(List<Application> model){

        return model.stream()
                .map(x ->
                        new ApplicationViewDTO(x.getId()
                                ,x.getStartDateAh()
                                , x.getDuration()
                                ,leaveType.getType(x.getType())
                                ,leaveType.getStatus(x.getStatus())))
                .collect(Collectors.toList());
    }
    

}
