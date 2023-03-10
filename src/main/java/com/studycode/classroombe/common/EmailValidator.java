package com.studycode.classroombe.common;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;
@Component
public class EmailValidator  implements Predicate<String> {
    public static final Predicate<String> IS_VALID_EMAIL_REG =
            Pattern
            .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE)
            .asPredicate();
    @Override
    public boolean test(String email) {
        return IS_VALID_EMAIL_REG.test(email);
    }
}
