package br.com.DAO;

import br.com.Utilitarios.Conexao;
import java.sql.PreparedStatement;
import br.com.Bean.ClienteBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
    private Conexao con; 

    public ClienteDAO() {
        con = new Conexao();  
    }
    
    
    public boolean salvarCliente(ClienteBean cliente){
     
        try {
            String sql = "INSERT INTO CLIENTE(NOME,CPF, ENDERECO, CEP, DT_NASCIMENTO, SN_ATIVO)"+
                    " VALUES(?,?,?,?,?,?);";
            
            PreparedStatement stm = con.getConnection().prepareStatement(sql);
            stm.setString(1,cliente.getNomeCliente());
            stm.setInt(2,   cliente.getCpf()); 
            stm.setString(3,cliente.getEndereco());
            stm.setInt(4,cliente.getCep());
            stm.setDate(5, (java.sql.Date) cliente.getDtNascimento());
            stm.setString(6,cliente.getSnAtivo() );
                    
            stm.execute();
            con.getConnection().commit();
            return true;       
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    public boolean alterarCliente(ClienteBean cliente){

        try {
            String sql = "UPDATE CLIENTE SET NOME = ?, CPF = ?, ENDERECO = ?, CEP = ?"+
                    "DT_NASCIMENTO = ?, SN_ATIVO = ? WHERE ID = ?;";
            
            
            PreparedStatement stm = con.getConnection().prepareStatement(sql);
            
            stm.setString(1,cliente.getNomeCliente());
            stm.setInt(2,   cliente.getCpf());
            stm.setString(3,cliente.getEndereco());
            stm.setInt(4,cliente.getCep());
            stm.setDate(5, (java.sql.Date) cliente.getDtNascimento());
            stm.setString(6,cliente.getSnAtivo() );
            stm.setInt(7,cliente.getIdCliente() );
    
            stm.execute();
            con.getConnection().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return false;
    }
    public boolean excluirCliente(ClienteBean cliente) {
        try{
            String sql = "DELETE FROM CLIENTE WHERE ID = ?";
        
            PreparedStatement stm = con.getConnection().prepareStatement(sql);

            stm.setInt(1, cliente.getIdCliente());
            stm.execute();
            con.getConnection().commit();

            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public List<ClienteBean> listarCliente() throws SQLException{
        
        List<ClienteBean> lista = new ArrayList<ClienteBean>();
        
        try {
            
        String sql = "SELECT ID, NOME, CPF, ENDERECO, CEP FROM CLIENTE";
        PreparedStatement stm = con.getConnection().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        con.getConnection().commit();
        
        while (rs.next()) {
            ClienteBean cliente = new ClienteBean();
            
            cliente.setIdCliente(rs.getInt("ID"));
            cliente.setNomeCliente(rs.getString("NOME"));
            cliente.setCpf(rs.getInt("CPF"));
            cliente.setEndereco(rs.getString("ENDERECO"));
            cliente.setCep(rs.getInt("CEP"));
            
            lista.add(cliente);
        }
        
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
    }
    
}
