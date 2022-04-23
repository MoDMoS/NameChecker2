package th.ac.kmutnb.namechecker.ui.Student;

public class Data_Student {
    private String Subject;
    private String Subject_Id;
    private String Sec;
    private String Time;
    private String Teacher;

    public Data_Student(String subject, String id, String sec, String time, String teacher) {
        Subject = subject;
        Subject_Id = id;
        Sec = sec;
        Time = time;
        Teacher = teacher;
    }

    public String getSubject() {
        return Subject;
    }

    public String getId() {
        return Subject_Id;
    }

    public String getSec() {
        return Sec;
    }

    public String getTime() {
        return Time;
    }

    public String getTeacher() {
        return Teacher;
    }
}
