package com.example.scheduleApp.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;


@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/user/signup" , "/login"};

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request; // 다운 캐스팅
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response; // 다운 캐스팅

        log.info("로그인 필터 로직 실행");

        if (!isWhiteList(requestURI)) { // 화이트리스트에 없음.
            HttpSession session = httpRequest.getSession(false);

            if(session == null || session.getAttribute("user") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }

            log.info("로그인에 성공했습니다.");
        }
        chain.doFilter(request, response);

    }

    private boolean isWhiteList(String requestURI) { // 요청 URL가 whiteList에 있는지 확인하는 메서드
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}
