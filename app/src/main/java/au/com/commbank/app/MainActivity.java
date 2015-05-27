package au.com.commbank.app;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.log4j.Logger;

import au.com.commbank.app.fragment.MainFragment;
import au.com.commbank.app.helper.ConfigureLog4J;
import au.com.commbank.app.helper.Utils;


public class MainActivity extends ActionBarActivity implements MainFragment.OnFragmentInteractionListener {

    public static final String TITLE = "Account details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConfigureLog4J.configure();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.icon_welcome_logo);
        actionBar.setTitle(TITLE);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit();
        }

        Utils.Log(MainActivity.class).info("Main application has started");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}