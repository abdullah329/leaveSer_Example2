package sa.gov.sfd.leave.domain.balance;

import org.springframework.stereotype.Service;
import sa.gov.sfd.leave.model.Balance;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalanceService {


    private final BalanceDao dao;
    private final BalanceValidation validation;

    public BalanceService(BalanceDao dao, BalanceValidation validation) {
        this.dao = dao;
        this.validation = validation;
    }

    
    public List<Balance> getCurrentBalanceForEmployee(int employeeId, Short type) {
        return dao.findCurrentBalanceByEmployeeID(employeeId, type);
    }

    
    public List<Balance> getBalanceTransactionForApplication(long application) {
        return dao.findByApplicationId(application);
    }

    
    public List<Balance> getBalanceTransactionForEmployee(int employeeId) {
        return dao.findBalanceTransactionByEmployeeID(employeeId);
    }

    
    public void addNewBalanceRecord(Balance balance) {
        validation.validateTransactionBalance(balance);
        dao.insertBalance(balance);
    }

    
    public void addOpenYearBalance(Balance balance){
        validation.validateOpenBalance(balance);
        dao.insertBalance(balance);
    }


    
    public void addCreditBalanceTransaction(long applicationId) {
        List<Balance> applicationBalance = getBalanceTransactionForApplication(applicationId);

        applicationBalance.forEach(balance -> {
            balance.setCreditOrDebit((short) 1);
            balance.setApplicationID(null);
        });

        applicationBalance.forEach(this::addNewBalanceRecord);

    }

    
    public void addHoldBalanceTransaction(long applicationId, int userNid, int duration, Short type) {
        List<Balance> balanceList = getCurrentBalanceForEmployee(userNid, type);

        List<Balance> listToInsert = calculateDeductionBalance(applicationId, duration, balanceList, (short)-1, 0);

        listToInsert.forEach(this::addNewBalanceRecord);
    }

    
    public void addDebitBalanceTransaction(long applicationId, int userNid, int duration, Short type) {
        List<Balance> balanceList = getCurrentBalanceForEmployee(userNid, type);

        List<Balance> listToInsert = calculateDeductionBalance(applicationId, duration, balanceList, (short)-1, 1);

        listToInsert.forEach(this::addNewBalanceRecord);
    }


    
    public void removeBalanceByApplicationId(long applicationId) {
        dao.removeBalanceTransaction(applicationId);
    }

    /**
     * This function will deduct the duration of leaving application from employee balance based on year by earlier ended
     * date,
     *
     * @param applicationId related application id
     * @param duration
     * @param balanceList   current employee leaving balance,
     * @param type
     * @return list of debit balance to insert it in balance transaction
     */

    private List<Balance> calculateDeductionBalance(long applicationId, int duration, List<Balance> balanceList, short type, int status) {
        List<Balance> sortedBalance = balanceList.stream()
                .sorted(Comparator.comparing(Balance::getEndDateAg, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());

        for (Balance x : sortedBalance)
            if (duration != 0) {
                if (x.getValue() >= duration) {
                    x.setValue(duration);
                    duration = 0;

                } else {
                    duration = duration - x.getValue();
                }
                x.setApplicationID(applicationId);
                x.setCreditOrDebit(type);
                x.setStatus(status);
            }


        return sortedBalance
                .stream()
                .filter(x -> x.getCreditOrDebit() != null)
                .filter(x -> x.getCreditOrDebit() == type)
                .collect(Collectors.toList());
    }



}
