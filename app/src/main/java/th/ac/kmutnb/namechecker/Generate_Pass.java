package th.ac.kmutnb.namechecker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import java. util. Random;

public class Generate_Pass extends AppCompatActivity {

    private String TAG = "myapp";
    private String randomstring = "";
    private String URL = "http://192.168.1.41/Name_Checker/Teacher/generate_pass.php";
    SharedPreferences pref;
    String username, subject, sec, check_in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pass);

        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "Not found");
        subject = pref.getString("subject", "Not found");
        sec = pref.getString("sec", "Not found");
        check_in = pref.getString("check_in", " ");

        TextView textView = findViewById(R.id.classpass);
        textView.setText(check_in);
    }

    public void generate(View view){
        if(check_in.length() < 4){
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int length = 5;
            Random rand = new Random();
            char[] text = new char[length];
            for (int i = 0; i < length; i++){
                text[i] = characters.charAt(rand.nextInt(characters.length()));
            }
            for (int i = 0; i < text.length; i++){
                check_in += text[i];
            }
            TextView textView = findViewById(R.id.classpass);
            textView.setText(check_in);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Log.i(TAG, "success");

                        AlertDialog.Builder alert = new AlertDialog.Builder(Generate_Pass.this);
                        alert.setTitle("รหัสเช็คชื่อ");
                        alert.setMessage(check_in);
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Generate_Pass.this,"Done",Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor =  pref.edit();
                                editor.putString("check_in", check_in);
                                editor.commit();
                            }
                        });
                        alert.create();
                        alert.show();

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
                    data.put("pass", check_in);
                    data.put("subject", subject);
                    data.put("sec", sec);
                    data.put("check", "generate");
                    Log.i(TAG, String.valueOf(data));
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(Generate_Pass.this);
            alert.setTitle("รหัสเช็คชื่อ");
            alert.setMessage("Close old password");
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(Generate_Pass.this,"Done",Toast.LENGTH_SHORT).show();
                }
            });
            alert.create();
            alert.show();
        }
    }

    public void closepass(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("รหัสเช็คชื่อ");
        alert.setMessage("ต้องการปิดรหัสหรือไม่");
        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Log.i(TAG, "success");
                            check_in = "";
                            SharedPreferences.Editor editor = pref.edit();
                            editor.remove("check_in");
                            editor.commit();
                            TextView textView = findViewById(R.id.classpass);
                            textView.setText("");

                        } else if (response.equals("failure")) {
                            Log.i(TAG, "failure");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        Log.i(TAG, error.toString().trim());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("pass", randomstring);
                        data.put("subject", subject);
                        data.put("sec", sec);
                        data.put("check", "close");
                        Log.i(TAG, String.valueOf(data));
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

                Toast.makeText(Generate_Pass.this, "Password close", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Generate_Pass.this, "ยกเลิก", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create();
        AlertDialog dialog = alert.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
    }
}