package com.distractorandroid;

import java.util.ArrayList;

import com.gc.materialdesign.views.ButtonFloatSmall;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;


public class MainActivity extends Activity
implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private ArrayList<String> reddits = new ArrayList<String>(); // preset, rule, [start/end][day, hour, min]
	private ArrayList<String> youtubes = new ArrayList<String>(); // program person action number
	private ButtonFloatSmall editYoutube;
	private ButtonFloatSmall editReddit;
	private TableLayout redditTable;
	private TableLayout youtubeTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, ChatHeadService.class));
        mNavigationDrawerFragment = (NavigationDrawerFragment)
        getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
        mNavigationDrawerFragment.setActivity(this);
        reddits.add("r/bennythebullgifs/");
        reddits.add("r/funny/");
        reddits.add("r/AdviceAnimals/");
        reddits.add("r/pics/");
        reddits.add("r/todayilearned/");
        reddits.add("r/aww/");
        reddits.add("r/WTF/");
        reddits.add("r/BlackPeopleTwitter/");
        reddits.add("r/gifs/");
        reddits.add("r/leagueoflegends/");
        reddits.add("r/videos/");
        reddits.add("r/gaming/");
        youtubes.add("pRpvdcjkT3k");
        youtubes.add("Te4wx4jtiEA");
        youtubes.add("efTj6UYzvk4");
        youtubes.add("Qit3ALTelOo");
        youtubes.add("0Bmhjf0rKe8");
        youtubes.add("z3U0udLH974");
        youtubes.add("Vw4KVoEVcr0");
        youtubes.add("_ZSbC09qgLI");
        youtubes.add("_ZSbC09qgLI");
        youtubes.add("J---aiyznGQ");
        youtubes.add("C_S5cXbXe-4");
        youtubes.add("X3iFhLdWjqc");
        youtubes.add("hPzNl6NKAG0");
        youtubes.add("TJ5UJv9A4jE");
        youtubes.add("eV71mpbvl-g");
        youtubes.add("gCH68g_R5Iw");
        youtubes.add("S5-D0f6nHSQ");
        youtubes.add("QT2NOQ0P4t8");
        youtubes.add("598IdFlOXcQ");
        youtubes.add("HECa3bAFAYk");
        youtubes.add("tcxhOGyrCtI");
    }

    @
    Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.mainLayout, PlaceholderFragment.newInstance(position + 1))
            .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }
    protected static String BROADCAST_ACTION = "com.distractorandroid.SHOWTOAST";
    protected void sendBroadcast(String action, String information)
    {
    	Intent broadcast = new Intent();
        broadcast.setAction(BROADCAST_ACTION);
        broadcast.putExtra("ACTION", action);
        broadcast.putExtra("INFORMATION", information);
        Log.e("myid", "testingSend");
        sendBroadcast(broadcast);
    }
    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @
    Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {}

        @
        Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @
        Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}