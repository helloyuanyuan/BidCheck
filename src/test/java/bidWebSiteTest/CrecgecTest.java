package bidWebSiteTest;

import java.util.Calendar;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import bidWebSite.Crecgec;
import tools.DateUtils;

public class CrecgecTest {

    @Test
    @Tag("daily")
    public void testDateEqualsCurrentDate() throws Exception {
        Crecgec crecgec = new Crecgec();
        crecgec.search(DateUtils.getCurrentDate());
    }

    @Test
    @Tag("test")
    public void testDateBeforeCurrentDate() throws Exception {
        Crecgec crecgec = new Crecgec();
        crecgec.search(DateUtils.getDate(Calendar.DAY_OF_MONTH, -1));
    }

    @Test
    @Tag("test")
    public void testDateAfterCurrentDate() throws Exception {
        Crecgec crecgec = new Crecgec();
        crecgec.search(DateUtils.getDate(Calendar.DAY_OF_MONTH, 1));
    }
}
