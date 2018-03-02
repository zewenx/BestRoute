package com.francis.bestroute.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.francis.bestroute.R;
import com.francis.bestroute.adapter.MainListAdapter;
import com.francis.bestroute.base.BaseActivity;
import com.francis.bestroute.vo.MainItemVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.map)
    Button button;
    @Bind(R.id.list)
    ListView listView;

    @Bind(R.id.add)
    Button add;
    @Bind(R.id.cal)
    Button cal;

    int count=0;



    List<MainItemVO> datas = new ArrayList<>();
    MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void init() {
        super.init();
         adapter = new MainListAdapter(this, datas, R.layout.layout_line);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        button.setOnClickListener(this);

        add.setOnClickListener(this);

        requestPermissions(new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onClick(View v) {

        if( v.getId() == R.id.add ) {
//            MainItemVO vo = new MainItemVO();
//            vo.setAddress("sadfasdf");
//            vo.setNum(count++);
//            datas.add(vo);
//            adapter.notifyDataSetChanged();

            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivityForResult(intent,1);

        }else if(v.getId() == R.id.map){

            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            datas.add(((MainItemVO) data.getExtras().get("data")).setNum(count++));
            adapter.notifyDataSetChanged();
;        }
    }

}
