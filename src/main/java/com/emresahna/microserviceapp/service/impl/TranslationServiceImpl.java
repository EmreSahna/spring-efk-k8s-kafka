package com.emresahna.microserviceapp.service.impl;

import com.emresahna.microserviceapp.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {
    private final MessageSource messageSource;

    @Override
    public List<String> findByKey(List<String> key, Object... arguments) {
        try {
            return key
                    .stream()
                    .map(k -> messageSource.getMessage(k, arguments, null))
                    .collect(Collectors.toList());
        } catch (NoSuchMessageException exception) {
            return Collections.emptyList();
        }
    }
}
