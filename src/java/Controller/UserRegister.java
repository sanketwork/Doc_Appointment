package java.Controller;

import java.Database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {

    private String user;
    private String pass;
    private int i = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {
            Connection con = DatabaseConnection.initializeDatabase();

            user = request.getParameter("Username");
            pass = request.getParameter("password");
            String repass = request.getParameter("repassword");

            PreparedStatement pst = con.prepareStatement("insert into login values(?,?)");
            pst.setString(1, user);
            pst.setString(2, pass);
            pst.setString(3, repass);
            i = pst.executeUpdate();
            if (i > 0) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Register Successfully..!');");
                pw.println("window.location.href = \"index.jsp\";");
                pw.println("</script>");
                //RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                //rd.forward(request, response);
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Register Failed');");
                pw.println("window.location.href = \"userRegister.jsp\";");
                pw.println("</script>");
                //RequestDispatcher rd = request.getRequestDispatcher("userRegister.jsp");
                //rd.forward(request, response);
            }

        } catch (Exception e) {

        }

    }

}