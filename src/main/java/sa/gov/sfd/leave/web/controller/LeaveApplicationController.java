package sa.gov.sfd.leave.web.controller;


import org.springframework.web.bind.annotation.*;
import sa.gov.sfd.leave.action.AddNewApplication;
import sa.gov.sfd.leave.action.GetAllEmployeeApplication;
import sa.gov.sfd.leave.action.GetCurrentBalance;
import sa.gov.sfd.leave.web.infrastructure.util.ApplicationConverter;
import sa.gov.sfd.leave.web.infrastructure.util.BalanceConverter;
import sa.gov.sfd.leave.model.Application;
import sa.gov.sfd.leave.model.Balance;
import sa.gov.sfd.leave.web.view.ApplicationDTO;
import sa.gov.sfd.leave.web.view.ApplicationViewDTO;
import sa.gov.sfd.leave.web.view.BalanceDTO;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/leave/application")
public class LeaveApplicationController {

    private final AddNewApplication newApplication;

    private final GetCurrentBalance balance;

    private final ApplicationConverter converter;

    private final BalanceConverter balanceConverter;

    private final GetAllEmployeeApplication allEmployeeApplication;

    public LeaveApplicationController(AddNewApplication newApplication, ApplicationConverter converter, GetCurrentBalance balance, BalanceConverter balanceConverter, GetAllEmployeeApplication allEmployeeApplication) {
        this.newApplication = newApplication;
        this.converter = converter;
        this.balance = balance;
        this.balanceConverter = balanceConverter;
        this.allEmployeeApplication = allEmployeeApplication;
    }

    @PostMapping("/addLeave")
    public void createLeaveApplication(@RequestBody ApplicationDTO applicationDTO) {
        Integer userNid = Integer.valueOf("1045167143");

        Application model = converter.convertToModel(applicationDTO);
        model.setUserNid(userNid);
        newApplication.addNewApplication(model);
    }

    @GetMapping(value = "/balance")
    public List<BalanceDTO> getBalanceForEmployee(@RequestParam(value = "type", required = true) String type) {
        Integer userNid = Integer.valueOf("1045167143");
        List<Balance> currentBalance = balance.getBalance(userNid, Short.valueOf(type));
        return balanceConverter.convertToDTO(currentBalance);
    }

    @GetMapping(value = "/leaves")
    public List<ApplicationViewDTO> getAllApplicationRequest() {
        Integer userNid = Integer.valueOf("1045167143");
        List<Application> applicationList = allEmployeeApplication.getAll(userNid);
        return converter.convertoapplicationdto(applicationList);

    }
}
