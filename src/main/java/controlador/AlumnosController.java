/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Alumnos;
import modelo.AlumnosDAO;

/**
 *
 * @author nfernandez
 */
@WebServlet(name="AlumnosController", urlPatterns={"/AlumnosController"})
public class AlumnosController extends HttpServlet 
{
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        AlumnosDAO alumnosdao=new AlumnosDAO();
        String accion;
        RequestDispatcher dispacher = null;
        accion=request.getParameter("accion");
        
        if(accion==null||accion.isEmpty())
        {
            dispacher=request.getRequestDispatcher("vistas/alumnos.jsp");
        }
        else if(accion.equals("modificar"))
        {
 
           dispacher=request.getRequestDispatcher("vistas/modificar.jsp"); 
        }
         else if(accion.equals("actualizar"))
        {
            int id=Integer.parseInt(request.getParameter("id"));
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String mail=request.getParameter("mail");           
            Alumnos alumno=new Alumnos(id,nombre,apellido,mail);
            alumnosdao.actualizarAlumno(alumno);            
            
            dispacher=request.getRequestDispatcher("vistas/alumnos.jsp");
        }
         
         
        else if(accion.equals("eliminar"))
        {
          int id=Integer.parseInt(request.getParameter("id")) ;
          alumnosdao.eliminarAlumno(id);
          dispacher=request.getRequestDispatcher("vistas/alumnos.jsp"); 
        }
        
        
        else if(accion.equals("nuevo"))
        {
            dispacher=request.getRequestDispatcher("vistas/nuevo.jsp"); 
        } 
        else if(accion.equals("insert"))
        {
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String mail=request.getParameter("mail");            
            Alumnos alumno=new Alumnos(0,nombre,apellido,mail);
            alumnosdao.insertarAlumnos(alumno);            
            dispacher=request.getRequestDispatcher("vistas/alumnos.jsp"); 
        }
        else
        {
            dispacher=request.getRequestDispatcher("vistas/alumnos.jsp");
        }
        
        
            
        dispacher.forward(request,response);        
    } 

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        doGet(request,response);
    }

    
    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }

}
