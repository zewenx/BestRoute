package com.francis.bestroute.utils;

import android.app.Activity;
import android.view.View;

public class ViewFinders {
	public static <T extends View> T findView(int id, Activity activity){
		
		return (T) activity.findViewById(id);
	}
}
