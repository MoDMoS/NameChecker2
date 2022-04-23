package th.ac.kmutnb.namechecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter_Student extends BaseAdapter {

    private List<Data_Student> mDatas;
    private LayoutInflater mLayoutInflater;

    public MyAdapter_Student(Context context, List<Data_Student> aList) {
        mDatas = aList;
        mLayoutInflater = LayoutInflater.from(context);
    }


    static class ViewHolder {
        TextView subject;
        TextView sec;
        TextView id;
        TextView time;
        TextView teacher;
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
            view = mLayoutInflater.inflate(R.layout.row_layout_student,viewGroup,false);
            holder = new ViewHolder();
            holder.subject = (TextView)view.findViewById(R.id.namesubject);
            holder.id = (TextView)view.findViewById(R.id.id);
            holder.sec = (TextView)view.findViewById(R.id.sec);
            holder.time = (TextView)view.findViewById(R.id.time);
            holder.teacher = (TextView)view.findViewById(R.id.teacher);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.subject.setText(mDatas.get(position).getSubject());
        holder.id.setText(mDatas.get(position).getId());
        holder.sec.setText(mDatas.get(position).getSec());
        holder.time.setText(mDatas.get(position).getTime());
        holder.teacher.setText(mDatas.get(position).getTeacher());
        return view;
    }
}
