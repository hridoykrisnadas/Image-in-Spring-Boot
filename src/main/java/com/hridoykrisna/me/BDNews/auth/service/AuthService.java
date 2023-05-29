package com.hridoykrisna.me.BDNews.auth.service;


import com.hridoykrisna.me.BDNews.auth.user.AuthenticationDTO;
import com.hridoykrisna.me.BDNews.auth.user.AuthorizationDTO;
import com.hridoykrisna.me.BDNews.auth.user.RegisterDTO;

public interface AuthService {
    AuthorizationDTO register(RegisterDTO request);

    AuthorizationDTO authenticate(AuthenticationDTO request);
}
