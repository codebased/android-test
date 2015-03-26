package au.com.commbank.app.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import au.com.commbank.app.CbaFragment;
import au.com.commbank.app.MainApplication;
import au.com.commbank.app.R;
import au.com.commbank.app.adapter.OnListItemListener;
import au.com.commbank.app.adapter.RecyclerHeaderViewTransactionAdapter;
import au.com.commbank.app.customview.TransactionRow;
import au.com.commbank.app.helper.Utils;
import au.com.commbank.app.pojo.AccountModel;
import au.com.commbank.app.pojo.Atm;
import au.com.commbank.app.services.IAccountService;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainFragment extends CbaFragment {

    @InjectView(R.id.transactionListView)
    RecyclerView transactionsViewContainer;

    @Inject
    IAccountService accountService;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        // @todo follow standard pattern in next release.
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public String getTitle() {
        return getString(R.string.title_mainfragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.getObjectGraphInstance().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        AccountModel model = accountService.Get();
        if (model == null) {
            Toast.makeText(MainApplication.getInstance(), getString(R.string.datanotfound), Toast.LENGTH_LONG).show();
        } else {
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            transactionsViewContainer.setLayoutManager(llm);
            transactionsViewContainer.setHasFixedSize(true);

            RecyclerHeaderViewTransactionAdapter ca = new RecyclerHeaderViewTransactionAdapter(model.getAllTransactions());
            ca.setAccountModel(model);
            ca.setListener(new OnListItemListener<TransactionRow>() {
                @Override
                public void onClick(TransactionRow row) {
                    if (row.getAtm() != null) {
                        openMap(row.getAtm());
                    }
                }
            });

            transactionsViewContainer.setAdapter(ca);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * @todo
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void openMap(Atm atm) {
        if (Utils.isNetworkAvailable(getActivity())) {
            if (Utils.isGooglePlayServicesAvailable(getActivity())) {
                MapFragment fragment = new MapFragment();
                Bundle args = new Bundle();
                args.putDouble("lat", atm.getLocation().getLat());
                args.putDouble("lng", atm.getLocation().getLng());
                args.putString("atmTitle", atm.getName());

                fragment.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment).addToBackStack("map").commit();
            }
        } else {
            Toast.makeText(getActivity(), "To view Map, you must be connected with the Internet.", Toast.LENGTH_LONG).show();
        }
    }
}
