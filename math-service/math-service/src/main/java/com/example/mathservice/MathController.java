package com.example.mathservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/sum/{s1}/{s2}")
    public Response sum(@PathVariable int s1, @PathVariable int s2) {
        return new Response(s1, s2, s1 + s2);
    }
}
