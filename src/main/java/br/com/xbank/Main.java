package br.com.xbank;

import java.util.*;

import br.com.xbank.domain.conta.ContaService;
import br.com.xbank.domain.cliente.ClienteDadosRegistro;
import br.com.xbank.domain.conta.ContaDadosRegistro;
import br.com.xbank.domain.conta.Conta;

import java.math.*;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        Integer numConta;
        Integer opcao = 0;
        BigDecimal saldo;

        while (opcao != 7) {
            System.out.println(" ");
            System.out.println("Bem-vindo(a) ao XBank");
            System.out.println("1- Listar todas as contas");
            System.out.println("2- Criar uma nova conta");
            System.out.println("3- Consultar saldo da conta");
            System.out.println("4- Realizar saque");
            System.out.println("5- Realizar depósito");
            System.out.println("6- Excluir conta");
            System.out.println("7- Sair");
            System.out.println("Digite o número da operação:");
            opcao = Integer.parseInt(read.nextLine());

            ContaService service = new ContaService();
            switch (opcao) {
                case 1:
                    Set<Conta> contas = service.listarContas();
                    for (Conta conta : contas) {
                        System.out.println(conta);
                    }
                    break;
                case 2:
                    System.out.println("Digite o seu nome: ");
                    String nome = read.nextLine();
                    System.out.println("Digite o seu CPF: ");
                    String cpf = read.nextLine();
                    System.out.println("Digite o seu email: ");
                    String email = read.nextLine();
                    System.out.println("Digite o número da conta: ");
                    numConta = Integer.parseInt(read.nextLine());

                    ClienteDadosRegistro dadosCliente = new ClienteDadosRegistro(nome, cpf, email);
                    ContaDadosRegistro dadosConta = new ContaDadosRegistro(numConta, dadosCliente);
                    service.abrirConta(dadosConta);

                    break;
                case 4:
                    System.out.println("Digite o número da conta: ");
                    numConta = Integer.parseInt(read.nextLine());
                    System.out.println("Digite o valor do saque: ");
                    saldo = new BigDecimal(read.nextLine());
                    service.realizarSaque(numConta, saldo);
                    break;
                case 5:
                    System.out.println("Digite o número da conta: ");
                    numConta = Integer.parseInt(read.nextLine());
                    System.out.println("Digite o valor do deposito: ");
                    saldo = new BigDecimal(read.nextLine());
                    service.realizarDeposito(numConta, saldo);
                    break;
                case 6:
                    System.out.println("Digite o número da conta a ser deletada: ");
                    numConta = Integer.parseInt(read.nextLine());
                    service.excluirLogico(numConta);
                    break;
                default:
                    break;
            }
            System.out.println("Operação finalizada!");
        }

        System.out.println("Finalizando aplicação!");
    }

}
