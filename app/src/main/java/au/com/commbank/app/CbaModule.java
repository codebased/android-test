package au.com.commbank.app;

import javax.inject.Singleton;

import au.com.commbank.app.fragment.MainFragment;
import au.com.commbank.app.services.AccountService;
import au.com.commbank.app.services.IAccountService;
import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                MainFragment.class,
        }
)
public class CbaModule {

    @Provides @Singleton
    public IAccountService provideAccountService() {
        return new AccountService();
    }
}
