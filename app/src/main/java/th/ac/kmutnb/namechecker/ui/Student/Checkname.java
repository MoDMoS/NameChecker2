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
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import th.ac.kmutnb.namechecker.R;

public class Checkname extends AppCompatActivity {

    static  String TAG = "myapp";
    EditText etPass;
    String username, subject, currentDateTime, Pass = "";
    SharedPreferences pref;
    String URL = "http://192.168.1.41/Name_Checker/Student/checkname.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheackname);

        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "not found");
        subject = pref.getString("subject", "not found");

        etPass = findViewById(R.id.etCheckIn);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM");
        currentDateTime = sdf.format(new Date());
    }
    public void Check_name(View view){
        Pass = etPass.getText().toString().trim();
        if(!Pass.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i(TAG,response);
                    if(response.equals("success")){
                        Log.i(TAG,"success");

                        AlertDialog.Builder alert = new AlertDialog.Builder(Checkname.this);
                        alert.setTitle("เสร็จสิ้น");
                        alert.setMessage("เช็คชื่อเรียบร้อย");
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Checkname.this, Student_class_info.class);
                                startActivity(intent);
                            }
                        });
                        alert.create();
                        alert.show();
                    } else if(response.equals("failure")){
                        Log.i(TAG,"failure");

                        AlertDialog.Builder alert = new AlertDialog.Builder(Checkname.this);
                        alert.setTitle("ผิดพลาด");
                        alert.setMessage("รหัสเช็ดชื่อไม่ถูกต้อง");
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                etPass.setText("");
                            }
                        });
                        alert.create();
                        alert.show();
                        Toast.makeText(Checkname.this,"Password fail",Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG,error.toString().trim());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("username", username);
                    data.put("pass", Pass);
                    data.put("date", currentDateTime);
                    Log.i(TAG, String.valueOf(data));
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(this,"Field pass",Toast.LENGTH_SHORT).show();
        }
    }
}
