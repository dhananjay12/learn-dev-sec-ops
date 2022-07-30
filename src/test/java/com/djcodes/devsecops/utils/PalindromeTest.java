package com.djcodes.devsecops.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromeTest {
    @Test
    public void whenEmptyString_thanAccept() {
        Palindrome palindromeTester = new Palindrome();
        assertTrue(palindromeTester.isPalindrome(""));
    }

    @Test
    public void whenPalindrom_thanAccept() {
        Palindrome palindromeTester = new Palindrome();
        assertTrue(palindromeTester.isPalindrome("noon"));
    }

    @Test
    public void whenNotPalindrom_thanReject(){
        Palindrome palindromeTester = new Palindrome();
        assertFalse(palindromeTester.isPalindrome("box"));
    }

    @Test
    public void whenNearPalindrom_thanReject(){
        Palindrome palindromeTester = new Palindrome();
        assertFalse(palindromeTester.isPalindrome("neon"));
    }
}