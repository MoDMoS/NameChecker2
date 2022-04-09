package th.ac.kmutnb.namechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Teacher_class_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_info);

        Intent itn = getIntent();
        String subject = itn.getStringExtra("subject");
        String sec = itn.getStringExtra("sec");
        String time = itn.getStringExtra("time");

        TextView textView = findViewById(R.id.classname);
        textView.setText(subject);
        TextView textView1 = findViewById(R.id.Sec);
        textView1.setText(sec);
        TextView textView2 = findViewById(R.id.Time);
        textView2.setText(time);
    }

    public void createPass(View view){
        Intent intent = new Intent(this, Generate_Pass.class);

        startActivity(intent);
    }
    public void studentlist(View view){
        Intent intent = new Intent(this, Teacher_student_list.class);

        startActivity(intent);
    }
    public void home(View view){
        Intent intent = new Intent(this,Teacher_main.class);

        startActivity(intent);
    }
}