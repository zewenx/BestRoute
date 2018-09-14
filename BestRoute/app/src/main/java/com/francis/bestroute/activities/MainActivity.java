package com.francis.bestroute.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.francis.bestroute.R;
import com.francis.bestroute.adapter.MainListAdapter;
import com.francis.bestroute.antAlgorithm.ACO;
import com.francis.bestroute.base.BaseActivity;
import com.francis.bestroute.utils.DistanceUtil;
import com.francis.bestroute.vo.JsonRootBean;
import com.francis.bestroute.vo.MainItemVO;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.dift.ui.SwipeToAction;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.list)
    RecyclerView listView;

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
        adapter = new MainListAdapter(datas, R.layout.layout_line);

        listView.setAdapter(adapter);

        new SwipeToAction(listView, new SwipeToAction.SwipeListener() {
            @Override
            public boolean swipeLeft(Object itemData) {
                return false;
            }

            @Override
            public boolean swipeRight(Object itemData) {
                return false;
            }

            @Override
            public void onClick(Object itemData) {

            }

            @Override
            public void onLongClick(Object itemData) {

            }
        });

        adapter.notifyDataSetChanged();

        add.setOnClickListener(this);
        cal.setOnClickListener(this);

        requestPermissions(new String[]{ACCESS_FINE_LOCATION}, 1);
        cal.setImageBitmap(textAsBitmap("GO", convertDpToPixel(20), Color.BLACK));
    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
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

        } else if (v.getId() == R.id.cal) {
            if (datas.size() > 2) {
                count = datas.size() * (datas.size() - 1) / 2;
                progressBar.show();
                String address = "";
                for (int i = 0; i < datas.size(); i++) {
                    address += "" + datas.get(i).getLatitude() + "," + datas.get(i).getLongitude() + "|";

                }
                DistanceUtil.getInstance(MainActivity.this).CalculateDistance(address, address);
            } else {
                toastText(R.string.need_more_places);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                MainItemVO vo = new MainItemVO();
                vo.setAddress(place.getName().toString());
                vo.setAddress2(place.getAddress().toString().replace(place.getName().toString() + ",", ""));
                vo.setPlace(place);
                datas.add(vo);
                adapter.notifyDataSetChanged();
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Subscribe
    public void onEventMainThread(String item) {
        // TODO Auto-generated method stub
        JsonRootBean object = new Gson().fromJson(item, JsonRootBean.class);

        System.out.println(new Gson().toJson(object));

        ACO aco = new ACO(datas.size(), 100, 100, 1.f, 5.f, 0.5f);
        aco.init(object);
        int[] best = aco.solve();
        int count = 0;
        for(int i =0;i<best.length-1;i++){
            datas.get(best[i]).setOrder(count);
            count++;
        }
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("data", (ArrayList<MainItemVO>)datas);
        intent.putExtra("order","order");
        startActivity(intent);
        progressBar.dismiss();
    }

}
