package br.com.Controller;

import br.com.Bean.AnimalBean;
import br.com.DAO.AnimalDAO;
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
public class AnimalController implements Serializable{
    private AnimalBean animal;
    private DataModel listaAnimaisGeral;
    private DataModel listaAnimaisParaDoacao;
    private boolean exibeBtnSalvar;
    private boolean exibeBtnAlterar;    

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

    public AnimalController() {
        animal = new AnimalBean();
    }

    public AnimalBean getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalBean animal) {
        this.animal = animal;
    }

    public DataModel getListaAnimaisGeral() {
        try {
            AnimalDAO animalDAO = new AnimalDAO();
            
          //  animalDAO.listarAnimalGeral();
            listaAnimaisGeral = new ListDataModel(animalDAO.listarAnimalGeral());
            return listaAnimaisGeral;
        } catch (SQLException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAnimaisGeral;
    }

    public void setListaAnimaisGeral(DataModel listaAnimaisGeral) {
        this.listaAnimaisGeral = listaAnimaisGeral;
    }
    

    public DataModel getListaAnimaisParaDoacao() {
        try {
            AnimalDAO animalDAO = new AnimalDAO();
            
            animalDAO.listarAnimalParaDoacao();
            listaAnimaisParaDoacao = new ListDataModel(animalDAO.listarAnimalParaDoacao());
            return listaAnimaisParaDoacao;
        } catch (SQLException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAnimaisParaDoacao;
    }

    public void setListaAnimaisParaDoacao(DataModel listaAnimaisParaDoacao) {
        this.listaAnimaisParaDoacao = listaAnimaisParaDoacao;
    }
    
    public void novoAnimal(){
        animal = new AnimalBean();
        escondeBntAlterar();
        
    }
    
    public String salvaAnimal(){
        try {
            AnimalDAO animalDAO = new AnimalDAO();
            
            if(animalDAO.salvarAnimal(animal)){
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "cadastro com sucesso!", ""));
                return "listaranimais";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "erro";
        
    }

    public String alteraAnimal(){
        try {
            AnimalDAO animalDAO = new AnimalDAO();
            
            if(animalDAO.alterarAnimal(animal)){
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "alterado com sucesso!", ""));
                return "listaranimais";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "erro";
        
    }
    
    public void deletaAnimal(){
        try {
            AnimalDAO animalDAO = new AnimalDAO();
            
            if(animalDAO.excluirAnimal(animal)){
              msgDelecaoAnimal();
              getListaAnimaisParaDoacao();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void autorizarAnimal(){
        try {
            AnimalDAO animalDAO = new AnimalDAO();
            FacesContext contexto = FacesContext.getCurrentInstance();
            
            if (animal == null ){
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Um registro precisa ser selecionado!", ""));
                
            }else{ 
              if(animalDAO.autorizaAnimal(animal)){
                  contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Autorizado com sucesso!", ""));
              }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void selecionarAnimalGeral(){
        animal = (AnimalBean) listaAnimaisGeral.getRowData();
    }

    public void selecionarAnimalGeralParaDoacao(){
        getListaAnimaisGeral();
        animal = (AnimalBean) listaAnimaisParaDoacao.getRowData();
    }

    public void msgDelecaoAnimal() {
        addMessage("Atenção", "Animal deletado com sucesso.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void escondeBntAlterar(){
        setExibeBtnAlterar(false);
        setExibeBtnSalvar(true);
    }
    
    public void escondeBtnSalvar(){
        setExibeBtnAlterar(true);
        setExibeBtnSalvar(false);
    }
}
