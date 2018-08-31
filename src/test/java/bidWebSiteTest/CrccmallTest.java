package bidWebSiteTest;

import java.util.Calendar;

import org.junit.Test;

import bidWebSite.Crccmall;
import tools.DateTool;

public class CrccmallTest {

    DateTool dateTool = new DateTool();

    @Test
    public void testDateEqualsCurrentDate() throws Exception {
        Crccmall crccmall = new Crccmall();
        crccmall.search(dateTool.getCurrentDate());
    }

    // @Test
    public void testDateBeforeCurrentDate() throws Exception {
        Crccmall crccmall = new Crccmall();
        crccmall.search(dateTool.getDate(Calendar.DAY_OF_MONTH, -1));
    }

    // @Test
    public void testDateAfterCurrentDate() throws Exception {
        Crccmall crccmall = new Crccmall();
        crccmall.search(dateTool.getDate(Calendar.DAY_OF_MONTH, 1));
    }
}
