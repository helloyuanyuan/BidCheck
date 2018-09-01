package bidWebSiteTest;

import java.util.Calendar;

import org.junit.Test;

import bidWebSite.Crccmall;
import tools.DateUtils;

public class CrccmallTest {

    // @Test
    public void testDateEqualsCurrentDate() throws Exception {
        Crccmall crccmall = new Crccmall();
        crccmall.search(DateUtils.getCurrentDate());
    }

    @Test
    public void testDateBeforeCurrentDate() throws Exception {
        Crccmall crccmall = new Crccmall();
        crccmall.search(DateUtils.getDate(Calendar.DAY_OF_MONTH, -1));
    }

    // @Test
    public void testDateAfterCurrentDate() throws Exception {
        Crccmall crccmall = new Crccmall();
        crccmall.search(DateUtils.getDate(Calendar.DAY_OF_MONTH, 1));
    }
}
