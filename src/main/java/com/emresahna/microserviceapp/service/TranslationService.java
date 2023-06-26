package com.emresahna.microserviceapp.service;

import java.util.List;

public interface TranslationService {
    List<String> findByKey(List<String> key, Object... arguments);
}
