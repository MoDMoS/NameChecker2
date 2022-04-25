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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import th.ac.kmutnb.namechecker.R;
import th.ac.kmutnb.namechecker.ui.Teacher.Data_T_Student_list;
import th.ac.kmutnb.namechecker.ui.Teacher.Data_Teacher;

public class Student_class_info extends AppCompatActivity {

    static  String TAG = "myapp";
    TextView txtsubject, txtid, txtsec, txtteacher, txttime;
    private String username, subject, id, sec, teacher, time, date, dateNow, datepass;
    SharedPreferences pref;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_info);

        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "not found");
        subject = pref.getString("subject","not found");
        id = pref.getString("id","not found");
        sec = pref.getString("sec","not found");
        teacher = pref.getString("teacher","not found");
        time = pref.getString("time","not found");

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

        URL = "http://192.168.1.41/Name_Checker/Student/date.php?subject=" + subject + "&sec=" + sec + "&username=" + username;

        JsonArrayRequest jsRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "TEST" + String.valueOf(response));
                        Gson gson = new Gson();

                        JSONObject jsObj;   // = null;
                        for (int i=0; i < response.length(); i++ ) {
                            try {
                                jsObj = response.getJSONObject(i);
                                Log.i(TAG, String.valueOf(jsObj.getString("Date")));
                                Log.i(TAG, String.valueOf(jsObj.getString("Last")));
                                datepass = jsObj.getString("Date");
                                date = jsObj.getString("Last");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,error.toString());
                    }
                });  // Request
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsRequest);
    }

    public  void checkname(View view){
        Log.i(TAG, "datepass " + datepass);
        Log.i(TAG, "date " + date);
        Log.i(TAG, "dateNow " + dateNow);
        if(datepass.equals(dateNow)){
            if(date.equals(dateNow)){
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
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(Student_class_info.this);
            alert.setTitle("ผิดพลาด");
            alert.setMessage("รหัสเช็คชื่อยังไม่สามารถใช้ได้ตอนนี้");
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            alert.create();
            alert.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        URL = "http://192.168.1.41/Name_Checker/Student/date.php?subject=" + subject + "&sec=" + sec;

        JsonArrayRequest jsRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.i(TAG, String.valueOf(response.getString(0)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Gson gson = new Gson();

                        JSONObject jsObj;   // = null;
                        for (int i=0; i < response.length(); i++ ) {
                            try {
                                jsObj = response.getJSONObject(i);
                                Log.i(TAG, String.valueOf(jsObj.getString("Date")));
                                Log.i(TAG, String.valueOf(jsObj.getString("Last")));
                                datepass = jsObj.getString("Date");
                                date = jsObj.getString("Last");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,error.toString());
                    }
                });  // Request
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsRequest);
    }
}