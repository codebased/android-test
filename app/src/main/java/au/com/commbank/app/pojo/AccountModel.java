package au.com.commbank.app.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import au.com.commbank.app.helper.Utils;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "account",
        "transactions",
        "pending",
        "atms"
})
public class AccountModel {

    @JsonProperty("account")
    private Account account;
    @JsonProperty("transactions")
    private List<Transaction> transactions = new ArrayList<>();
    @JsonProperty("pending")
    private List<Pending> pending = new ArrayList<>();
    @JsonProperty("atms")
    private List<Atm> atms = new ArrayList<Atm>();

    /**
     * @return The account
     */
    @JsonProperty("account")
    public Account getAccount() {
        return account;
    }

    /**
     * @param account The account
     */
    @JsonProperty("account")
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return The transactions
     */
    @JsonProperty("transactions")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * @param transactions The transactions
     */
    @JsonProperty("transactions")
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * @return The pending
     */
    @JsonProperty("pending")
    public List<Pending> getPending() {
        return pending;
    }

    /**
     * @param pending The pending
     */
    @JsonProperty("pending")
    public void setPending(List<Pending> pending) {
        this.pending = pending;
    }

    /**
     * @return The atms
     */
    @JsonProperty("atms")
    public List<Atm> getAtms() {
        return atms;
    }

    /**
     * @param atms The atms
     */
    @JsonProperty("atms")
    public void setAtms(List<Atm> atms) {
        this.atms = atms;
    }

    public List<Transaction> getAllTransactions() {

        // @todo - to save memory resources I could have used transactions and attach the rest of pending with.
        // But but but I left this for the future to improve the same.
        List<Transaction> transactions = new ArrayList<>();

        transactions.addAll(getTransactions());
        transactions.addAll(getPending());

        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction sourcetrn, Transaction targettrn) {
                return targettrn.getEffectiveDate().compareTo(sourcetrn.getEffectiveDate());
            }
        });

        return transactions;
    }

    public Atm searchAtmById(String atmId) {

        if (Utils.isEmptyOrNull(atmId)) return null;

        // @todo it would be nice to have Mr. Lembda (lambdaJ Library)
        // here but lets leave it for the future. Its' java turn to copy C# :P
        // otherwise buble sort could be good.
        for (Atm atm : getAtms()) {
            if (atm != null && atm.getId().equalsIgnoreCase(atmId)) {
                return atm;
            }
        }

        return null;
    }
}