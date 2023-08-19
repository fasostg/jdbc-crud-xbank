package br.com.xbank.domain.conta;

import br.com.xbank.domain.cliente.ClienteDadosRegistro;
import java.math.*;

public record ContaDadosRegistro(Integer conta, ClienteDadosRegistro dadosCliente) {

}
