package th.ac.kmutnb.namechecker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetailpageStudentclass extends AppCompatActivity {
    static  String TAG = "myapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpage_studentclass);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent itn = getIntent();
        int item_id = itn.getIntExtra("id",-1);
        String name_class = itn.getStringExtra("nameclass");
        String id_class = itn.getStringExtra("idclass");
        String sec_class = itn.getStringExtra("secnumber");
        String nameT_class = itn.getStringExtra("nameT");
        String time_class = itn.getStringExtra("timeclass");


        TextView tv2 = findViewById(R.id.classname);       //P-name
        tv2.setText(name_class);

        TextView tv3 = findViewById(R.id.idclass1);       //P-name
        tv3.setText(id_class);

        TextView tv4 = findViewById(R.id.secclass);       //P-name
        tv4.setText(sec_class);

        TextView tv5 = findViewById(R.id.nameT);       //P-name
        tv5.setText(nameT_class);

        TextView tv6 = findViewById(R.id.Timeclass);       //P-name
        tv6.setText(time_class);


//        Toast.makeText(this,String.valueOf(item_id),Toast.LENGTH_SHORT).show();
        Log.i(TAG,String.valueOf(item_id));
    }
    public  void checkname(View view){
        Intent intent = new Intent(this,Cheackname.class);
        startActivity(intent);
    }
}