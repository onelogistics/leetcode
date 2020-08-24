package system.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * LeetCode 535：Encode and Decode TinyURL
 *
 *TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 * 解法1：用map存储
 * 解法2：全局发号器，可以用redis实现，
 */
public class Codec {
    private Map<String,String> longToShort = new HashMap<>();
    private Map<String,String> shortToLong = new HashMap<>();
    private String baseUrl="http://tinyurl.com/";
    private String charset="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private Random random=new Random();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(longToShort.containsKey(longUrl)) {
            return baseUrl+longToShort.get(longUrl);
        }
        String shortUrl=null;
        do {
            shortUrl=randomShortUrl();
        }while (shortToLong.containsKey(shortUrl));
        longToShort.put(longUrl,shortUrl);
        shortToLong.put(shortUrl,longUrl);
        return baseUrl+shortUrl;
    }
    private String randomShortUrl() {
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<6;i++) {
            sb.append(charset.charAt(random.nextInt(charset.length()));
        }
        return sb.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortToLong.get(shortUrl.replace(baseUrl,""));
    }
}
