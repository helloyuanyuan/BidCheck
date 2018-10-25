package bidWebSiteTest;

import java.util.Calendar;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import bidWebSite.Crccmall;
import tools.DateUtils;

public class CrccmallTest {

    @Test
    @Tag("test")
    public void testDateEqualsCurrentDate() throws Exception {
        Crccmall crccmall = new Crccmall();
        crccmall.search(DateUtils.getCurrentDate());
    }

    @Test
    @Tag("test")
    public void testDateBeforeCurrentDate() throws Exception {
        Crccmall crccmall = new Crccmall();
        crccmall.search(DateUtils.getDate(Calendar.DAY_OF_MONTH, -1));
    }

    @Test
    @Tag("test")
    public void testDateAfterCurrentDate() throws Exception {
        Crccmall crccmall = new Crccmall();
        crccmall.search(DateUtils.getDate(Calendar.DAY_OF_MONTH, 1));
    }
}
