import ui.CandidateUI;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        boolean exit = false;
        while (!exit){
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to Human Resource System of FPT Software");
            System.out.println("Please select feature");
            System.out.println("1.Show list candidate");
            System.out.println("2.Add new candidate");
            System.out.println("3.Remove candidate");
            System.out.println("4.Update candidate");
            System.out.println("0.Exit");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> CandidateUI.getCandidates();
                case 2 -> CandidateUI.addCandidate();
                case 3 -> CandidateUI.deleteCandidate();
                case 4 -> CandidateUI.updateCandidate();
                case 0 -> exit = true;
                default -> System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }
}