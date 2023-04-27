package com.kissco.ex.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        // 사용자 정보 가져오기
        Optional<SiteUser> _siteUser = this.userRepository.findByusername(authentication.getName());

        // HttpSession 객체 가져오기
        HttpSession session = request.getSession();

        // 세션에 사용자 정보 저장
        session.setAttribute("cartCount", _siteUser.get().getCartCount());


        // 다음 처리를 위해 부모 클래스의 메서드 호출
        super.onAuthenticationSuccess(request, response, authentication);
    }
}