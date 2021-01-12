package n1k.spring_project.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class CookieService {

	public Cookie createCookie(String name, String value, int time) {
		Cookie cookie = null;
		try {
			cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assert cookie != null;
		cookie.setMaxAge(time);
		return cookie;
	}

	public Cookie createCookie(String name, int value, int time) {
		Cookie cookie = null;
		try {
			cookie = new Cookie(name, URLEncoder.encode(String.valueOf(value), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assert cookie != null;
		cookie.setMaxAge(time);
		return cookie;
	}

	public Cookie createNullCookie(String name) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		return cookie;
	}
}
