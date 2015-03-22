package com.distractorandroid;

import java.util.ArrayList;
import java.util.Random;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class ChatHeadService extends Service {

    private WindowManager windowManager;
    private Random random = new Random();
    private ArrayList < ImageView > chatHeads = new ArrayList < ImageView > ();
    ArrayList<String> subReddits = new ArrayList<String>();
    ArrayList<String> subYoutubes = new ArrayList<String>();
    protected ArrayList < WindowManager.LayoutParams > params = new ArrayList < WindowManager.LayoutParams > ();
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
    	@Override
        public void onReceive(Context context, Intent intent) {
    		Bundle bundle = intent.getExtras();
    		String action = (String) bundle.get("ACTION");
    		String info = (String) bundle.get("INFORMATION");
    		if(action.equals("justStop"))
    		{
    			killBubbles();
	            Toast.makeText(getApplicationContext(), "Just one more...", Toast.LENGTH_LONG).show();
	            makeNewBubble();
	            Toast.makeText(getApplicationContext(), "Okay I'm done, for now", Toast.LENGTH_LONG).show();
	            noMoreBubbles = 5;
    		} else if(action.equals("addReddit"))
    		{
    			subReddits.add(info);
    		} else if(action.equals("removeReddit"))
    		{
    			subReddits.remove(info);
    		} else if(action.equals("addYoutube"))
     		{
    			subYoutubes.add(info);
     		} else if(action.equals("removeYoutube"))
     		{
     			subYoutubes.remove(info);
     		}
    	}
    };
    protected String[] rQuotes = {"Hey are you still working", 
    		"Did you see that new thing on Reddit",
    		"Yo check it out"
    		};
    protected String[] yQuotes = {"Hey are you still working", 
    		"You seriously need to check out this vid",
    		"You work too hard sometimes",
    		"Take a break man"
    		};
    private int noMoreBubbles;
    @Override
    public IBinder onBind(Intent intent) {
        // Not used
        return null;
    }
    private Handler mHandler = new Handler();
    protected Runnable frameCaller = new Runnable() {
        public void run() {
        	if(noMoreBubbles > 0)
        	{
        		noMoreBubbles --;
        	} else
        	{
        		makeNewBubble();
        	}
            mHandler.postDelayed(this, (int)(Math.random() * 5000));
        }
    };@
    Override public void onCreate() {
        super.onCreate();
        Log.e("myid", "test123service");
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        frameCaller.run();
        
        
        subReddits.add("r/bennythebullgifs/");
        subReddits.add("r/funny/");
        subReddits.add("r/AdviceAnimals/");
        subReddits.add("r/pics/");
        subReddits.add("r/todayilearned/");
        subReddits.add("r/aww/");
        subReddits.add("r/WTF/");
        subReddits.add("r/BlackPeopleTwitter/");
        subReddits.add("r/gifs/");
        subReddits.add("r/leagueoflegends/");
        subReddits.add("r/videos/");
        subReddits.add("r/gaming/");
        subYoutubes.add("pRpvdcjkT3k");
        subYoutubes.add("Te4wx4jtiEA");
        subYoutubes.add("efTj6UYzvk4");
        subYoutubes.add("Qit3ALTelOo");
        subYoutubes.add("0Bmhjf0rKe8");
        subYoutubes.add("z3U0udLH974");
        subYoutubes.add("Vw4KVoEVcr0");
        subYoutubes.add("_ZSbC09qgLI");
        subYoutubes.add("_ZSbC09qgLI");
        subYoutubes.add("J---aiyznGQ");
        subYoutubes.add("C_S5cXbXe-4");
        subYoutubes.add("X3iFhLdWjqc");
        subYoutubes.add("hPzNl6NKAG0");
        subYoutubes.add("TJ5UJv9A4jE");
        subYoutubes.add("eV71mpbvl-g");
        subYoutubes.add("gCH68g_R5Iw");
        subYoutubes.add("S5-D0f6nHSQ");
        subYoutubes.add("QT2NOQ0P4t8");
        subYoutubes.add("598IdFlOXcQ");
        subYoutubes.add("HECa3bAFAYk");
        subYoutubes.add("tcxhOGyrCtI");
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainActivity.BROADCAST_ACTION);
        registerReceiver(receiver, filter);
    }
    protected void makeNewBubble() {
        final ImageView newChatHead = new ImageView(this);
        final boolean isYoutube = Math.random() > 0.5;
        if(isYoutube)
        {
        	if(Math.random() > 0.7)
        	{
        		Toast.makeText(getApplicationContext(), yQuotes[random.nextInt(yQuotes.length)], Toast.LENGTH_LONG).show();
        	}
        	newChatHead.setImageResource(R.drawable.youtube);
        } else
        {
        	if(Math.random() > 0.7)
        	{
        		Toast.makeText(getApplicationContext(), rQuotes[random.nextInt(rQuotes.length)], Toast.LENGTH_LONG).show();
        	}
        	newChatHead.setImageResource(R.drawable.reddit);
        }

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

        newParams.x = 100 + (int)(Math.random() * (width - 200));
        newParams.y = 100 + (int)(Math.random() * (height - 200));



        newChatHead.setOnTouchListener(new View.OnTouchListener() {
        	private boolean isYoutubeBubble = isYoutube;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            private long time;

            @
            Override public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (clickedThis(newParams, event)) {
                            initialX = newParams.x;
                            initialY = newParams.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            time = System.currentTimeMillis();
                        }
                        return false;
                    case MotionEvent.ACTION_UP:
                    	double xDif = event.getRawX() - initialTouchX;
                        double yDif = event.getRawY() - initialTouchY;
                        double distance = Math.pow(xDif, 2) + Math.pow(yDif, 2);
                        double timeDif = Math.abs(time - System.currentTimeMillis());
                        if(timeDif < 100 && distance < 200)
                        {
                        	popBubble(newChatHead, isYoutube);
                        }
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        if (clickedThis(newParams, event)) {
                            newParams.x = initialX + (int)(event.getRawX() - initialTouchX);
                            newParams.y = initialY + (int)(event.getRawY() - initialTouchY);
                            windowManager.updateViewLayout(newChatHead, newParams);
                        }
                        return false;
                }
                return false;
            }
            protected boolean clickedThis(WindowManager.LayoutParams params, MotionEvent event) {
                int xDif = params.x - initialX + (int)(event.getRawX() - initialTouchX);
                int yDif = params.y - initialY + (int)(event.getRawY() - initialTouchY);
                return true; //return Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2)) < 100;
            }
        });
        windowManager.addView(newChatHead, newParams);
        chatHeads.add(newChatHead);
        params.add(newParams);
    }
    protected void popBubble(ImageView bubbleToPop, boolean isYoutube)
    {
    	windowManager.removeView(bubbleToPop);
    	bubbleToPop.setOnTouchListener(null);
    	bubbleToPop = null;
    	String subName;
    	if(isYoutube)
    	{
    		subName = "https://m.youtube.com/";
    		if(subYoutubes.size() != 0)
        	{
    			subName = subName.concat("watch?v=");
    	    	int randomInt = random.nextInt(subYoutubes.size());
    	    	subName = subName.concat(subYoutubes.get(randomInt));
    	    	Log.e("myid", "testong");
        	}
    	} else
    	{
    		subName = "http://www.reddit.com/";
    		if(subReddits.size() != 0)
    	    {
    	   	    int randomInt = random.nextInt(subReddits.size());
    	   	    subName = subName.concat(subReddits.get(randomInt));
    	   	    Log.e("myid", "testonjhgg");
    	    }
    		subName = subName.concat(".compact?modestbranding=1&amp;fs=0&amp;rel=0&amp;hd=1&amp;disablekb=0&amp;egm=1&amp;showsearch=0&amp;showinfo=0&amp;iv_load_policy=0&amp;autoplay=1&amp;loop=0&amp;version=3");
    	}
    	Log.e("myid", subName);
    	getApplication().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(subName)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
    protected void killBubbles() {
        for (int i = 0; i < chatHeads.size(); i++) {
        	if(chatHeads.get(i) != null)
        	{
        		windowManager.removeView(chatHeads.get(i));
        	}
        }
        chatHeads.clear();
        params.clear();
    }@
    Override
    public void onDestroy() {
        super.onDestroy();
        killBubbles();
        unregisterReceiver(receiver);
    }
}