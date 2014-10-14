package br.com.Bean;
import java.sql.Date;


public class AnimalBean {
    private int    id;
    private String descricao;
    private String raca;
    private String historico;
    private String status;
    private int    idade;
    private String vacinado;
    private String vermifugado;
    private int    idCliente;

    public AnimalBean() {
    }

    public AnimalBean(int id, String descricao, String raca, int idade, String historico, String status, int idCliente, String vacinado, String vermifugado) {
        this.id = id;
        this.descricao = descricao;
        this.raca = raca;
        this.idade = idade;
        this.historico = historico;
        this.status = status;
        this.idCliente = idCliente;
        this.vacinado = vacinado;
        this.vermifugado = vermifugado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
            
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getVacinado() {
        return vacinado;
    }

    public void setVacinado(String vacinado) {
        this.vacinado = vacinado;
    }

    public String getVermifugado() {
        return vermifugado;
    }

    public void setVermifugado(String vermifugado) {
        this.vermifugado = vermifugado;
    }
}
