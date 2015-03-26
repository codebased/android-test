package au.com.commbank.app.adapter;

import android.view.View;

import au.com.commbank.app.R;
import au.com.commbank.app.customview.AccountSummaryView;
import au.com.commbank.app.pojo.Account;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by codebased on 25/03/15.
 */
public class AccountSummaryItemViewHolder extends BaseListItemViewHolder<Account> {

    @InjectView(R.id.accountSummaryHeaderView)
    AccountSummaryView mAccountSummaryHeaderView;

    public AccountSummaryItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void onClick(View v) {
    }

    public AccountSummaryView getAccountSummaryHeaderView() {
        return mAccountSummaryHeaderView;
    }
}
