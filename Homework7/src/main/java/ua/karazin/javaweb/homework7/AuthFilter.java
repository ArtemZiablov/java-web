package ua.karazin.javaweb.homework7;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter("/secured/*")  // Фільтр спрацьовує для всіх URL, що починаються з /secured/
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Ініціалізація фільтра (якщо потрібно)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // Перевіряємо, чи є атрибут "user" в сесії
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn) {
            // Користувач авторизований, продовжуємо виконання
            chain.doFilter(request, response);
        } else {
            // Користувач не авторизований, перенаправляємо на сторінку помилки
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/error.jsp");
        }
    }

    @Override
    public void destroy() {
        // Знищення фільтра (якщо потрібно)
    }
}
