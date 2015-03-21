package com.distractorandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class ChatHeadService extends Service {

	  private WindowManager windowManager;
	  //private ImageView chatHead;
	  //protected WindowManager.LayoutParams params;

	  @Override public IBinder onBind(Intent intent) {
	    // Not used
	    return null;
	  }
	  private Handler mHandler = new Handler();
	    protected Runnable frameCaller = new Runnable()
		{
			public void run()
			{
				Log.e("myid", "test453service");
				makeNewBubble();
				mHandler.postDelayed(this, 10000);
			}
		};	
	  @Override public void onCreate() {
	    super.onCreate();
	    Log.e("myid", "test123service");
	    windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
	    frameCaller.run();
	  }
	  protected void makeNewBubble()
	  {
		    final ImageView newChatHead = new ImageView(this);
		    newChatHead.setImageResource(R.drawable.ic_launcher);

		    final WindowManager.LayoutParams newParams = new WindowManager.LayoutParams(
		        WindowManager.LayoutParams.WRAP_CONTENT,
		        WindowManager.LayoutParams.WRAP_CONTENT,
		        WindowManager.LayoutParams.TYPE_PHONE,
		        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
		        PixelFormat.TRANSLUCENT);

		    newParams.gravity = Gravity.TOP | Gravity.LEFT;
		    
		    WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE); 
		    Display display = window.getDefaultDisplay();
		    int width = display.getWidth();
		    int height = display.getHeight();
		    
		    newParams.x = 100 + (int) (Math.random()*(width-200));
		    newParams.y = 100 + (int) (Math.random()*(height-200));
		    
		    
		    
		    newChatHead.setOnTouchListener(new View.OnTouchListener() {
		    	  private int initialX;
		    	  private int initialY;
		    	  private float initialTouchX;
		    	  private float initialTouchY;

		    	  @Override public boolean onTouch(View v, MotionEvent event) {
		    	    switch (event.getAction()) {
		    	      case MotionEvent.ACTION_DOWN:
		    	    	  if(clickedThis(newParams, event))
			    	    	{ 
			    	        initialX = newParams.x;
			    	        initialY = newParams.y;
			    	        initialTouchX = event.getRawX();
			    	        initialTouchY = event.getRawY();
			    	    	}
		    	        return false;
		    	      case MotionEvent.ACTION_UP:
		    	        return false;
		    	      case MotionEvent.ACTION_MOVE:
		    	    	if(clickedThis(newParams, event))
		    	    	{
		    	    		newParams.x = initialX + (int) (event.getRawX() - initialTouchX);
		    	    		newParams.y = initialY + (int) (event.getRawY() - initialTouchY);
			    	        windowManager.updateViewLayout(newChatHead, newParams);
		    	    	}
		    	        return false;
		    	    }
		    	    return false;
		    	  }
		    	  protected boolean clickedThis(WindowManager.LayoutParams params, MotionEvent event)
		    	  {
		    		  int xDif =  params.x - initialX + (int) (event.getRawX() - initialTouchX);
		    		  int yDif =  params.y - initialY + (int) (event.getRawY() - initialTouchY);
		    		  return true;//return Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2)) < 100;
		    	  }
		    });
		    windowManager.addView(newChatHead, newParams);
	  }
	  

	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	    //if (chatHead != null) windowManager.removeView(chatHead);
	  }
	}