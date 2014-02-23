/**
 * $Author:  $
 * 
 * Revision History
 * $Log: LoginServlet.java,v $ *
 *
 */
package ar.edu.um.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.um.dominio.MensajeDominio;
import ar.edu.um.dominio.UsuarioDominio;
import ar.edu.um.entidad.Mensaje;
import ar.edu.um.entidad.Usuario;
 
/**
 * LoginServlet ofrece los métodos para manejar mensajes
 * 
 * $Id: $
 * 
 */
@WebServlet(
        description = "Login Servlet",
        urlPatterns = { "/LoginServlet" }
        )
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
     
    public void init() throws ServletException {
        //we can create DB connection resource here and set it to Servlet context
        if(getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/mysql_db") &&
                getServletContext().getInitParameter("dbUser").equals("mysql_user") &&
                getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd"))
        getServletContext().setAttribute("DB_Success", "True");
        else throw new ServletException("DB Connection error");
    }
 
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        //get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
         
        log("User="+user+"::password="+pwd);
        UsuarioDominio usuDominio = new UsuarioDominio();
        Usuario usuario = usuDominio.iniciarSesion(user, pwd);
        
        if(usuario != null){

            MensajeDominio mensajeDominio = new MensajeDominio();
            List<Mensaje> mensajes = mensajeDominio.visualizarTablon(usuario.getIdusuario());
            	
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginSuccess.jsp");
            request.setAttribute("usuario", usuario);
            request.setAttribute("mensajes", mensajes);
            rd.include(request, response);
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);             
        }
         
    }
 
}