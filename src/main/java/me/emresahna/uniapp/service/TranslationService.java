package me.emresahna.uniapp.service;

import java.util.List;
import java.util.Locale;

public interface TranslationService {
    List<String> findByKey(List<String> key, Locale locale, Object... arguments);
}
