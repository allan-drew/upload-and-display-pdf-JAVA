package dev.aadc.controller;

import dev.aadc.connection.ConnectionFactory;
import dev.aadc.dao.ContratosDao;
import dev.aadc.model.Contratos;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

@MultipartConfig // An HTTP multipart request is a type of HTTP request that allows clients to send files and data to an HTTP server.
@WebServlet("/controllerUpload")
public class ControllerUpload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("--- Sent to controller! --- ");

        // The Part interface is a part of the Java Servlet API.
        // It represents a part or form item that was received within a multipart/form-data POST request
        // The interface provides methods to retrieve the content of the part, such as getSubmittedFileName(), getInputStream() and getSize()
        Part arquivoPart = req.getPart("arquivoContrato"); // é o atributo 'name' no input do formulario

        String contratoFileName = arquivoPart.getSubmittedFileName();
        System.out.println("O arquivo submetido foi o: " + contratoFileName);

        // criando uma String com o path + nome do arquivo
        String uploadPathAndFileName = "/Users/allan-andrew/Desktop/UploadToDatabase/upload-to-database/src/main/webapp/uploaded-files/" + contratoFileName;
        System.out.println(uploadPathAndFileName);

        try {

            // Input: leitura de arquivos =====> Object --> InputStream --> FileInputStream
            // Outuput: escrita de arquivos =====> Object --> OutputStream --> FileOutputStream

            // InputStream is designed to provide a common interface for reading data from various sources, such as files, network connections...
            // getInputStream returns an InputStream object which can be used to read content of the part
            InputStream leitor = arquivoPart.getInputStream(); // abrindo um stream para ser possível a leitura dos bytes do arquivo submetido do formulário

            // FileOutputStream is an output stream for writing data to a file!
            // uploadPathAndFileName é o caminho do arquivo no qual o conteúdo será gravado
            FileOutputStream escritor = new FileOutputStream(uploadPathAndFileName); // abrindo um stream para ser possível escrever no uploadPathAndFileName

            //-------
            // array de bytes no tamanho igual ao número de bytes submetidos
            // esse array de byte será usado como buffer para a leitura dos dados e para a posterior escrita
            byte[] bufferBytes = new byte[leitor.available()]; // available() returns an estimate of the number of bytes that can be read (or skipped over)
            // aqui, temos um array de bytes de tamanho definido.


            // variável para armazenar o número de bytes lidos que posteriormente será usado para fazer a escrita dentro do loop
            int numeroBytesLidos = 0;
            //--------


            // read() --> Reads some number of bytes from the input stream (leitor) and stores them into the buffer array b (bufferBytes).
            // The number of bytes actually read is returned as an integer.
            // Returns -1 if there is no more data because the end of the stream
            while ((numeroBytesLidos = leitor.read(bufferBytes)) != -1) {

                // Os dados em buffer que estão no array de bytes bufferBytes são escritos no stream escritor.
                // "off" é o índice inicial no array de bytes b a partir do qual os dados serão escritos
                // O número de bytes lidos (numeroBytesLidos) será passado para o número o len (len – the number of bytes to write.)
                escritor.write(bufferBytes, 0 , numeroBytesLidos);
            }

            escritor.close(); // Closes this file output stream
            System.out.println("File uploaded to the server! ");


            // Saving contratoFileName to database:
            Contratos novoContrato = new Contratos(contratoFileName); // passando o contratoFileName obtido com o Part

            Connection connection = ConnectionFactory.connection();
            ContratosDao contratosDao = new ContratosDao(connection);

            contratosDao.save(novoContrato);

            System.out.println("File (path) saved into database sucessfully! ");

            resp.sendRedirect("upload-sucesso.html");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}

