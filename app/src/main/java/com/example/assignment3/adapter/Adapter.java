package com.example.assignment3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment3.R;
import com.example.assignment3.model.ModelClass;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder>
{
    public List<ModelClass> modelClasslist;
    private OnListItemClickListener onListitemClickListener;

    public Adapter(List<ModelClass> modelClassList,OnListItemClickListener mOnitemClickListener) {
        this.modelClasslist = modelClassList;
        this.onListitemClickListener = mOnitemClickListener;

    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        final  ModelClass modelClass=modelClasslist.get(position);
        String name = modelClasslist.get(position).getMname();
        int std = modelClasslist.get(position).getMclass();
        holder.setData(name,std);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onListitemClickListener.onListItemClick(model);
            onListitemClickListener.onListItemClick(modelClass);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelClasslist.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder
   {
       private TextView tvName;
       private TextView tvClass;

       public Viewholder(@NonNull View itemView) {

           super(itemView);
           tvName = itemView.findViewById(R.id.tv_name_of_stu);
           tvClass = itemView.findViewById(R.id.tv_class_of_stu);
       }

       private void setData(String name,int std)
       {
           tvName.setText(name);
           tvClass.setText(Integer.toString(std));
       }
   }

    public  interface OnListItemClickListener {
        void onListItemClick(ModelClass modelClass);
    }

}

