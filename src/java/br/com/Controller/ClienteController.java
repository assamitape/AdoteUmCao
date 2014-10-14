
package br.com.Controller;

import br.com.Bean.ClienteBean;
import br.com.DAO.ClienteDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean
@SessionScoped
public class ClienteController implements Serializable{
    private ClienteBean cliente;
    private DataModel listaCliente;
    private boolean exibeBtnSalvar;
    private boolean exibeBtnAlterar;    

    public ClienteController() {
        cliente = new ClienteBean();
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public boolean isExibeBtnSalvar() {
        return exibeBtnSalvar;
    }

    public void setExibeBtnSalvar(boolean exibeBtnSalvar) {
        this.exibeBtnSalvar = exibeBtnSalvar;
    }

    public boolean isExibeBtnAlterar() {
        return exibeBtnAlterar;
    }

    public void setExibeBtnAlterar(boolean exibeBtnAlterar) {
        this.exibeBtnAlterar = exibeBtnAlterar;
    }

    public DataModel getListaCliente() {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            
            clienteDAO.listarCliente();
            listaCliente = new ListDataModel(clienteDAO.listarCliente());
            return listaCliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCliente;        
    }

    public void setListaCliente(DataModel listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    public String salvaCliente(){
        ClienteDAO clienteDAO = new ClienteDAO();
        if(clienteDAO.salvarCliente(cliente)){
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "cadastro com sucesso!", ""));
            return "listarclientes";
        }

        return "erro";
        
    }

    public String alteraCliente(){
        ClienteDAO clienteDAO = new ClienteDAO();
        if(clienteDAO.alterarCliente(cliente)){
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "alterado com sucesso!", ""));
            return "listarclientes";
        }

        return "erro";
        
    }
    
    public String deletaCliente(){
        ClienteDAO clienteDAO = new ClienteDAO();
        if(clienteDAO.excluirCliente(cliente)){
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "deletado com sucesso!", ""));
            return "listarclientes";
        }

        return "erro";
         
    }
    public void novoCliente(){
        cliente = new ClienteBean();
        escondeBntAlterar();
    }
    
    public void escondeBntAlterar(){
        setExibeBtnAlterar(false);
        setExibeBtnSalvar(true);
    }
    
    public void escondeBtnSalvar() throws IOException{
       FacesContext contexto = FacesContext.getCurrentInstance();
       
        if (cliente == null ){  
          contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Um registro precisa ser selecionado!", ""));            
          
        }else{ 
          setExibeBtnAlterar(true);
          setExibeBtnSalvar(false);
        }

    }
}
