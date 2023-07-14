package models;

public class Certificate {
    private int certificateId;

    private int candidateId;
    private String certificateName;
    private String certificateRank;
    private String certificateDate;

    public Certificate(){

    }

    public Certificate(int certificateId, int candidateId, String certificateName, String certificateRank, String certificateDate) {
        this.certificateId = certificateId;
        this.candidateId = candidateId;
        this.certificateName = certificateName;
        this.certificateRank = certificateRank;
        this.certificateDate = certificateDate;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateRank() {
        return certificateRank;
    }

    public void setCertificateRank(String certificateRank) {
        this.certificateRank = certificateRank;
    }

    public String getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(String certificateDate) {
        this.certificateDate = certificateDate;
    }

    public void showCertificate(){
        System.out.println("Certificate here !!!!");
    }
}
