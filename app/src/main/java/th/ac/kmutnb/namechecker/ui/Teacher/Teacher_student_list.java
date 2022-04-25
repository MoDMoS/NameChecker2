package th.ac.kmutnb.namechecker.ui.Teacher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

import th.ac.kmutnb.namechecker.R;

public class Teacher_student_list extends AppCompatActivity {

    MyAdapter_T_Student_list adapter;
    String TAG = "myapp";
    private List<Data_T_Student_list> datas;
    private String subject, sec;
    private String URL;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        datas = new ArrayList<>();

        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        subject = pref.getString("subject", "Not found");
        sec = pref.getString("sec", "Not found");
        URL = "http://192.168.1.41/Name_Checker/Teacher/student_list.php?subject="+subject+"&sec="+sec;

        JsonArrayRequest jsRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(!response.equals(null)){
                            Log.i(TAG, String.valueOf(response));
                            Gson gson = new Gson();

                            JSONObject jsObj;   // = null;
                            for (int i=0; i < response.length(); i++ ) {
                                try {
                                    jsObj = response.getJSONObject(i);
                                    String title = jsObj.getString("Name");
                                    Log.d(TAG, title);

                                    Data_T_Student_list dataitem = gson.fromJson(String.valueOf(jsObj), Data_T_Student_list.class);
                                    datas.add(dataitem);
                                    Log.d(TAG,"gson "+ dataitem.getName());
                                    Log.i(TAG, String.valueOf(datas.size()));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (datas.size() > -1) {
                                displayListview();
                            }
                        }else{
                            AlertDialog.Builder alert = new AlertDialog.Builder(Teacher_student_list.this);
                            alert.setTitle("รายชื่อนักศึกษา");
                            alert.setMessage("ไม่มีนักศึกษาในวิชานี้");
                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Teacher_student_list.this, Teacher_class_info.class);
                                    startActivity(intent);
                                }
                            });
                            alert.create();
                            alert.show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,error.toString());
                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsRequest);
    }

    public void displayListview(){
        Log.i(TAG,"display");
        Log.i(TAG, String.valueOf(datas));
        adapter = new MyAdapter_T_Student_list(this,datas);
        ListView lv = findViewById(R.id.listview_SL);
//        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////        SharedPreferences.Editor editor =  pref.edit();
////        editor.putString("subject",datas.get(i).getSubject());
////        editor.putString("sec",datas.get(i).getSec());
////        editor.putString("time",datas.get(i).getTime());
////        editor.putString("check_in",datas.get(i).getCheck_in());
////        editor.commit();
//
////        Intent itn = new Intent(this,Teacher_class_info.class);
////        startActivity(itn);
//    }

}