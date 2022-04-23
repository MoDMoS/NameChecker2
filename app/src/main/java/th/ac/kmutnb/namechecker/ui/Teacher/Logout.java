package th.ac.kmutnb.namechecker.ui.Teacher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import th.ac.kmutnb.namechecker.Generate_Pass;
import th.ac.kmutnb.namechecker.MainActivity;
import th.ac.kmutnb.namechecker.Nav_Menu;
import th.ac.kmutnb.namechecker.R;

public class Logout extends Fragment {

    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getActivity().getSharedPreferences("MyPreRef", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  pref.edit();

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("ออกจากระบบ");
        alert.setMessage("ยืนยันที่จะออกจากระบบหรือไม่");
        alert.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"Done",Toast.LENGTH_SHORT).show();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "ยกเลิก", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create();
        AlertDialog dialog = alert.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }
}