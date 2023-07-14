package ui;

import connection.NetworkConnection;
import models.Candidate;
import models.Experience;
import models.Fresher;
import models.Intern;
import services.CandidateService;
import validation.Validation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CandidateUI {
    public static List<Candidate> candidates = new ArrayList<>();
    public static Connection conn = NetworkConnection.getConnection();

    private static Scanner sc = new Scanner(System.in);
    public static void addCandidate() throws SQLException {
        String name, birthday, email, phone,graduationDate, graduationRank, education,majors, semester;
        int option, expYear;
        Candidate can;
        String proSkill;
        System.out.println("Chọn loại ứng viên cần thêm : ");
        System.out.println("1. Experience");
        System.out.println("2. Fresher");
        System.out.println("3. Intern");
        System.out.println("0. Back");
        option = Integer.parseInt(sc.nextLine());

        System.out.println("Vui lòng nhập tên ứng viên : ");
        name = sc.nextLine();
        while (true) {
            System.out.println("Vui lòng nhập email ứng viên : ");
            email = sc.nextLine();

            if (Validation.emailValidate(email)) {
                break;
            } else {
                System.out.println("Invalid email address. Please try again.");
            }
        }
        while (true) {
            System.out.println("Vui lòng nhập năm sinh ứng viên (YY-MM-DD) : ");
            birthday =  sc.nextLine();

            if (Validation.birthDayValidate(birthday)) {
                break;
            } else {
                System.out.println("Invalid birthday. Please try again.");
            }
        }
        System.out.println("Vui lòng nhập phone ứng viên : ");
        phone = sc.nextLine();

        switch (option) {
            case 1 -> {
                System.out.println("Vui lòng nhập số năm kinh nghiệm ứng viên :");
                expYear = Integer.parseInt(sc.nextLine());
                System.out.println("Vui lòng nhập kĩ năng chuyên nghiệp :");
                proSkill = sc.nextLine();
                can = new Experience();
                can.setCandidateType(0);
                can.setFullName(name);
                can.setEmail(email);
                can.setPhoneNumber(phone);
                can.setBirthDay(birthday);
                ((Experience) can).setExpInYear(expYear);
                ((Experience) can).setProSkill(proSkill);
                CandidateService.createCandidate(can,conn);
                CertificateUI.createCertificate(CandidateService.candidateIdCurrent, sc, conn);
                break;
            }
            case 2 -> {
                System.out.println("Vui lòng nhập ngày tốt nghiệp ứng viên :");
                graduationDate = sc.nextLine();
                System.out.println("Vui lòng nhập loại tốt nghiệp :");
                graduationRank = sc.nextLine();
                System.out.println("Vui lòng nhập tên trường đại học :");
                education = sc.nextLine();
                can = new Fresher();
                can.setCandidateType(1);
                can.setFullName(name);
                can.setEmail(email);
                can.setPhoneNumber(phone);
                can.setBirthDay(birthday);
                ((Fresher) can).setGraduationDate(graduationDate);
                ((Fresher) can).setGraduationRank(graduationRank);
                ((Fresher) can).setEducation(education);
                CandidateService.createCandidate(can,conn);
                CertificateUI.createCertificate(CandidateService.candidateIdCurrent, sc, conn);
                System.out.println("Add candidates Successfully");
                break;
            }
            case 3 -> {
                System.out.println("Vui lòng nhập ngành học ứng viên :");
                majors = sc.nextLine();
                System.out.println("Vui lòng nhập học kỳ :");
                semester = sc.nextLine();
                System.out.println("Vui lòng nhập tên trường đại học :");
                education = sc.nextLine();
                can = new Intern();
                can.setCandidateType(2);
                can.setFullName(name);
                can.setEmail(email);
                can.setPhoneNumber(phone);
                can.setBirthDay(birthday);
                ((Intern) can).setMajors(majors);
                ((Intern) can).setSemester(semester);
                ((Intern) can).setUniversityName(education);
                CandidateService.createCandidate(can,conn);
                CertificateUI.createCertificate(CandidateService.candidateIdCurrent, sc, conn);
                break;
            }
            default -> System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    public static void getCandidates(){
        candidates = CandidateService.getAllCandidates(conn);
        System.out.println("================================== ĐÂY LÀ DANH SÁCH ỨNG VIÊN ==================================");
        for (Candidate i : candidates) {
            i.showMe();
        }
        System.out.println("==============================================================================================");
    }

    public static void deleteCandidate(){
        String email;
        System.out.println("Vui lòng nhập email ứng viên cần xóa : ");
        email = sc.nextLine();
        CandidateService.deleteCandidate(email, conn);
    }

    public static void updateCandidate() throws SQLException {
        String columnName = "fullName";
        String newName;
        int id;
        System.out.println("=== Demo update candidate name ===");
        System.out.println("Nhâập Id candidate cần cập nhật : ");
        id = Integer.parseInt(sc.nextLine());
        System.out.println("Vui lòng nhập tên mới : ");
        newName = sc.nextLine();
        CandidateService.updateCandidate(newName, conn, id);
    }

    public static void insertCandidate(){ // advance , use result set , not use sql query statement

    }
}
