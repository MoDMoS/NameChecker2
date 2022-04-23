package th.ac.kmutnb.namechecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
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

public class Teacher_main extends AppCompatActivity implements AdapterView.OnItemClickListener {

    MyAdapter_Teacher adapter;
    String TAG = "myapp";
    private List<Data_Teacher> datas;
    private String URL;
    private String username;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        datas = new ArrayList<>();
        pref = getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
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
                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });  // Request

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsRequest);

    }

    public void displayListview(){
        Log.i(TAG,"display");
        Log.i(TAG, String.valueOf(datas));
        adapter = new MyAdapter_Teacher(Teacher_main.this,datas);
        ListView lv = findViewById(R.id.listview_S);
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences.Editor editor =  pref.edit();
        editor.putString("subject",datas.get(i).getSubject());
        editor.putString("sec",datas.get(i).getSec());
        editor.putString("time",datas.get(i).getTime());
        editor.putString("check_in",datas.get(i).getCheck_in());
        editor.commit();

        Intent itn = new Intent(this,Teacher_class_info.class);
        startActivity(itn);
    }

    public void btn_add(View view){
        Intent intent = new Intent(this, Addclass_Teacher.class);
        startActivity(intent);
    }
}