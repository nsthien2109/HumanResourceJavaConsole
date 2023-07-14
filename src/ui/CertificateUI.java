package ui;

import models.Certificate;
import services.CertificateService;

import java.sql.Connection;
import java.util.Scanner;

public class CertificateUI {
    public static void createCertificate(int candidateID, Scanner sc, Connection conn){
        int soLuongBang;
        String certificateName,certificateDate, certificateRank;
        Certificate certificate;
        System.out.println("Số lượng bằng cấp | chứng chỉ : ");
        soLuongBang = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < soLuongBang; i++) {
            System.out.println("Tên bằng cấp - chứng chỉ thứ "+(i + 1)+ ":");
            certificateName = sc.nextLine();
            System.out.println("Xếp hạng bằng cấp -  chứng chỉ "+(i + 1)+ ": ");
            certificateRank = sc.nextLine();
            System.out.println("Có giá trị đến ngày : ");
            certificateDate = sc.nextLine();
            certificate = new Certificate();
            certificate.setCandidateId(candidateID);
            certificate.setCertificateName(certificateName);
            certificate.setCertificateRank(certificateRank);
            certificate.setCertificateDate(certificateDate);
            CertificateService.createCertificate(certificate,conn, candidateID);
        }
    }
}
