package br.com.xbank.domain.conta;

import br.com.xbank.domain.cliente.Cliente;
import java.math.*;

public class Conta {

    private Integer conta;
    private BigDecimal saldo;
    private Cliente titular;
    private Boolean isActive;

    public Conta() {
    }

    public Conta(Integer conta, BigDecimal saldo, Cliente titular, Boolean isActive) {
        this.conta = conta;
        this.saldo = saldo;
        this.titular = titular;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "[CONTA: " + conta + " // SALDO: " + saldo + " // CLIENTE: " + titular + "]";
    }

    public Integer getConta() {
        return conta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public Boolean getIsActive() {
        return isActive;
    }

}
