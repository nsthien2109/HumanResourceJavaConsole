package models;

import java.util.Arrays;

public class Experience extends Candidate{
    private float expInYear;
    private String proSkill;


    public Experience(int candidateId, String fullName, String birthDay, String email, String phoneNumber, int candidateType, int candidateCount, float expInYear, String proSkill) {
        super(candidateId, fullName, birthDay, email, phoneNumber, candidateType, candidateCount);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public Experience() {
        super();
    }

    public float getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(float expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    @Override
    public void showMe(){
        System.out.println(
                "Candidate Name : " + getFullName() + "\n" +
                "Birthday : " + getBirthDay() + "\n" +
                "Email : " + getEmail() + "\n" +
                "Phone : " + getPhoneNumber() + "\n" +
                "Type : Experience\n" +
                "Experience Years: " + expInYear + "\n" +
                "Professional Skills : " + proSkill + "\n"
        );
    }
}
