package th.ac.kmutnb.namechecker.ui.Teacher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Random;

import th.ac.kmutnb.namechecker.Nav_Menu;
import th.ac.kmutnb.namechecker.R;
import th.ac.kmutnb.namechecker.ui.Student.Checkname;

public class Addclass_Teacher extends Fragment {

    private EditText etSubject, etId, etSec, etTime;
    private Button btn_AddT;
    private String URL = "http://192.168.1.41/Name_Checker/Teacher/addclass.php";
    private String subject, id, sec, time, username;
    private String randomstring = "";
    String TAG = "myapp";
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addclass_teacher, container, false);

        pref = getActivity().getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "not found");
        Log.i(TAG,username);

        etSubject =  view.findViewById(R.id.etSubject);
        etId = view.findViewById(R.id.etSubject_id);
        etSec = view.findViewById(R.id.etSection);
        etTime = view.findViewById(R.id.etTime);
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


        Button button = (Button) view.findViewById(R.id.btn_AddT);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                subject = etSubject.getText().toString().trim();
                id = etId.getText().toString().trim();
                sec = etSec.getText().toString().trim();
                time = etTime.getText().toString().trim();

                if(!subject.equals("") && !id.equals("") && !sec.equals("") && !time.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i(TAG, response);
                            if(response.equals("success")){
                                Log.i(TAG,"success");
                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("เสร็จสิ้น");
                                alert.setMessage("เพิ่มตอนเรียนเรียบร้อย");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(getActivity(), Nav_Menu.class);
                                        startActivity(intent);
                                    }
                                });
                                alert.create();
                                alert.show();
                            } else if(response.equals("failureSec")){
                                Log.i(TAG,"failureSec");
                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("ผิดพลาด");
                                alert.setMessage("ตอนเรียนนี้มีอยู่แล้ว");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });
                                alert.create();
                                alert.show();
                            }else if(response.equals("failureId")){
                                Log.i(TAG,"failureId");
                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("ผิดพลาด");
                                alert.setMessage("รหัสวิชานี้ถูกใช้ไปแล้ว");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });
                                alert.create();
                                alert.show();
                            }
                        }
                    }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
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
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    requestQueue.add(stringRequest);
                }else{
                    Toast.makeText(getActivity(),"Field all",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}