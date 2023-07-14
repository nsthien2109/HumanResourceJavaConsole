package services;

import models.Certificate;
import models.Fresher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CertificateService {
    private static PreparedStatement preparedStatement;

    public static void createCertificate(Certificate certificate, Connection conn, int idCandidate) {
        try {
            String query = "INSERT INTO certificate (idCandidate, certificateName, certificateRank, certificateDate) VALUES (?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,idCandidate);
            preparedStatement.setString(2, certificate.getCertificateName());
            preparedStatement.setString(3, certificate.getCertificateRank());
            preparedStatement.setString(4, certificate.getCertificateDate());
            preparedStatement.executeUpdate();
            System.out.println("Created certificate"+ certificate.getCertificateName() +" successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
