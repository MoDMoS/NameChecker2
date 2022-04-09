package th.ac.kmutnb.namechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java. util. Random;

public class Generate_Pass extends AppCompatActivity {

    private String randomstring = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pass);
    }

    public void generate(View view){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = 5;
        Random rand = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++){
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for (int i = 0; i < text.length; i++){
            randomstring += text[i];
        }

//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("รหัสเช็คชื่อ");
//        alert.setMessage(randomstring);
//        alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(generate_Pass.this,"Done",Toast.LENGTH_SHORT).show();
//            }
//        });

        TextView textView = findViewById(R.id.classpass);
        textView.setText(randomstring);
    }

    public void closepass(View view){
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("รหัสเช็คชื่อ");
//        alert.setMessage("ต้องการปิดรหัสหรือไม่");
//        alert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                randomstring = "";
//                TextView textView = findViewById(R.id.classpass);
//                textView.setText("");
//                Toast.makeText(generate_Pass.this,"ปิดรหัส",Toast.LENGTH_SHORT).show();
//            }
//        });
//        alert.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(generate_Pass.this,"ยกเลิก",Toast.LENGTH_SHORT).show();
//            }
//        });
        randomstring = "";
        TextView textView = findViewById(R.id.classpass);
        textView.setText("");
    }
}