package au.com.commbank.app.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TextView;

import au.com.commbank.app.R;
import au.com.commbank.app.Utils;
import au.com.commbank.app.pojo.Account;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class AccountSummaryView extends TableLayout {

    @InjectView(R.id.accountName)
    TextView accountName;

    @InjectView(R.id.accountNumber)
    TextView accountNumber;

    @InjectView(R.id.availableBalanceTV)
    TextView availableBalanceTV;

    @InjectView(R.id.availableFundsTV)
    TextView availableFundsTV;


    public AccountSummaryView(Context context) {
        super(context);
        init();
    }

    public AccountSummaryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.account_summary_header, this);
        ButterKnife.inject(this);
    }

    public void setAccountHeader(Account account) {
        if (account != null) {
            accountName.setText(account.getAccountName());
            accountNumber.setText(account.getAccountNumber());
            availableBalanceTV.setText(Utils.formatCurrency(account.getBalance()));
            availableFundsTV.setText(Utils.formatCurrency(account.getAvailable()));
        }
    }
}