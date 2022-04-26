package th.ac.kmutnb.namechecker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import th.ac.kmutnb.namechecker.ui.Student.Checkname;
import th.ac.kmutnb.namechecker.ui.Student.Student_class_info;

public class Register extends AppCompatActivity {
    private EditText etName, etUser, etId, etPassword, etReenterPassword;
    private TextView tvStatus;
    private Button btnRegister;
    private String URL = "http://192.168.1.41/Name_Checker/login/register.php";
    private String id, name, username, password, reenterPassword, role;
    private RadioGroup radioGroup;
    String TAG = "myapp";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        etName = findViewById(R.id.etName);
        etUser = findViewById(R.id.etUser);
        etId = findViewById(R.id.etId);
        etPassword = findViewById(R.id.Password);
        etReenterPassword = findViewById(R.id.etReenterPassword);
        tvStatus = findViewById(R.id.tvStatus);
        btnRegister = findViewById(R.id.btnRegister);
        name = id = username = password = reenterPassword = role = "";
        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.btn_student:
                        role = "Student";
                        break;
                    case R.id.btn_teacher:
                        role = "Teacher";
                        break;
                }
            }
        });

    }

    public void save(View view) {
        Log.i(TAG,"save");
        name = etName.getText().toString().trim();
        username = etUser.getText().toString().trim();
        id = etId.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        reenterPassword = etReenterPassword.getText().toString().trim();

        if(!password.equals(reenterPassword)){
            AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
            alert.setTitle("ผิดพลาด");
            alert.setMessage("การยืนยันรหัสผ่านไม่ถูกต้อง");
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            alert.create();
            alert.show();
        }
        else if(!name.equals("") && !id.equals("") && !password.equals("") && !username.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i(TAG,response);
                    if (response.equals("success")) {
                        tvStatus.setText("Successfully registered.");
                        btnRegister.setClickable(false);
                        AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
                        alert.setTitle("เสร็จสิ้น");
                        alert.setMessage("สมัครสมาชิคเรียบร้อย");
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Register.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        alert.create();
                        alert.show();
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
                        alert.setTitle("ผิดพลาด");
                        alert.setMessage("Id หรือ ชื่อผู้ใช้ถูกใช้ไปแล้ว");
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        alert.create();
                        alert.show();
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        tvStatus.setText("ID or username is already in use.");
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
                    data.put("name", name);
                    data.put("username", username);
                    data.put("id", id);
                    data.put("password", password);
                    data.put("role", role);
                    Log.i(TAG, String.valueOf(data));
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
