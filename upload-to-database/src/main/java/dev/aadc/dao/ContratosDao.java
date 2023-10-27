package dev.aadc.dao;

import dev.aadc.model.Contratos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratosDao {

    private final Connection conect;

    public ContratosDao(Connection connection) {
        this.conect = connection;
    }


    // ============================================================================================================
    public Contratos save(Contratos contrato) {

        String sql = "INSERT INTO `contratosArquivos`.`contratos` (contratoFileName) VALUES (?)";

        try {
            PreparedStatement ps = conect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, contrato.getContratoFileName());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if(rs.next()) {
                    Integer generatedId = rs.getInt(1);
                    System.out.println("O id gerado foi: " + generatedId);
                } else {
                    throw new SQLException("Falha no id");
                }

                // rs.close();
            }

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contrato;

    }
    // ============================================================================================================


    // ============================================================================================================
    public List<Contratos> findAllContratos() {

        String sql = "SELECT idContrato, contratoFileName FROM `contratosArquivos`.`contratos`";

        List<Contratos> listaDeTodosOsContratos = new ArrayList<>();

        try {

            PreparedStatement ps = conect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer idContrato = rs.getInt("idContrato");
                String contratoFileName = rs.getString("contratoFileName");

                Contratos contratoRecuperadoDaBaseDeDados = new Contratos(idContrato, contratoFileName);
                listaDeTodosOsContratos.add(contratoRecuperadoDaBaseDeDados);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaDeTodosOsContratos;

    }
    // ============================================================================================================




}
