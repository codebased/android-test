NOTES:


Dependency Injection (Dagger) & View Injection (Butterknife)

Reusable Custom Adapter, using RecyclerView, with Header View

Reusable Custom Components for Transactions List UI

Google Map

Separation of Concerns

Json parser using Jackson Library.

Centralized styling.

Check with exception such as google service not available for Map , Internet availability etc.. 

Test Automation (could be further improved by using mock frameworks - Mockito or android Mock or even UI test automation throgh robotium).



Point of Improvements:

1. There are definitely many ways to implement this solution.

I have used RecycleView with Header Custom Adapter that can be further improved by pagination. By doing this, 
we can reduce the memory cost.

2. Currently I am building a list collection that contains pending as well as completed transactions. 
On rendering the decision has been made for grouping by date. I think that is cool over using a Comparable class.


class GroupedTransaction  implements
        Comparable<GroupedTransaction> {

    private List<Transaction> transactions;
    private Date date;

    GroupedTransaction  (Date t) {
        transactions = new ArrayList<Transaction>();
        date = t;
    }

    @Override
    public int compareTo(GroupedTransaction   another) {
        return another.date.compareTo(date);
    }

    public void insert(Transaction t) {
        //...
    }

    public Date getDate() {
        /....
    }

    public List<Transaction> getTransactionList() {
        return transactions;
    }
}

Then at AccountService.Get(..) you could make a group collection. But then again, many ways to do the same :))

3.View Hierarchy improvements 


http://developer.android.com/tools/debugging/debugging-ui.html
