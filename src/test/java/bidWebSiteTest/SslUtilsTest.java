package bidWebSiteTest;

import org.junit.Test;

import tools.SslUtils;

public class SslUtilsTest {

    @Test
    public void testGet() throws Exception {
        String a = SslUtils.getRequest("https://www.crccmall.com/cms/infomation/page/list/1/1", 3000);
        System.out.println(a);
    }

    @Test
    public void testPost() throws Exception {
        String a = SslUtils.postRequest("https://www.crccmall.com/cms/infomation/page/list/1/1", "", 3000);
        System.out.println(a);
    }

}