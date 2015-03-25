package au.com.commbank.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import au.com.commbank.app.adapter.RecyclerViewBaseAdapter;
import au.com.commbank.app.adapter.RecyclerViewTransactionAdapter;
import au.com.commbank.app.customview.TransactionRow;
import au.com.commbank.app.pojo.Account;
import au.com.commbank.app.pojo.AccountModel;
import au.com.commbank.app.pojo.Atm;
import au.com.commbank.app.pojo.Transaction;
import au.com.commbank.app.services.IAccountService;
import butterknife.ButterKnife;
import butterknife.InjectView;
import android.support.v7.widget.LinearLayoutManager;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends CbaFragment {

    @InjectView(R.id.transactionListView)
    RecyclerView transactionsView;

    @Inject
    IAccountService accountService;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public String getTitle() {
        return "Account details";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // could use injection for object graph too.
        // @todo this line works but how it works - is it because it is not runtime inject - it can make
        // reading difficult.
        //  mainApplication.getObjectGraphInstance().inject(this);
        ((MainApplication) getActivity().getApplication()).getObjectGraphInstance().inject(this);


    }

    // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset) {
        // This method probably sends out a network request and appends new data items to your adapter.
        // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
        // Deserialize API response and then construct new objects to append to the adapter

    }


    @Override
    public void onStart() {
        super.onStart();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        transactionsView.setLayoutManager(llm);
        transactionsView.setHasFixedSize(true);
        AccountModel model = accountService.Get();

        RecyclerViewTransactionAdapter ca = new RecyclerViewTransactionAdapter(model.getAllTransactions());
        transactionsView.setAdapter(ca);

//        if (model != null) {
//            setView(model);
//        } else {
//            Toast.makeText(MainApplication.getInstance(), getString(R.string.datanotfound), Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ;
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

    private void setView(AccountModel model) {
        cleanup();
        setAccountSummary(model.getAccount());

    }

    private void cleanup() {
       // transactionTable.removeAllViews();
    }

    public void setAccountSummary(Account accountHeader) {
    }




    private Atm searchAtm(String searchterm, List<Atm> atms) {
        for (Atm atm : atms) {
            if (atm != null && atm.getId().equalsIgnoreCase(searchterm)) {
                return atm;
            }
        }

        return null;
    }



    private void setTransactionRow(Transaction item, Atm atm) {

        TransactionRow row = new TransactionRow(getActivity().getBaseContext(), item, atm);
        if (atm != null) {
            row.SetOnClickListner(new TransactionRow.OnClickListener() {
                @Override
                public void OnClick(Atm atm) {

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
            });
        }

        //transactionTable.addView(row);
    }

    private void setTransactionHeaderRow(Transaction item) {

//        TransactionHeaderRow row = new TransactionHeaderRow(getActivity().getBaseContext(), item);
//        transactionTable.addView(row);
    }

    /**
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
}
