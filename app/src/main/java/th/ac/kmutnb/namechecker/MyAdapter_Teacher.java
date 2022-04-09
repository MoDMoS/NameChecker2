package th.ac.kmutnb.namechecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter_Teacher extends BaseAdapter {
    private List<Data_Teacher> mDatas;
    private LayoutInflater mLayoutInflater;

    public MyAdapter_Teacher(Context context, List<Data_Teacher> aList) {
        mDatas = aList;
        mLayoutInflater = LayoutInflater.from(context);
    }


    static class ViewHolder {
        TextView subject;
        TextView section;
        TextView time;
        TextView code;
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
            view = mLayoutInflater.inflate(R.layout.row_layout_teacher,viewGroup,false);
            holder = new ViewHolder();
            holder.subject = (TextView)view.findViewById(R.id.row1);
            holder.section = (TextView)view.findViewById(R.id.row2);
            holder.time = (TextView)view.findViewById(R.id.row3);
            holder.code = (TextView)view.findViewById(R.id.row4);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        String subject = mDatas.get(position).getSubject();
        holder.subject.setText(subject);
        holder.section.setText(mDatas.get(position).getSec());
        holder.time.setText(mDatas.get(position).getTime());
        holder.code.setText(mDatas.get(position).getPass());

        return view;
    }
}
