package bidWebSiteTest;

import java.util.Calendar;

import org.junit.Test;

import bidWebSite.Crecgec;
import tools.DateTool;

public class CrecgecTest {

    DateTool dateTool = new DateTool();

    @Test
    public void testDateEqualsCurrentDate() throws Exception {
        Crecgec crecgec = new Crecgec();
        crecgec.search(dateTool.getCurrentDate());
    }

    // @Test
    public void testDateBeforeCurrentDate() throws Exception {
        Crecgec crecgec = new Crecgec();
        crecgec.search(dateTool.getDate(Calendar.DAY_OF_MONTH, -1));
    }

    // @Test
    public void testDateAfterCurrentDate() throws Exception {
        Crecgec crecgec = new Crecgec();
        crecgec.search(dateTool.getDate(Calendar.DAY_OF_MONTH, 1));
    }
}
