package io.github.originalalex.twitter.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

    public static String extractCookie(String cookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName)) {
                return c.getValue();
            }
        }
        return null;
    }

}
