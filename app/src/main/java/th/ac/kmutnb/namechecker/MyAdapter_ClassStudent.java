package th.ac.kmutnb.namechecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter_ClassStudent extends BaseAdapter {

    private List<Data_studentclass> mDatas;
    private LayoutInflater mLayoutInflater;

    public MyAdapter_ClassStudent(Context context, List<Data_studentclass> aList) {
        mDatas = aList;
        mLayoutInflater = LayoutInflater.from(context);
    }


    static class ViewHolder {
        TextView namesubject;
        TextView sec;
        TextView timeclass;
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
            holder.namesubject = (TextView)view.findViewById(R.id.namesubject);
            holder.sec = (TextView)view.findViewById(R.id.sec);
            holder.timeclass = (TextView)view.findViewById(R.id.timeclass);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        String title = mDatas.get(position).getmText1();
        holder.namesubject.setText(title);
        holder.sec.setText(mDatas.get(position).getmText2());
        holder.timeclass.setText(mDatas.get(position).getmText3());

        return view;
    }
}
