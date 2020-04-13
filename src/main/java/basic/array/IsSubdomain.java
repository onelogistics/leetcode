package basic.array;

import org.junit.Assert;

/**
 * 对于给定的域名A和B，判断A是否为B的子域名。
 * 输入 "123.qq.com", "qq.com" 输出：yes
 *
 * 子域名（或子域；英语：Subdomain）是在域名系统等级中，
 * 属于更高一层域的域。
 * 比如，mail.example.com和calendar.example.com是example.com的两个子域，而example.com则是顶级域.com的子域。
 */
public class IsSubdomain {
    @org.junit.Test
    public void test() {
        Assert.assertTrue(isSubdomain("123.qq.com","qq.com"));
        Assert.assertFalse(isSubdomain("123.qq.com","3.qq.com"));
        Assert.assertTrue(isSubdomain("mail.example.com","example.com"));
        Assert.assertTrue(isSubdomain("calendar.example.com","example.com"));
        Assert.assertTrue(isSubdomain("example.com",".com"));
    }
    public boolean isSubdomain(String subdomain, String domain) {
        if(subdomain.length()<=domain.length()) {
            return false;
        }
        int i,j;
        for(i=subdomain.length()-1,j=domain.length()-1; i>=0 && j>=0 ; i--,j--) {
            if(subdomain.charAt(i)!=domain.charAt(j)) {
                return false;
            }
        }
        //排除123.qq.com 3.qq.com这种case(此时，j=-1，i为对应位置的下标)
        if(subdomain.charAt(i)!='.' && domain.charAt(0)!='.') {
            return false;
        }
        return true;
    }
}
