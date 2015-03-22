package au.com.commbank.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by codebased on 21/03/15.
 */
public abstract class CbaFragment extends Fragment {


    public CbaFragment() {
    }

    public abstract String getTitle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getTitle());
    }
}
