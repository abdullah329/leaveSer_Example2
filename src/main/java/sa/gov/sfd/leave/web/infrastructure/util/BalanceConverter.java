package sa.gov.sfd.leave.web.infrastructure.util;

import org.springframework.stereotype.Component;
import sa.gov.sfd.leave.web.infrastructure.constant.LeaveType;
import sa.gov.sfd.leave.model.Balance;
import sa.gov.sfd.leave.web.view.BalanceDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BalanceConverter {

    private final LeaveType type;

    public BalanceConverter(LeaveType type) {
        this.type = type;
    }

    public List<BalanceDTO> convertToDTO(List<Balance> model) {

        return model.stream().map(x ->
                new BalanceDTO(x.getYear().shortValue(), x.getStartDateAh()
                , x.getEndDateAh()
                , type.getType(x.getType())
                , x.getValue())).collect(Collectors.toList());
    }




}
