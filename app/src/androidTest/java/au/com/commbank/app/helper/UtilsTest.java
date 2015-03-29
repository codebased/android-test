package au.com.commbank.app.helper;

import junit.framework.Assert;
import junit.framework.TestCase;

public class UtilsTest extends TestCase {


    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testDoubleToCurrency_Validvalue_ShouldReturnValue() {

        //act
        String result = Utils.formatCurrency(23.32);

        // assert
        Assert.assertTrue(result.startsWith("$"));
    }

    public void testDoubleToCurrency_NullValue_ShouldReturnNull() {

        //act
        String result = Utils.formatCurrency(null);

        // assert
        Assert.assertNull(result);
    }

    // @todo you can the rest ....
}