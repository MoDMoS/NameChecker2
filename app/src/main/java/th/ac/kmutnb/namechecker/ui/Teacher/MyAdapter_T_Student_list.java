package th.ac.kmutnb.namechecker.ui.Teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import th.ac.kmutnb.namechecker.R;
import th.ac.kmutnb.namechecker.ui.Teacher.Data_T_Student_list;

public class MyAdapter_T_Student_list extends BaseAdapter {
    private List<Data_T_Student_list> mDatas;
    private LayoutInflater mLayoutInflater;

    public MyAdapter_T_Student_list(Context context, List<Data_T_Student_list> aList) {
        mDatas = aList;
        mLayoutInflater = LayoutInflater.from(context);
    }


    static class ViewHolder {
        TextView name;
        TextView id;
        TextView count;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.row_layout_student_list,viewGroup,false);
            holder = new ViewHolder();
            holder.name = (TextView)view.findViewById(R.id.row8);
            holder.id = (TextView)view.findViewById(R.id.row9);
            holder.count = (TextView)view.findViewById(R.id.row10);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.name.setText(mDatas.get(position).getName());
        holder.id.setText(mDatas.get(position).getId());
        holder.count.setText(mDatas.get(position).getCount());

        return view;
    }
}
