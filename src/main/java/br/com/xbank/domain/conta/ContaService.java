package br.com.xbank.domain.conta;

import br.com.xbank.ConnectionFactory;

import java.util.*;
import java.sql.*;
import java.math.*;

public class ContaService {

    private ConnectionFactory connFactory;

    public ContaService() {
        this.connFactory = new ConnectionFactory();
    }

    public Set<Conta> listarContas() {
        Connection conn = connFactory.getConnection();

        return (new ContaDAO(conn).listAll());
    }

    public void abrirConta(ContaDadosRegistro data) {
        Connection conn = connFactory.getConnection();
        ContaDAO contaDao = new ContaDAO(conn);
        contaDao.register(data);

        return;
    }

    public Conta buscarPorNumero(Integer numConta) {
        Connection conn = connFactory.getConnection();
        ContaDAO contaDao = new ContaDAO(conn);

        return contaDao.findByNum(numConta);
    }

    public BigDecimal consultarSaldo(Integer numConta) {
        Conta conta = buscarPorNumero(numConta);

        return conta.getSaldo();
    }

    public void realizarSaque(Integer numConta, BigDecimal saldo) {
        Conta conta = buscarPorNumero(numConta);

        if (saldo.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Saque deve ser maior do que zero!");
            return;
        }

        saldo = conta.getSaldo().subtract(saldo);
        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("You have no dough my man!");
            return;
        } else {
            Connection conn = connFactory.getConnection();
            ContaDAO contaDao = new ContaDAO(conn);
            contaDao.updateFunds(conta.getConta(), saldo);
        }
        return;
    }

    public void realizarDeposito(Integer numConta, BigDecimal saldo) {
        Conta conta = buscarPorNumero(numConta);

        if (saldo.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Deposito deve ser maior do que zero!");
            return;
        }

        saldo = conta.getSaldo().add(saldo);

        Connection conn = connFactory.getConnection();
        ContaDAO contaDao = new ContaDAO(conn);
        contaDao.updateFunds(conta.getConta(), saldo);

        return;
    }

    public void excluirLogico(Integer numConta) {
        var conta = buscarPorNumero(numConta);
        if (conta.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("A conta tem saldo, por isso não pode ser encerrada!");
            return;
        }

        Connection conn = connFactory.getConnection();
        ContaDAO contaDao = new ContaDAO(conn);
        contaDao.excluirLogico(numConta);
    }
}
