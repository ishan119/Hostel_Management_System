package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.tm.RemainingKeyMoneyTM;

import java.util.List;

public interface JoinService {
   List<RemainingKeyMoneyTM> getRemainingKeyMoneyDetails();
}
