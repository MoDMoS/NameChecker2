package th.ac.kmutnb.namechecker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import java. util. Random;

public class Addclass_Teacher extends AppCompatActivity {

    private EditText etSubject, etId, etSec, etTime;
    private Button btn_AddT;
    private String URL = "http://192.168.1.41/Name_Checker/Teacher/addclass.php";
    private String subject, id, sec, time, username;
    private String randomstring = "";
    String TAG = "myapp";
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclass_teacher);

        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "not found");
        Log.i(TAG,username);

        etSubject = findViewById(R.id.etSubject);
        etId = findViewById(R.id.etSubject_id);
        etSec = findViewById(R.id.etSection);
        etTime = findViewById(R.id.etTime);
        subject = id = sec = time = "";

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        int length = 8;
        Random rand = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++){
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for (int i = 0; i < text.length; i++){
            randomstring += text[i];
        }
    }

    public void addClass(View view){

        subject = etSubject.getText().toString().trim();
        id = etId.getText().toString().trim();
        sec = etSec.getText().toString().trim();
        time = etTime.getText().toString().trim();

        if(!subject.equals("") && !id.equals("") && !sec.equals("") && !time.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("success")){
                        Log.i(TAG,"success");
                        Log.i(TAG,response);
                        Intent intent = new Intent(Addclass_Teacher.this,Teacher_main.class);
                        intent.putExtra("name",username);
                        startActivity(intent);
                        finish();
                    } else if(response.equals("failure")){
                        Log.i(TAG,"failure");
//                        Toast.makeText(Addclass_Teacher.this,"Section have",Toast.LENGTH_SHORT).show();
                    }else if(response.equals("failure1")){
                        Log.i(TAG,"failure1");
//                        Toast.makeText(Addclass_Teacher.this,"Section have",Toast.LENGTH_SHORT).show();
                    }else if(response.equals("failure2")){
                        Log.i(TAG,"failure2");
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
                    data.put("subject", subject);
                    data.put("id", id);
                    data.put("sec", sec);
                    data.put("time", time);
                    data.put("username", username);
                    data.put("pass", randomstring);
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