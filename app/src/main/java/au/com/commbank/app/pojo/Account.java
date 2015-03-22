
package au.com.commbank.app.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "accountName",
        "accountNumber",
        "available",
        "balance"
})
public class Account {

    @JsonProperty("accountName")
    private String accountName;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("available")
    private Double available;
    @JsonProperty("balance")
    private Double balance;

    /**
     *
     * @return
     * The accountName
     */
    @JsonProperty("accountName")
    public String getAccountName() {
        return accountName;
    }

    /**
     *
     * @param accountName
     * The accountName
     */
    @JsonProperty("accountName")
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     *
     * @return
     * The accountNumber
     */
    @JsonProperty("accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     *
     * @param accountNumber
     * The accountNumber
     */
    @JsonProperty("accountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     *
     * @return
     * The available
     */
    @JsonProperty("available")
    public Double getAvailable() {
        return available;
    }

    /**
     *
     * @param available
     * The available
     */
    @JsonProperty("available")
    public void setAvailable(Double available) {
        this.available = available;
    }

    /**
     *
     * @return
     * The balance
     */
    @JsonProperty("balance")
    public Double getBalance() {
        return balance;
    }

    /**
     *
     * @param balance
     * The balance
     */
    @JsonProperty("balance")
    public void setBalance(Double balance) {
        this.balance = balance;
    }
}