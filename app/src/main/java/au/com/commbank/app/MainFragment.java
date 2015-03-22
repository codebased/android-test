package au.com.commbank.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import au.com.commbank.app.customview.AccountSummaryView;
import au.com.commbank.app.customview.TransactionHeaderRow;
import au.com.commbank.app.customview.TransactionRow;
import au.com.commbank.app.pojo.Account;
import au.com.commbank.app.pojo.AccountModel;
import au.com.commbank.app.pojo.Atm;
import au.com.commbank.app.pojo.Transaction;
import au.com.commbank.app.services.AccountService;
import au.com.commbank.app.services.IAccountService;
import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends CbaFragment {

    @InjectView(R.id.accountSummaryHeader)
    AccountSummaryView accountSummaryView;

    @InjectView(R.id.transactionTable)
    TableLayout transactionTable;

    @Inject
    IAccountService accountService;

    private OnFragmentInteractionListener mListener;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public String getTitle() {
        return "Account details";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // I could reduce this easily by using injection for object graph too but lets don't do it fo rnow.

        ((MainApplication) getActivity().getApplication()).getObjectGraphInstance().inject(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        AccountModel model = accountService.Get();
        setView(model);
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
        setAccountSummary(model.getAccount());
        setAccountTransactions(model);
    }

    public void setAccountSummary(Account accountHeader) {
        accountSummaryView.setAccountHeader(accountHeader);
    }

    private void setAccountTransactions(AccountModel model) {

        String lastDate = Constants.EMPTY_STRING;

        List<Transaction> transactions = new ArrayList<>();

        transactions.addAll(model.getTransactions());
        transactions.addAll(model.getPending());

        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction sourcetrn, Transaction targettrn) {
                try {
                    return Utils.CompareDates(targettrn.getEffectiveDate(), sourcetrn.getEffectiveDate());
                } catch (ParseException e) {
                    Toast.makeText(MainApplication.getInstance(), e.toString(), Toast.LENGTH_LONG).show();
                    return 0;
                }
            }
        });

        for (Iterator<Transaction> i = transactions.iterator(); i.hasNext(); ) {
            Transaction item = i.next();

            if (!lastDate.equalsIgnoreCase(item.getEffectiveDate())) {
                // if they are not same dates then definitely create a header.
                setTransactionHeaderRow(item);
            }

            Atm atm = null;
            if (!Utils.isEmptyOrNull(item.getAtmId())) {
                for (Atm a : model.getAtms()) {
                    if (a != null && a.getId().equalsIgnoreCase(item.getAtmId())) {
                        atm = a;
                        break;
                    }
                }
            }

            setTransactionRow(item,atm);
            lastDate = item.getEffectiveDate();
        }
    }

    private void setTransactionRow(Transaction item,Atm atm) {

        TransactionRow row = new TransactionRow(getActivity().getBaseContext(), item, atm);
        if ( atm != null ){
            row.SetOnClickListner(new TransactionRow.OnClickListener() {
                @Override
                public void OnClick(Atm atm) {
                    MapFragment fragment = new MapFragment();
                    Bundle args = new Bundle();
                    args.putDouble("lat", atm.getLocation().getLat());
                    args.putDouble("lng", atm.getLocation().getLng());
                    args.putString("atmTitle", atm.getName());

                    fragment.setArguments(args);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment ).addToBackStack("map").commit();
                }
            });
        }
        transactionTable.addView(row);
    }

    private void setTransactionHeaderRow(Transaction item) {

        TransactionHeaderRow row = new TransactionHeaderRow(getActivity().getBaseContext(), item);
        transactionTable.addView(row);
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
