package th.ac.kmutnb.namechecker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Student_main<joinclass> extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<Data_studentclass> datas = new ArrayList<>();        //สำคัญ
    static  String TAG1 = "myapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Data_studentclass item = new Data_studentclass("Mobile App","040613425" ,"ตอนเรียน 1" ,"KSB" ,"09.00" );
        datas.add(item);

        datas.add(new Data_studentclass("Mobile App","040613425" ,"ตอนเรียน 2" ,"KSB" ,"13.00" ));



        MyAdapter_ClassStudent adapter = new MyAdapter_ClassStudent(this,datas);  //เพิ่มมา
        ListView lv = findViewById(R.id.listview01);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this); //ดักจับ
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i(TAG1,String.valueOf(i));

        Toast.makeText(this, String.valueOf(i)+" " +datas.get(i).getmText1()+" " +datas.get(i).getmText2()+" "+datas.get(i).getmText3()+" "
                +datas.get(i).getmText4()+" " +datas.get(i).getmText5(), Toast.LENGTH_SHORT).show();

        //ส่งข้อมูลไปแสดงหน้า DetailpageActivity
        Intent itn = new Intent(this,DetailpageStudentclass.class);
        itn.putExtra("id",i);
        itn.putExtra("nameclass",datas.get(i).getmText1());
        itn.putExtra("idclass",datas.get(i).getmText2());
        itn.putExtra("secnumber",datas.get(i).getmText3());
        itn.putExtra("nameT",datas.get(i).getmText4());
        itn.putExtra("timeclass",datas.get(i).getmText5());
        startActivity(itn);
    }

    public void joinclass(View view){
        Intent itn = new Intent(this,Addclass_Student.class);
        startActivity(itn);
    }
}