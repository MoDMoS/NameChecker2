package th.ac.kmutnb.namechecker.ui.Teacher;

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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmutnb.namechecker.R;

public class Teacher_main extends Fragment {

    MyAdapter_Teacher adapter;
    String TAG = "myapp";
    private List<Data_Teacher> datas;
    private String URL;
    private String username;
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void displayListview(){
        Log.i(TAG,"display");
        Log.i(TAG, String.valueOf(datas));
        adapter = new MyAdapter_Teacher(getActivity(),datas);
        ListView lv = this.getActivity().findViewById(R.id.listview_T);
        lv.setOnItemClickListener(this::onItemClick);
        lv.setAdapter(adapter);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences.Editor editor =  pref.edit();
        editor.putString("subject",datas.get(i).getSubject());
        editor.putString("sec",datas.get(i).getSec());
        editor.putString("time",datas.get(i).getTime());
        editor.putString("check_in",datas.get(i).getCheck_in());
        editor.commit();

//        Teacher_class_info teacher_class_info = new Teacher_class_info();
//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(null)
//                .add(R.id.nav_host_fragment_content_menu,teacher_class_info)
//                .commit();
        Intent intent = new Intent(getActivity(), Teacher_class_info.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teacher_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG,"Resume");
        datas = new ArrayList<>();
        pref = this.getActivity().getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "not found");
        Log.i(TAG, username);
        URL = "http://192.168.1.41/Name_Checker/Teacher/class_list.php?username=" + username;

        SharedPreferences.Editor editor =  pref.edit();
        editor.remove("check_in");
        editor.commit();

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

                                Data_Teacher dataitem = gson.fromJson(String.valueOf(jsObj), Data_Teacher.class);
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
//                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });  // Request

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(jsRequest);
    }
}