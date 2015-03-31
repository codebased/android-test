package au.com.commbank.app.services;

import java.io.IOException;
import java.io.InputStream;

import au.com.commbank.app.MainApplication;
import au.com.commbank.app.pojo.AccountModel;


public class AccountService implements IAccountService {

    public AccountService() {
    }

    public AccountModel Get() {
        try {

            MainApplication application = MainApplication.getInstance();
             // @todo instead of static here, I could use Dagger for that. Anyways leave it for this version.
            InputStream stream = application.getApplicationContext().getAssets()
                    .open("exercise.json");

            AccountModel accountModel = MainApplication.getObjectMapperInstance().readValue(stream, AccountModel.class);
            return accountModel;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
