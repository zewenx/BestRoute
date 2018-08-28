package com.francis.bestroute.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
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
    FloatingActionButton button;
    @Bind(R.id.list)
    SwipeMenuListView listView;

    @Bind(R.id.add)
    FloatingActionButton add;
    @Bind(R.id.cal)
    FloatingActionButton cal;

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

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon(R.drawable.baseline_delete_black_24);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView.setMenuCreator(creator);
        listView.setAdapter(adapter);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

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
