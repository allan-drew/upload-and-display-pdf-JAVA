package dev.aadc.controller;


import dev.aadc.connection.ConnectionFactory;
import dev.aadc.dao.ContratosDao;
import dev.aadc.model.Contratos;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@MultipartConfig
@WebServlet("/controllerDownload")
public class ControllerDownload extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("--- Recuperando arquivos da base dados... --- ");

        Connection connection = null;

        try {
            connection = ConnectionFactory.connection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ContratosDao contratosDao = new ContratosDao(connection);
        List<Contratos> contratosBaseDeDados = contratosDao.findAllContratos();

        System.out.println(contratosBaseDeDados);

        req.setAttribute("cont", contratosBaseDeDados);

        RequestDispatcher rd = req.getRequestDispatcher("displayContratos.jsp");
        rd.forward(req, resp);

    }

}
