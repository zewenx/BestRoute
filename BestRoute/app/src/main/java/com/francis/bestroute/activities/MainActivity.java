package com.francis.bestroute.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.francis.bestroute.R;
import com.francis.bestroute.adapter.MainListAdapter;
import com.francis.bestroute.base.BaseActivity;
import com.francis.bestroute.utils.DistanceUtil;
import com.francis.bestroute.vo.MainItemVO;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MainActivity extends BaseActivity implements View.OnClickListener {


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

        listView.setAdapter(adapter);

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

                for (int i = 0; i < datas.size(); i++) {
                    String from = "" + datas.get(i).getLatitude() + "," + datas.get(i).getLongitude();
                    for (int j = i + 1; j < datas.size(); j++) {
                        String to = "" + datas.get(j).getLatitude() + "," + datas.get(i).getLongitude();
                        DistanceUtil.getInstance(MainActivity.this).CalculateDistance(from, to);
                    }
                }
            }else {
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
        System.out.println(item);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count--;
        if (count == 0) {
            progressBar.dismiss();
        }
    }

}
