package th.ac.kmutnb.namechecker.ui.Teacher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import th.ac.kmutnb.namechecker.Nav_Menu;
import th.ac.kmutnb.namechecker.R;

public class Teacher_class_info extends AppCompatActivity {

    String TAG = "myapp";
    SharedPreferences pref;
    private String URL = "http://192.168.1.41/Name_Checker/Teacher/generate_pass.php";
    private String subject, sec, time;
    TextView textView, textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_info);

        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);

        subject = pref.getString("subject", "Not found");
        sec = pref.getString("sec", "Not found");
        time = pref.getString("time", "Not found");

        textView = findViewById(R.id.Subject);
        textView.setText(subject);
        textView1 = findViewById(R.id.Sec);
        textView1.setText(sec);
        textView2 = findViewById(R.id.Time);
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

    public void delete_class(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("ลบคลาส");
        alert.setMessage("ยืนยันการลบคลาส");
        alert.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Log.i(TAG, "success");
                            Intent intent = new Intent(Teacher_class_info.this, Nav_Menu.class);
                            startActivity(intent);
                        }else if(response.equals("failure")) {
                            Log.i(TAG, "failure");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        Log.i(TAG,error.toString().trim());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("pass", "");
                        data.put("subject", subject);
                        data.put("sec", sec);
                        data.put("check", "delete");
                        Log.i(TAG, String.valueOf(data));
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

                Toast.makeText(Teacher_class_info.this,"ลบคลาสเรียบร้อย",Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Teacher_class_info.this,"ยกเลิก",Toast.LENGTH_SHORT).show();
            }
        });
        alert.create();
        AlertDialog dialog = alert.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
    }
}