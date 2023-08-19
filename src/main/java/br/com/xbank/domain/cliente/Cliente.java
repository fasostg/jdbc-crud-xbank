package br.com.xbank.domain.cliente;

public class Cliente {

    private String nome;
    private String cpf;
    private String email;

    public Cliente() {
    }

    public Cliente(ClienteDadosRegistro data) {
        this.nome = data.nome();
        this.cpf = data.cpf();
        this.email = data.email();
    }

    public String toString() {
        return "[NOME: " + nome + " // CPF: " + cpf + " // EMAIL: " + email + "]";
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

}
