package java.Controller;

import java.Database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddDoctor")
public class AddDoctor extends HttpServlet {

    private int i;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {
            Date todaysDate = new Date();
            DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            String sid = request.getParameter("sid");
            int id = Integer.parseInt(sid);
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String city = request.getParameter("city");
            String email = request.getParameter("email");
            String age = request.getParameter("age");
            String address = request.getParameter("address");
            String qualification = request.getParameter("qualification");

            String DateAndTime = df2.format(todaysDate);

            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement pst = con.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?,?,?,?)");
            
            pst.setInt(1, id);
            pst.setString(2, fname);
            pst.setString(3, lname);
            pst.setString(4, gender);
            pst.setString(5, phone);
            pst.setString(6, city);
            pst.setString(7, email);
            pst.setString(8, age);
            pst.setString(9, address);
            pst.setString(10, DateAndTime);
            pst.setString(11, qualification);

            i = pst.executeUpdate();
            if (i > 0) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Data Add Successfully..!');");
                pw.println("window.location.href = \"AdminHome.jsp\";");
                pw.println("</script>");
                //RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
                //rd.forward(request, response);
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Failed !!!!,try Again Later!');");
                pw.println("window.location.href = \"addDoctor.jsp\";");
                pw.println("</script>");
                //RequestDispatcher rd = request.getRequestDispatcher("addDoctor.jsp");
                //rd.forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AddPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
