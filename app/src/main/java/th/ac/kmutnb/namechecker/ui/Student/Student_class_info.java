package th.ac.kmutnb.namechecker.ui.Student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import th.ac.kmutnb.namechecker.R;

public class Student_class_info extends AppCompatActivity {

    static  String TAG = "myapp";
    TextView txtsubject, txtid, txtsec, txtteacher, txttime;
    private String subject, id, sec, teacher, time, checkname, date, dateNow;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_info);

        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        subject = pref.getString("subject","not found");
        id = pref.getString("id","not found");
        sec = pref.getString("sec","not found");
        teacher = pref.getString("teacher","not found");
        time = pref.getString("time","not found");
        checkname = pref.getString("checkname", "not found");
        date = pref.getString("datetime","not found");

        txtsubject = findViewById(R.id.stu_subject);
        txtsubject.setText(subject);
        txtid = findViewById(R.id.stu_subject_id);
        txtid.setText(id);
        txtsec = findViewById(R.id.stu_sec);
        txtsec.setText(sec);
        txtteacher = findViewById(R.id.stu_teacher);
        txtteacher.setText(teacher);
        txttime = findViewById(R.id.stu_time);
        txttime.setText(time);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM");
        dateNow = sdf.format(new Date());
    }
    public  void checkname(View view){
        Log.i(TAG,checkname + subject);
        if(checkname.equals(subject) && date.equals(dateNow)){
            AlertDialog.Builder alert = new AlertDialog.Builder(Student_class_info.this);
            alert.setTitle("ผิดพลาด");
            alert.setMessage("คุณเช็คชื่อไปแล้ว");
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alert.create();
            alert.show();
        }else{
            Intent intent = new Intent(this, Checkname.class);
            startActivity(intent);
        }
    }
}