package th.ac.kmutnb.namechecker.ui.Student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import th.ac.kmutnb.namechecker.Data_Student;
import th.ac.kmutnb.namechecker.MyAdapter_Student;
import th.ac.kmutnb.namechecker.R;
import th.ac.kmutnb.namechecker.Student_class_info;

public class Student_Main extends Fragment {

    MyAdapter_Student adapter;
    String TAG = "myapp";
    private List<Data_Student> datas;
    private String URL;
    private String username;
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        datas = new ArrayList<>();

        pref = getActivity().getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "not found");

        Log.i(TAG,username);
        URL = "http://192.168.1.41/Name_Checker/Student/class_list.php?username=" + username;

        JsonArrayRequest jsRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, String.valueOf(response));
                        Gson gson = new Gson();

                        JSONObject jsObj;   // = null;
                        for (int i=0; i < response.length(); i++ ) {
                            try {
                                jsObj = response.getJSONObject(i);
                                String title = jsObj.getString("Subject");
                                Log.d(TAG, title);

                                Data_Student dataitem = gson.fromJson(String.valueOf(jsObj), Data_Student.class);
                                datas.add(dataitem);
                                Log.d(TAG,"gson "+ dataitem.getSubject());
                                Log.i(TAG, String.valueOf(datas.size()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        if (datas.size() > -1) {
                            displayListview();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,error.toString());
                        Toast.makeText(getActivity().getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });  // Request

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsRequest);
    }

    public void displayListview(){
        Log.i(TAG,"display");
        Log.i(TAG, String.valueOf(datas));
        adapter = new MyAdapter_Student(getActivity(),datas);
        ListView lv = getActivity().findViewById(R.id.listview_S);
        lv.setOnItemClickListener(this::onItemClick);
        lv.setAdapter(adapter);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences.Editor editor =  pref.edit();
        editor.putString("subject",datas.get(i).getSubject());
        editor.putString("id",datas.get(i).getId());
        editor.putString("sec",datas.get(i).getSec());
        editor.putString("time",datas.get(i).getTime());
        editor.putString("teacher",datas.get(i).getTeacher());
        editor.commit();

        Intent itn = new Intent(getActivity(), Student_class_info.class);
        startActivity(itn);
    }
}