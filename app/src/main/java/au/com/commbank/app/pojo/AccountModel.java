package au.com.commbank.app.pojo;

import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import au.com.commbank.app.MainApplication;
import au.com.commbank.app.Utils;

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

}