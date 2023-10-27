
<%@ page pageEncoding="UTF-8"%>


<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title> Upload dos contratos! </title>

        <link rel="stylesheet" href="styles.css">
    </head>

    <body>
        <h1> Upload do contrato: </h1>


        <form id="upload-contrato-form" action="controllerUpload" method="post" enctype="multipart/form-data">
          <div class="form-div">
            <h3> Selecione o arquivo: </h3>
            <input id="arquivo" type="file" name="arquivoContrato" >
            <button class="botao-enviar" id="submit" type="submit"> ENVIAR </button>
          </div>
        </form>

        <a href="index.jsp" class="botao-voltar"> VOLTAR </a>

    </body>

</html>

