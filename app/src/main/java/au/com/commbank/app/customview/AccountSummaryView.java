package au.com.commbank.app.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TextView;

import au.com.commbank.app.R;
import au.com.commbank.app.helper.Utils;
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
        this.init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.account_summary_header, this);
        ButterKnife.inject(this);
    }

    /* @todo can be further improve by providing 4 string properties to this custom control
     and then we are good to support xml based. Lets don't do that for this release.
     */
    public void applyBinding(Account account) {

        if (account != null) {
            this.accountName.setText(account.getAccountName());
            this.accountNumber.setText(account.getAccountNumber());
            this.availableBalanceTV.setText(Utils.formatCurrency(account.getBalance()));
            this.availableFundsTV.setText(Utils.formatCurrency(account.getAvailable()));
        }
    }
}