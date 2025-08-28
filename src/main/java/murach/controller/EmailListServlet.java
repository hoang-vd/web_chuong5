package murach.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import murach.business.User;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.html";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        if (action.equals("join")) {
            url = "/index.html";    // the "join" page
        } else if (action.equals("add") || action.equals("")) {

            // Get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String heardFrom = request.getParameter("heardFrom");
            String contactVia = request.getParameter("contactVia");

            boolean wantsUpdates = request.getParameter("wantsUpdates") != null;
            boolean emailOK = request.getParameter("emailOK") != null;

            // Create User object
            User user = new User(firstName, lastName, email, dob, heardFrom, wantsUpdates, emailOK, contactVia);

            // Set User object in request
            request.setAttribute("user", user);

            url = "/thanks.jsp";   // forward to thanks page
        }

        // forward request and response
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
