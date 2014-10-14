
package br.com.Bean;

import java.io.Serializable;
import java.sql.Date;



public class ClienteBean implements Serializable{
    private int    idCliente;
    private String nomeCliente;
    private int cep;
    private String endereco;
    private int    cpf;
    private Date   dtNascimento;
    private String snAtivo;

    public ClienteBean() {
    }

    public ClienteBean(int idCliente, String nomeCliente, int cep, String endereco, int cpf, java.util.Date dtNascimento, String snAtivo) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cep = cep;
        this.endereco = endereco;
        this.cpf = cpf;
        this.dtNascimento = new java.sql.Date(dtNascimento.getTime());
        this.snAtivo = snAtivo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public java.util.Date getDtNascimento() {
        return (java.util.Date)dtNascimento;
        
    }

    public void setDtNascimento(java.util.Date  dtNascimento) {
        this.dtNascimento =  new java.sql.Date(dtNascimento.getTime());
    }

    public String getSnAtivo() {
        return snAtivo;
    }

    public void setSnAtivo(String snAtivo) {
        this.snAtivo = snAtivo;
    }


}
