package au.com.commbank.app.services;

import junit.framework.Assert;
import junit.framework.TestCase;

import au.com.commbank.app.pojo.AccountModel;
import au.com.commbank.app.pojo.Atm;

public class AccountServiceTest extends TestCase {

    IAccountService sut = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // @todo could use mock framework - Mockito or Android Mock
        // any way we are reading it from local json so I will leave this for now.
        sut = new AccountService();
    }

    public void testGet_NotNullResult() {
        // @todo could use mock
        AccountModel model = sut.Get();
        Assert.assertNotNull(model);
    }

    public void testGet_SearchAtm_AtmObject() {

        // arrange
        AccountModel model = sut.Get();

        // act
        Atm atm = model.searchAtmById("129382");

        // assert
        Assert.assertNotNull(atm);
        Assert.assertTrue(atm.getId().equalsIgnoreCase("129382"));

    }

    public void testGet_SearchInvalidAtm_NullResult() {

        // arrange
        AccountModel model = sut.Get();

        // act
        Atm atm = model.searchAtmById("INVALID");

        // assert
        Assert.assertNull(atm);

    }
}