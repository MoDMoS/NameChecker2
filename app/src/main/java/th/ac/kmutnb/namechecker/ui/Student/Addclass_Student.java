package th.ac.kmutnb.namechecker.ui.Student;

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

import th.ac.kmutnb.namechecker.Nav_Menu;
import th.ac.kmutnb.namechecker.Nav_Menu2;
import th.ac.kmutnb.namechecker.R;

public class Addclass_Student extends Fragment {

    String TAG = "myapp";
    private EditText etCheckIn;
    private String username, pass ="";
    private String URL = "http://192.168.1.41/Name_Checker/Student/addclass.php";
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addclass_student, container, false);

        pref = getActivity().getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        username = pref.getString("username", "not found");

        etCheckIn = view.findViewById(R.id.etCheckIn);

        Button button = (Button) view.findViewById(R.id.btn_AddS);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                pass = etCheckIn.getText().toString().trim();
                if(!pass.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("success")){
                                Log.i(TAG,"success");
                                Log.i(TAG,response);
                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("เสร็จสิ้น");
                                alert.setMessage("เพิ่นห้องเรียนเรียบร้อย");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        etCheckIn.setText("");
                                        Intent intent = new Intent(getActivity(), Nav_Menu2.class);
                                        startActivity(intent);
                                    }
                                });
                                alert.create();
                                alert.show();
                            } else if(response.equals("failure")){
                                Log.i(TAG,"failure");
                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("ผิดพลาด");
                                alert.setMessage("รหัสเข้าห้องเรียนไม่ถูกต้อง");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(getActivity(), Nav_Menu.class);
                                        startActivity(intent);
                                    }
                                });
                                alert.create();
                                alert.show();
    //                        Toast.makeText(Addclass_Teacher.this,"Section have",Toast.LENGTH_SHORT).show();
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
                            data.put("username", username);
                            data.put("pass", pass);
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