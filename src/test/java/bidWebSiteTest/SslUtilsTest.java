package bidWebSiteTest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import tools.SslUtils;

public class SslUtilsTest {

    @Test
    @Tag("test")
    public void testGet() throws Exception {
        String a = SslUtils.getRequest("https://www.crccmall.com/cms/infomation/page/list/1/1", 3000);
        System.out.println(a);
    }

    @Test
    @Tag("test")
    public void testPost() throws Exception {
        String a = SslUtils.postRequest("https://www.crccmall.com/cms/infomation/page/list/1/1", "", 3000);
        System.out.println(a);
    }

}