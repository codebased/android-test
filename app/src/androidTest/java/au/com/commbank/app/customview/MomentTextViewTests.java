package au.com.commbank.app.customview;

import android.widget.TextView;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Date;

import au.com.commbank.app.MainApplication;

/**
 * Created by codebased on 30/03/15.
 */
public class MomentTextViewTests extends TestCase {

    MomentTextView sut = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        sut = new MomentTextView(MainApplication.getInstance());
    }

    public void testSetText_WithDate_ShouldHaveDaysAgoText() {
        // act
        sut.setText(new Date(234234), TextView.BufferType.NORMAL);

        Assert.assertTrue(sut.getText().toString().indexOf("days ago") != -1);
    }
}