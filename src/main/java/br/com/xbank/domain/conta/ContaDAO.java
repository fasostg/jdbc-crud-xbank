package br.com.xbank.domain.conta;

import java.util.*;
import java.math.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.xbank.domain.conta.Conta;
import br.com.xbank.domain.cliente.Cliente;
import br.com.xbank.domain.cliente.ClienteDadosRegistro;

public class ContaDAO {

    private Connection conn;

    public ContaDAO(Connection conn) {
        this.conn = conn;
    }

    public Set<Conta> listAll() {
        PreparedStatement ps;
        ResultSet rs;
        Set<Conta> contas = new HashSet<>();

        String query = "SELECT * FROM contas WHERE is_active=1";

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer num_conta = rs.getInt("numero_conta");
                BigDecimal saldo = rs.getBigDecimal("saldo");
                String cliente_nome = rs.getString("cliente_nome");
                String cliente_cpf = rs.getString("cliente_cpf");
                String cliente_email = rs.getString("cliente_email");
                Boolean isActive = rs.getBoolean("is_active");

                ClienteDadosRegistro data = new ClienteDadosRegistro(cliente_nome, cliente_cpf, cliente_email);
                Cliente cliente = new Cliente(data);
                Conta conta = new Conta(num_conta, saldo, cliente, isActive);
                contas.add(conta);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contas;
    }

    public void register(ContaDadosRegistro data) {
        PreparedStatement ps;
        Cliente cliente = new Cliente(data.dadosCliente());
        Conta conta = new Conta(data.conta(), BigDecimal.ZERO, cliente, true);

        String query = "INSERT INTO contas VALUES (?,?,?,?,?,?)";

        try {
            ps = conn.prepareStatement(query);

            ps.setInt(1, conta.getConta());
            ps.setBigDecimal(2, conta.getSaldo());
            ps.setString(3, cliente.getNome());
            ps.setString(4, cliente.getCpf());
            ps.setString(5, cliente.getEmail());
            ps.setBoolean(6, conta.getIsActive());

            ps.execute();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Conta adicionada com sucesso!");
    }

    public Conta findByNum(Integer numConta) {
        PreparedStatement ps;
        ResultSet rs;
        Conta conta = new Conta();

        String query = "SELECT * FROM contas WHERE numero_conta=?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, numConta);

            rs = ps.executeQuery();
            while (rs.next()) {
                Integer num_conta = rs.getInt(1);
                BigDecimal saldo = rs.getBigDecimal(2);
                String cliente_nome = rs.getString(3);
                String cliente_cpf = rs.getString(4);
                String cliente_email = rs.getString(5);
                Boolean isActive = rs.getBoolean(6);

                ClienteDadosRegistro dados = new ClienteDadosRegistro(cliente_nome, cliente_cpf, cliente_email);
                Cliente cliente = new Cliente(dados);
                conta = new Conta(num_conta, saldo, cliente, isActive);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conta;
    }

    public void updateFunds(Integer numConta, BigDecimal saldo) {
        PreparedStatement ps;

        String query = "UPDATE contas SET saldo=? WHERE numero_conta=?";

        try {
            ps = conn.prepareStatement(query);
            ps.setBigDecimal(1, saldo);
            ps.setInt(2, numConta);

            ps.execute();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirLogico(Integer numConta) {
        PreparedStatement ps;

        String query = "UPDATE contas SET is_active=0 WHERE numero_conta=?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, numConta);

            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
