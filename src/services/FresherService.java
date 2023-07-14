package services;

import connection.NetworkConnection;
import models.Experience;
import models.Fresher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FresherService {
    private static PreparedStatement preparedStatement;

    public static void createCandidate(Fresher can, Connection conn) {
        try {
            String query = "INSERT INTO fresher VALUES (?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, can.getCandidateId());
            preparedStatement.setString(2, can.getGraduationDate());
            preparedStatement.setString(3, can.getGraduationRank());
            preparedStatement.setString(4, can.getEducation());
            preparedStatement.executeUpdate();
            System.out.println("Created candidate fresher successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
