package com.hg.securitylearn.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hougen
 */
@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('hello')")
    public String hello() {
        return "hello,world";
    }

    @GetMapping("/world")
    @PreAuthorize("hasAuthority('world')")
    public String world() {
        return "hello,world";
    }
}
