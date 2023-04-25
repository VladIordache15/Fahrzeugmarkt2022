import controller.Controller;
import repository.AdsRepository;
import repository.TransactionRepository;
import repository.UserRepository;
import repository.db_repo.DBCarRepository;
import repository.db_repo.DBTransactionRepository;
import repository.db_repo.DBUserRepository;
import view.View;

import javax.swing.text.View;
import java.controller.Controller;
import java.repository.AdsRepository;
import java.repository.TransactionRepository;
import java.repository.UserRepository;
import java.repository.db_repo.DBCarRepository;
import java.repository.db_repo.DBTransactionRepository;
import java.repository.db_repo.DBUserRepository;

public class Main
{
    public static void main(String[] args)
    {
        UserRepository userRepository = new DBUserRepository();
        AdsRepository adsRepository = new DBCarRepository();
        TransactionRepository transactionRepository = new DBTransactionRepository();
        Controller controller = new Controller(userRepository, adsRepository, transactionRepository, false);
        View view = new View(controller, userRepository, adsRepository);

        view.mainMenu();
    }
}