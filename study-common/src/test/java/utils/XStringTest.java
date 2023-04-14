package utils;

import com.chain.study.common.utils.XString;
import org.junit.Test;

public class XStringTest {
    @Test
    public void TestMd5() {
        String a = "xxxxxxxxxx";
        System.out.println(XString.md5(a));
    }
}
