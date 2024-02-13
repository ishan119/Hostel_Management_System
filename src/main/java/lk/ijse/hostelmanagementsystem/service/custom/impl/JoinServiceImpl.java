package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.repo.custom.StudentRoomRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.StudentRoomRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.JoinService;
import lk.ijse.hostelmanagementsystem.tm.RemainingKeyMoneyTM;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class JoinServiceImpl implements JoinService {
    StudentRoomRepo studentRoomRepo =new StudentRoomRepoImpl();
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    Converter converter =Converter.getInstance();


    @Override
    public List<RemainingKeyMoneyTM> getRemainingKeyMoneyDetails() {
        Session session =factoryConfiguration.getSession();
        try {
            List<StudentRoom> all =studentRoomRepo.getRemainingKeyMoneyList(session);
           return converter.getRemainingKeyMoneyList(all);
        } catch (Exception e) {
        }finally {
            session.close();
        }
        return null;
    }
}
