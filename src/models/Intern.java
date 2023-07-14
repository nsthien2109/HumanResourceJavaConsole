package models;

public class Intern extends Candidate{
    private String majors;
    private String semester;
    private String universityName;

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public Intern(){}


    public Intern(int candidateId, String fullName, String birthDay, String email, String phoneNumber, int candidateType, int candidateCount, String majors, String semester, String universityName) {
        super(candidateId, fullName, birthDay, email, phoneNumber, candidateType, candidateCount);
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    @Override
    public void showMe() {
        System.out.println(
                "Candidate Name : " + getFullName() + "\n" +
                "Birthday : " + getBirthDay() + "\n" +
                "Email : " + getEmail() + "\n" +
                "Phone : " + getPhoneNumber() + "\n" +
                "Type : Intern \n" +
                "Majors: " + majors + "\n" +
                "Semester : " + semester + "\n" +
                "University : " + universityName + "\n"
        );
    }
}
