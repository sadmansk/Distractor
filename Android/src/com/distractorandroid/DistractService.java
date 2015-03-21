package com.distractorandroid;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class DistractService extends Service {
	private Handler mHandler = new Handler();
    protected Runnable frameCaller = new Runnable()
	{
		public void run()
		{
			Log.e("myid", "test453service");
			startService(new Intent(getBaseContext(), ChatHeadService.class));
			mHandler.postDelayed(this, 2000);
		}
	};	
	  @Override public void onCreate() {
	    super.onCreate();
	    frameCaller.run();
	  }

	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	  }

		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		}
	}