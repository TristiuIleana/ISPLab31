package ro.ulbs.isplab31;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CounterServlet", value = "/CounterServlet")
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer counterGet = (Integer) session.getAttribute("CounterGetName");
        if (counterGet == null) {
            counterGet = 0;
        }

        // Cerinta 3.5.2: Preluare valoare inc / dec din parametru
        String incStr = request.getParameter("inc");
        String decStr = request.getParameter("dec");

        if (incStr != null && !incStr.isEmpty()) {
            int step = Integer.parseInt(incStr);
            counterGet += step;
        } else if (decStr != null && !decStr.isEmpty()) { // Cerinta 3.5.1: Decrementare
            int step = Integer.parseInt(decStr);
            counterGet -= step;
        }

        session.setAttribute("CounterGetName", counterGet);

        // Cerinta 3.5.3: Setare/Resetare atribut de avertizare cand ajunge la 0
        if (counterGet == 0) {
            session.setAttribute("WarningGet", true);
        } else {
            session.removeAttribute("WarningGet");
        }

        status(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer counterPost = (Integer) session.getAttribute("CounterPostName");
        if (counterPost == null) {
            counterPost = 0;
        }

        String action = request.getParameter("action");
        String incStr = request.getParameter("inc");
        String decStr = request.getParameter("dec");

        // Cerinta 3.5.1 & 3.5.2: Incrementare sau decrementare cu pasul furnizat
        if ("Decrement POST".equals(action)) {
            if (decStr != null && !decStr.isEmpty()) {
                int step = Integer.parseInt(decStr);
                counterPost -= step;
            }
        } else {
            if (incStr != null && !incStr.isEmpty()) {
                int step = Integer.parseInt(incStr);
                counterPost += step;
            }
        }

        session.setAttribute("CounterPostName", counterPost);

        // Cerinta 3.5.3: Setare/Resetare atribut de avertizare la valoarea 0
        if (counterPost == 0) {
            session.setAttribute("WarningPost", true);
        } else {
            session.removeAttribute("WarningPost");
        }

        status(request, response);
    }

    private void status(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        HttpSession session = request.getSession();

        printWriter.println("<h2>Welcome to Counter Servlet</h2>");

        Integer counterGet = (Integer) session.getAttribute("CounterGetName");
        if (counterGet == null) counterGet = 0;
        printWriter.println("<br> Counter for GET: " + counterGet);

        // Cerinta 3.5.3: Verificare si afisare "WARNING GET"
        if (Boolean.TRUE.equals(session.getAttribute("WarningGet"))) {
            printWriter.println(" <strong style='color:red;'>WARNING GET</strong>");
        }

        Integer counterPost = (Integer) session.getAttribute("CounterPostName");
        if (counterPost == null) counterPost = 0;
        printWriter.println("<br> Counter for POST: " + counterPost);

        // Cerinta 3.5.3: Verificare si afisare "WARNING POST"
        if (Boolean.TRUE.equals(session.getAttribute("WarningPost"))) {
            printWriter.println(" <strong style='color:red;'>WARNING POST</strong>");
        }

        printWriter.println("<br><br><a href='index.jsp'>Inapoi la pagina principala</a>");
    }
}