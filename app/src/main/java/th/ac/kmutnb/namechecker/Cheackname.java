package th.ac.kmutnb.namechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Cheackname extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheackname);
    }
    public  void back(View view){
        Intent intent = new Intent(this,Student_main.class);
        startActivity(intent);
    }
}
