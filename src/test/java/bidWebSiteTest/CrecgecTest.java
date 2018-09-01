package bidWebSiteTest;

import java.util.Calendar;

import org.junit.Test;

import bidWebSite.Crecgec;
import tools.DateUtils;

public class CrecgecTest {

    @Test
    public void testDateEqualsCurrentDate() throws Exception {
        Crecgec crecgec = new Crecgec();
        crecgec.search(DateUtils.getCurrentDate());
    }

    // @Test
    public void testDateBeforeCurrentDate() throws Exception {
        Crecgec crecgec = new Crecgec();
        crecgec.search(DateUtils.getDate(Calendar.DAY_OF_MONTH, -1));
    }

    // @Test
    public void testDateAfterCurrentDate() throws Exception {
        Crecgec crecgec = new Crecgec();
        crecgec.search(DateUtils.getDate(Calendar.DAY_OF_MONTH, 1));
    }
}
