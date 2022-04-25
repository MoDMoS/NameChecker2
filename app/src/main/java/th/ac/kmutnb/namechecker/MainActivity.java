package th.ac.kmutnb.namechecker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    String TAG = "myapp";
    private EditText etUsername, etPassword;
    private String username, password, role;
    private String URL = "http://192.168.1.41/Name_Checker/login/login.php";
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = password = "";
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "");
        role = pref.getString("role", "");
        if(role.equals("Student")){
            Intent intent = new Intent(MainActivity.this, Nav_Menu2.class);
            startActivity(intent);
            finish();
        }else if(role.equals("Teacher")){
            Intent intent = new Intent(MainActivity.this, Nav_Menu.class);
            startActivity(intent);
            finish();
        }
    }

    public void Login(View view) {

        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if(!username.equals("") && !password.equals("")){
            Log.i(TAG,"login");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response);
                    if (response.equals("Student")) {
                        SharedPreferences pref = getSharedPreferences("MyPreRef", MODE_PRIVATE);
                        SharedPreferences.Editor editor =  pref.edit();

                        editor.putString("username",username);
                        editor.putString("role",response);
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this, Nav_Menu2.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("Teacher")) {
                        SharedPreferences pref = getSharedPreferences("MyPreRef", MODE_PRIVATE);
                        SharedPreferences.Editor editor =  pref.edit();

                        editor.putString("username",username);
                        editor.putString("role",response);
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this, Nav_Menu.class);
                        startActivity(intent);
                        finish();
                    }  else if (response.equals("failure")) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("ผิดพลาด");
                        alert.setMessage("ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alert.create();
                        alert.show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("username", username);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
}