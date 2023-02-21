package com.studycode.classroombe.common;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class EmailValidatorTest {
    private final EmailValidator underTest = new EmailValidator();
    @Test
    public void itShouldValidateCorrectEmail(){
        assertThat(underTest.test("hello@gmail.com")).isTrue();
    }
    @Test
    public void itShouldValidateIncorrectEmail(){
        assertThat(underTest.test("gmail.com")).isFalse();
    }
    @Test
    public void itShouldValidateIncorrectEmailWithoutDot(){
        assertThat(underTest.test("hello@gmail")).isFalse();
    }
}