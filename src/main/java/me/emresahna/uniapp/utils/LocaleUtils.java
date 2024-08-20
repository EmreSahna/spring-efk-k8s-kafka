package me.emresahna.uniapp.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Locale;

public class LocaleUtils {
    public static Locale getLocaleFromRequest(WebRequest request) {
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        String acceptLanguage = servletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE);

        return Locale.lookup(Locale.LanguageRange.parse(acceptLanguage), List.of(Locale.getAvailableLocales()));
    }
}
