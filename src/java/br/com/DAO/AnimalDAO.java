
package br.com.DAO;
import br.com.Utilitarios.Conexao;
import br.com.Bean.AnimalBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimalDAO {
    private Conexao con;

    public AnimalDAO() {
        con = new Conexao();
    }

    public boolean salvarAnimal(AnimalBean animal) throws SQLException{
        
        try {
            
            String sql = "INSERT INTO ANIMAL(DESCRICAO, RACA, IDADE, HISTORICO, STATUS, ID_CLIENTE, VACINADO, VERMIFUGADO) VALUES"+
                         "(?,?,?,?,?,?,?,?);";
            
            PreparedStatement stm = con.getConnection().prepareStatement(sql);
            
            stm.setString(1, animal.getDescricao());
            stm.setString(2, animal.getRaca());
            stm.setInt(3, animal.getIdade());
            stm.setString(4, animal.getHistorico());
            //UM NOVO ANIMAL SEMPRE É INSERIDO COM STATUS DE VALIDAÇÃO
            animal.setStatus("V");
            stm.setString(5, animal.getStatus());
            stm.setInt(6, animal.getIdCliente());
            stm.setString(7, animal.getVacinado());
            stm.setString(8, animal.getVermifugado());
            
            stm.execute();
            con.getConnection().commit();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean alterarAnimal(AnimalBean animal) throws SQLException{
        
        try{
            String sql = "UPDATE ANIMAL SET DESCRICAO = ? , RACA = ? , IDADE = ? , HISTORICO = ?, STATUS = ?, VACINADO = ?, VERMIFUGADO = ?"+
                         "WHERE ID = ?";

            PreparedStatement stm = con.getConnection().prepareStatement(sql);
            stm.setString(1, animal.getDescricao());
            stm.setString(2, animal.getRaca());
            stm.setInt(3, animal.getIdade());
            stm.setString(4, animal.getHistorico());
            stm.setString(5, animal.getStatus());
            stm.setString(6, animal.getVacinado());
            stm.setString(7, animal.getVermifugado());
            stm.setInt(8, animal.getId());
            
            stm.execute();
            con.getConnection().commit();
        
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean excluirAnimal(AnimalBean animal) throws SQLException{
        try{    
            String sql = "DELETE FROM ANIMAL WHERE ID = ?";

            PreparedStatement stm = con.getConnection().prepareStatement(sql);

            stm.setInt(1, animal.getId());
            stm.execute();
            con.getConnection().commit();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    public List<AnimalBean> listarAnimalGeral() throws SQLException{
        
        List<AnimalBean> lista = new ArrayList<AnimalBean>();
        
        try {
            
        String sql = "SELECT ID, DESCRICAO, RACA, IDADE, HISTORICO, STATUS, ID_CLIENTE, VACINADO, VERMIFUGADO FROM ANIMAL";
        PreparedStatement stm = con.getConnection().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        con.getConnection().commit();
        
        while (rs.next()) {
            AnimalBean animal = new AnimalBean();
            
            animal.setId(rs.getInt("ID"));
            animal.setDescricao(rs.getString("DESCRICAO"));
            animal.setRaca(rs.getString("RACA"));
            
//            switch(rs.getInt("IDADE")){
//                case 1:
//                    animal.setIdade("de 0 a 3 mes");
//                case 2:
//                    animal.setIdade(rs.getInt("IDADE"));
//                case 3:
//                    animal.setIdade(rs.getInt("IDADE"));
//                case 4:
//                    animal.setIdade(rs.getInt("IDADE"));
//                case 5:
//                    animal.setIdade(rs.getInt("IDADE"));
//                default:
//            }
            
            animal.setIdade(rs.getInt("IDADE"));
            animal.setHistorico(rs.getString("HISTORICO"));
            animal.setStatus(rs.getString("STATUS"));
            animal.setIdCliente(rs.getInt("ID_CLIENTE"));
            animal.setVacinado(rs.getString("VACINADO"));
            animal.setVermifugado(rs.getString("VERMIFUGADO"));
            
            lista.add(animal);
        }
        
        } catch (Exception ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
    }

    public List<AnimalBean> listarAnimalParaDoacao() throws SQLException{
        
        List<AnimalBean> lista = new ArrayList<AnimalBean>();
        
        try {
            
        String sql = "SELECT ID, DESCRICAO, RACA, IDADE, HISTORICO, STATUS, ID_CLIENTE, VACINADO, VERMIFUGADO FROM ANIMAL WHERE STATUS = 'D' ";
        PreparedStatement stm = con.getConnection().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        con.getConnection().commit();
        
        while (rs.next()) {
            AnimalBean animal = new AnimalBean();
            
            animal.setId(rs.getInt("ID"));
            animal.setDescricao(rs.getString("DESCRICAO"));
            animal.setRaca(rs.getString("RACA"));
            animal.setIdade(rs.getInt("IDADE"));
            animal.setHistorico(rs.getString("HISTORICO"));
            animal.setStatus(rs.getString("STATUS"));
            animal.setIdCliente(rs.getInt("ID_CLIENTE"));
            animal.setVacinado(rs.getString("VACINADO"));
            animal.setVermifugado(rs.getString("VERMIFUGADO"));
            
            lista.add(animal);
        }
        
        } catch (Exception ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
    }
    
//ATUALIZA O STATUS DO ANIMAL PARA EM ADOÇÃO PARA DEIXÁ-LO EM RESERVA
    public boolean reservaAnimal(AnimalBean animal) throws SQLException{
        
        try{
            String sql = "UPDATE ANIMAL SET STATUS = 'E'"+
                         "WHERE ID = ?";

            PreparedStatement stm = con.getConnection().prepareStatement(sql);
            
            stm.setInt(1, animal.getId());
            
            stm.execute();
            con.getConnection().commit();
        
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

//ATUALIZA O STATUS DO ANIMAL PARA AUTORIZADO PARA EXIBIR NO DELIVERY
    public boolean autorizaAnimal(AnimalBean animal) throws SQLException{
        
        try{
            String sql = "UPDATE ANIMAL SET STATUS = 'A'"+
                         "WHERE ID = ?";

            PreparedStatement stm = con.getConnection().prepareStatement(sql);
            
            stm.setInt(1, animal.getId());
            
            stm.execute();
            con.getConnection().commit();
        
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

}
