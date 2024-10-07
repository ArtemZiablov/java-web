package ua.karazin.javaweb.homework2;

import java.io.*;

// TODO: http://localhost:8080/Homework2_war_exploded/hello-servlet?name=Artem

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    // Метод init() викликається під час ініціалізації сервлета
    public void init() {
        message = "Hello World!";
    }

    // Метод doGet() для обробки GET-запиту (обов'язковий метод для сервлету)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Встановлюємо тип контенту у відповідь (метод setContentType(String type) об'єкта HttpServletResponse)
        response.setContentType("text/html");

        // Отримуємо один параметр з запиту за назвою "name" (метод getParameter(String name) об'єкта HttpServletRequest)
        String name = request.getParameter("name");

        // Отримуємо всі імена параметрів з запиту (метод getParameterNames() об'єкта HttpServletRequest)
        java.util.Enumeration<String> parameterNames = request.getParameterNames();

        // Отримуємо всі значення параметра "name" (метод getParameterValues(String name) об'єкта HttpServletRequest)
        String[] values = request.getParameterValues("name");

        // Виводимо відповідь в браузер користувача (метод getWriter() об'єкта HttpServletResponse)
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        // Якщо параметр "name" був переданий, відображаємо його значення
        if (name != null) {
            out.println("<p>Hello, " + name + "!</p>");
        }

        // Виводимо всі отримані параметри (обробка всіх параметрів з getParameterNames())
        out.println("<p>Імена параметрів:</p>");
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            out.println("<p>" + paramName + ": " + request.getParameter(paramName) + "</p>");
        }

        // Отримуємо контекст сервлета (метод getServletContext())
        ServletContext context = getServletContext();

        // Встановлюємо атрибут в контексті сервлета
        context.setAttribute("myAttribute", "Це атрибут контексту");

        // Отримуємо атрибут з контексту сервлета
        String contextAttribute = (String) context.getAttribute("myAttribute");

        // Виводимо атрибут контексту
        out.println("<p>Атрибут контексту: " + contextAttribute + "</p>");
        out.println("</body></html>");
    }

    // Метод destroy() викликається під час завершення роботи сервлета
    public void destroy() {
    }
}

