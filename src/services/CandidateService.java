package services;

import connection.NetworkConnection;
import models.Candidate;
import models.Experience;
import models.Fresher;
import models.Intern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidateService {
    private static PreparedStatement preparedStatement;
    public static int candidateIdCurrent;

    public static List<Candidate> candidateList;

    public static void createCandidate(Candidate can, Connection conn) throws SQLException {

        try {
            String query = "INSERT INTO candidates (fullName, birthDay, email, phoneNumber, candidateType) VALUES (?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, can.getFullName());
            preparedStatement.setString(2, can.getBirthDay());
            preparedStatement.setString(3, can.getEmail());
            preparedStatement.setString(4, can.getPhoneNumber());
            preparedStatement.setInt(5, can.getCandidateType());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int lastInsertedId = generatedKeys.getInt(1);
                candidateIdCurrent = lastInsertedId;
                can.setCandidateId(lastInsertedId);
            }
            if(can instanceof Experience){
                ExperienceService.createCandidate((Experience) can, conn);
            }
            if(can instanceof Fresher){
                FresherService.createCandidate((Fresher) can, conn);
            }
            if(can instanceof Intern){
                InternService.createCandidate((Intern) can, conn);
            }
            System.out.println("Candidate created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Candidate> getAllCandidates(Connection conn){
        List<Candidate> candidates = new ArrayList<>();
        try{
            String query = "SELECT c.*, f.*, e.*, i.* FROM candidates c LEFT JOIN fresher f ON c.idCandidate = f.idCandidate LEFT JOIN Experience e ON c.idCandidate = e.idCandidate LEFT JOIN Intern i ON c.idCandidate = i.idCandidate";
            preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Candidate can;
                int type = resultSet.getInt("c.candidateType");
                if(type == 0){
                    can = new Experience();
                    can.setCandidateId(resultSet.getInt("c.idCandidate"));
                    can.setFullName(resultSet.getString("c.fullName"));
                    can.setBirthDay(resultSet.getString("c.birthDay"));
                    can.setPhoneNumber(resultSet.getString("c.phoneNumber"));
                    can.setEmail(resultSet.getString("c.email"));
                    ((Experience) can).setExpInYear(resultSet.getFloat("e.expInYear"));
                    ((Experience) can).setProSkill(resultSet.getString("e.proSkill"));
                    candidates.add(can);
                }else if(type == 1){
                    can = new Fresher();
                    can.setCandidateId(resultSet.getInt("c.idCandidate"));
                    can.setFullName(resultSet.getString("c.fullName"));
                    can.setBirthDay(resultSet.getString("c.birthDay"));
                    can.setPhoneNumber(resultSet.getString("c.phoneNumber"));
                    can.setEmail(resultSet.getString("c.email"));
                    ((Fresher) can).setGraduationDate(resultSet.getString("f.graduationDate"));
                    ((Fresher) can).setGraduationRank(resultSet.getString("f.graduationRank"));
                    ((Fresher) can).setEducation(resultSet.getString("f.education"));
                    candidates.add(can);
                } else if (type == 2) {
                    can = new Intern();
                    can.setCandidateId(resultSet.getInt("c.idCandidate"));
                    can.setFullName(resultSet.getString("c.fullName"));
                    can.setBirthDay(resultSet.getString("c.birthDay"));
                    can.setPhoneNumber(resultSet.getString("c.phoneNumber"));
                    can.setEmail(resultSet.getString("c.email"));
                    ((Intern) can).setMajors(resultSet.getString("i.majors"));
                    ((Intern) can).setSemester(resultSet.getString("i.semester"));
                    ((Intern) can).setUniversityName(resultSet.getString("i.universityName"));
                    candidates.add(can);
                }
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return candidates;

    }

    public static void updateCandidate(String newName, Connection conn, int idCandidate) throws SQLException {
        try{
            String query = "Select * from candidates where idCandidate = ?";
            preparedStatement = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setInt(1,idCandidate);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                resultSet.updateString("fullName", newName);
                resultSet.updateRow();
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e);
        }
        preparedStatement.close();
        conn.close();
    }

    public static void deleteCandidate(String email, Connection conn){
        try{
            String query = "SELECT * FROM candidates where email = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            int candidateType, candidateId;

            if(resultSet.next()){
                candidateType = resultSet.getInt("candidateType");
                candidateId = resultSet.getInt("idCandidate");
                String deleteChildQuery = "";
                switch (candidateType){
                    case 0 -> deleteChildQuery = "DELETE FROM experience WHERE idCandidate = ?";
                    case 1 -> deleteChildQuery = "DELETE FROM fresher WHERE idCandidate = ?";
                    case 2 -> deleteChildQuery = "DELETE FROM intern WHERE idCandidate = ?";
                }
                if(!deleteChildQuery.isEmpty()){
                    preparedStatement = conn.prepareStatement(deleteChildQuery);
                    preparedStatement.setInt(1, candidateId);
                    int rowsDeleted = preparedStatement.executeUpdate();
                    System.out.println("Rows deleted from " + candidateType + " table: " + rowsDeleted);
                    preparedStatement.close();
                }

                String queryDelete = "DELETE FROM candidate WHERE idCandidate = ?";
                preparedStatement = conn.prepareStatement(queryDelete);
                preparedStatement.setInt(1, candidateId);
                int rowsDeletedCandidate = preparedStatement.executeUpdate();
                System.out.println("Rows deleted from Candidate table: " + rowsDeletedCandidate);
            }else{
                System.out.println("Email không tồn tai");
            }

            conn.commit();
            resultSet.close();
            preparedStatement.close();
            conn.close();
        }catch (SQLException e){
            System.out.println("Lỗi");
        }
    }
}
