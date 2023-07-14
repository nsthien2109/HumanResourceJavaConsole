package services;

import connection.NetworkConnection;
import models.Candidate;
import models.Experience;
import models.Fresher;
import models.Intern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExperienceService {
    private static PreparedStatement preparedStatement;

    public static void createCandidate(Experience canExp, Connection conn) {
        try {
            String query = "INSERT INTO experience VALUES (?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, canExp.getCandidateId());
            preparedStatement.setFloat(2, canExp.getExpInYear());
            preparedStatement.setString(3, canExp.getProSkill());
            preparedStatement.executeUpdate();
            System.out.println("Created candidate experience successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
