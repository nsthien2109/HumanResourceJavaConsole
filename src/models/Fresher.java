package models;

public class Fresher extends Candidate{
    private String graduationDate;
    private String graduationRank;
    private String education;

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setGraduationRank(String graduationRank) {
        this.graduationRank = graduationRank;
    }

    public Fresher(){}

    public Fresher(int candidateId, String fullName, String birthDay, String email, String phoneNumber, int candidateType, int candidateCount, String graduationDate, String graduationRank, String education) {
        super(candidateId, fullName, birthDay, email, phoneNumber, candidateType, candidateCount);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    @Override
    public void showMe() {
        System.out.println(
                "Candidate Name : " + getFullName() + "\n" +
                "Birthday : " + getBirthDay() + "\n" +
                "Email : " + getEmail() + "\n" +
                "Phone : " + getPhoneNumber() + "\n" +
                "Type : Fresher \n" +
                "Graduation Date: " + getGraduationDate() + "\n" +
                "Graduation Rank : " + getGraduationRank() + "\n" +
                "Education : " + education + "\n"
        );
    }
}
