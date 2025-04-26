package com.codeWise.codeWise.controller.auth;

import com.codeWise.codeWise.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/token/{username}")
    public String getToken(@PathVariable String username) {
        return jwtUtil.generateToken(username);
    }
}
