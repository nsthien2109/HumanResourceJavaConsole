package services;

import connection.NetworkConnection;
import models.Fresher;
import models.Intern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InternService {
    private static PreparedStatement preparedStatement;

    public static void createCandidate(Intern can, Connection conn) {
        try {
            String query2 = "INSERT INTO intern VALUES (?,?,?,?)";
            preparedStatement = conn.prepareStatement(query2);
            preparedStatement.setInt(1, can.getCandidateId());
            preparedStatement.setString(2, can.getMajors());
            preparedStatement.setString(3, can.getSemester());
            preparedStatement.setString(4, can.getUniversityName());
            preparedStatement.executeUpdate();
            System.out.println("Created candidate intern successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
