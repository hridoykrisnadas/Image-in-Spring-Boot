package com.hridoykrisna.me.BDNews.auth.controller;

import com.hridoykrisna.me.BDNews.auth.service.AuthService;
import com.hridoykrisna.me.BDNews.auth.user.AuthorizationDTO;
import com.hridoykrisna.me.BDNews.auth.user.RegisterDTO;
import com.hridoykrisna.me.BDNews.auth.user.AuthenticationDTO;
import com.hridoykrisna.me.BDNews.util.UrlConstraint;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstraint.AuthManagement.ROOT)
@RequiredArgsConstructor
public class authController {

    private final AuthService authService;

    @PostMapping(UrlConstraint.AuthManagement.REGISTER)
    public ResponseEntity<AuthorizationDTO> register(
            @Valid
            @RequestBody RegisterDTO request,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.ofNullable(AuthorizationDTO.builder().token("").build());
        }
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping(UrlConstraint.AuthManagement.LOGIN)
    public ResponseEntity<AuthorizationDTO> authenticate(
            @RequestBody AuthenticationDTO request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
