package com.francis.bestroute.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.greenrobot.eventbus.EventBus;

public class DistanceUtil {
    private static DistanceUtil util;
    private Context mContext;

    private DistanceUtil(Context context) {
        mContext = context;
    }

    public static DistanceUtil getInstance(Context context) {
        if (util == null) {
            util = new DistanceUtil(context);
        }
        return util;
    }

    public void CalculateDistance(String from, String to) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "http://maps.googleapis.com/maps/api/distancematrix/" +
                "json?origins=" + from + "&destinations=" + to + "&mode=driving&" +
                "language=en-EN&sensor=false";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        EventBus.getDefault().post(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
        queue.add(stringRequest);
    }
}
