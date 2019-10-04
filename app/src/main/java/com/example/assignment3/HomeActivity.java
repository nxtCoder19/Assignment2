package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.activity.StudentActivity;
import com.example.assignment3.adapter.Adapter;
import com.example.assignment3.model.ModelClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, Adapter.OnListItemClickListener {

    private static final int REQUEST_CODE =222 ;
    public static List<ModelClass> modelClasslist=new ArrayList<>();
    Button btnaddStudent;
    RecyclerView recyclerView;
    TextView tvTitle;
    private Dialog dialog1;
    Adapter adapter;
    ModelClass abc;
    private String name;
    private String mclass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();

        if(modelClasslist.size()!=0)
        {
            tvTitle.setVisibility(View.INVISIBLE);
            tvTitle.setClickable(false);
            tvTitle.setFocusable(false);
        }
    }

    private void initUi()

    {
        btnaddStudent = findViewById(R.id.btn_add_student);
        TextView tvSort = findViewById(R.id.tv_img_sort);
        TextView tvGrid = findViewById(R.id.tv_img_grid);
        recyclerView = findViewById(R.id.recycler_view);

        tvTitle = findViewById(R.id.tv_homeActivity_title);
        btnaddStudent.setOnClickListener(this);
        tvSort.setOnClickListener(this);
        tvGrid.setOnClickListener(this);
       setdataOnRecyclerView();
    }


    private void showPopuMenu()
    {
            TextView tvSort = findViewById(R.id.tv_img_sort);
            tvSort.setOnClickListener(this);
            PopupMenu popup = new PopupMenu(HomeActivity.this,tvSort);
            popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());

            popup.show();

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // Toast.makeText(getApplicationContext(),"You Clicked:"+item.getTitle(),Toast.LENGTH_SHORT).show();
                    switch (item.getItemId())
                    {
                        case R.id.popup_item_name:
                            Collections.sort(modelClasslist, ModelClass.StuNameComparator);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(),"You Clicked:"+item.getTitle(),Toast.LENGTH_SHORT).show();
                            return true;

                        case R.id.popup_item_roll:
                            Collections.sort(modelClasslist, ModelClass.StuRollno);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(),"You Clicked:"+item.getTitle(),Toast.LENGTH_SHORT).show();
                            return true;

                        default:
                            return false;

                    }
                }
            });
    }
    public void setdataOnRecyclerView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);



        adapter=new Adapter(modelClasslist,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setGridLayout()
    {

        GridLayoutManager gridLayoutManager=new GridLayoutManager(HomeActivity.this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter=new Adapter(modelClasslist,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


    public void setView()
    {
        Intent intent = new Intent(this,StudentActivity.class);
        intent.putExtra("Data",abc);
        intent.putExtra("is View",true);
        startActivityForResult(intent,2);
        dialog1.dismiss();
    }

    public void setDelete()
    {
        int mroll = abc.getMroll();
        for(int i=0;i<modelClasslist.size();i++)
        {
            if(mroll==modelClasslist.get(i).getMroll())
            {
                modelClasslist.remove(i);
                adapter.notifyDataSetChanged();
            }
        }
           dialog1.dismiss();
           if(modelClasslist.size()!=0)
           {
               tvTitle.setVisibility(View.INVISIBLE);
           }
           else
           {
               tvTitle.setVisibility(View.VISIBLE);
           }
    }

    public void setUpdate()
    {
        Intent intent = new Intent(this,StudentActivity.class);
        intent.putExtra("Data",abc);
        intent.putExtra("isUpdate",true);
        startActivityForResult(intent,REQUEST_CODE);
        dialog1.dismiss();
        adapter.notifyDataSetChanged();
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                String mroll = data.getStringExtra("mRoll");
                name= data.getStringExtra("name");
                mclass=data.getStringExtra("class");

                for(int i=0;i<modelClasslist.size();i++)
                {
                    ModelClass position = modelClasslist.get(i);
                    if(Integer.parseInt(mroll)==position.getMroll())
                    {
                        modelClasslist.get(i).setMroll(Integer.parseInt(mroll));
                        modelClasslist.get(i).setMname(name);
                        modelClasslist.get(i).setMclass(Integer.parseInt(mclass));
                    }
                }adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onListItemClick(ModelClass modelClass) {
        abc=modelClass;
        dialog1=new Dialog(HomeActivity.this);
        dialog1.setContentView(R.layout.dialog_box);

        TextView tvView =dialog1.findViewById(R.id.tv_dialog_view);
        TextView tvEdit =dialog1.findViewById(R.id.tv_dialog_edit);
        TextView tvDelete =dialog1.findViewById(R.id.tv_dialog_delete);
        tvView.setOnClickListener(this);
        tvEdit.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        dialog1.show();
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_add_student:
                Intent intent = new Intent(this,StudentActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_img_sort:
                showPopuMenu();
                break;

            case R.id.tv_img_grid:
                setGridLayout();
                break;

            case R.id.tv_dialog_view:

                setView();
                break;

            case R.id.tv_dialog_delete:
                setDelete();
                break;

            case R.id.tv_dialog_edit:
                setUpdate();
                break;

        }
    }

}


