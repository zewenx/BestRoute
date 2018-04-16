package com.francis.bestroute.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.francis.bestroute.R;
import com.francis.bestroute.adapter.MainListAdapter;
import com.francis.bestroute.base.BaseActivity;
import com.francis.bestroute.vo.MainItemVO;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

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

    int count = 0;
    int PLACE_PICKER_REQUEST = 1;


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

        if (v.getId() == R.id.add) {
            try {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                startActivityForResult(builder.build(MainActivity.this), PLACE_PICKER_REQUEST);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (v.getId() == R.id.map) {

            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                MainItemVO vo = new MainItemVO();
                vo.setAddress(place.getAddress().toString());
                vo.setPlace(place);
                datas.add(vo);
                adapter.notifyDataSetChanged();
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

}
