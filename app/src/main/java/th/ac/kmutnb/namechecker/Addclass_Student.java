package th.ac.kmutnb.namechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import java.util.HashMap;
import java.util.Map;

public class Addclass_Student extends AppCompatActivity {

    String TAG = "myapp";
    private EditText etPass;
    private String username, pass ="";
    private String URL = "http://192.168.1.41/Name_Checker/Student/addclass.php";
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclass_student);

        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "not found");

        etPass = findViewById(R.id.etCheckIn);
    }
    public  void Add(View view){
        pass = etPass.getText().toString().trim();

        if(!pass.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("success")){
                        Log.i(TAG,"success");
                        Log.i(TAG,response);
                        Intent intent = new Intent(Addclass_Student.this,Student_main.class);
                        startActivity(intent);
                        finish();
                    } else if(response.equals("failure")){
                        Log.i(TAG,"failure");
//                        Toast.makeText(Addclass_Teacher.this,"Section have",Toast.LENGTH_SHORT).show();
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
                    data.put("pass", pass);
                    Log.i(TAG, String.valueOf(data));
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this,"Field all",Toast.LENGTH_SHORT).show();
        }
    }
}