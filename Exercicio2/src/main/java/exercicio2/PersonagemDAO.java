package exercicio2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO extends DAO {
	
	public PersonagemDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean inserir(Personagens personagem) {
		boolean status = false;
		try {  	        
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO personagens (id, nome, idade, sexo, especie) "
				       + "VALUES ("+personagem.getID()+ ", '" + personagem.getNome() + "', '"  
				       + personagem.getIdade() + "', '" + personagem.getSexo() + "', '" + personagem.getEspecie() + "');";
			//System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Personagens get(int id) {
		Personagens personagem = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM personagens WHERE id=" + id;
			//System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 personagem = new Personagens(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("sexo").charAt(0), rs.getString("especie"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return personagem;
	}
	
	
	public List<Personagens> get() {
		return get("id");
	}	
	
	private List<Personagens> get(String orderBy) {	
	
		List<Personagens> personagens = new ArrayList<Personagens>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM personagens" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			//System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Personagens p = new Personagens(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("sexo").charAt(0), rs.getString("especie"));
	            personagens.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return personagens;
	}
	
	public boolean atualizar(Personagens personagem) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE personagens SET nome = '" + personagem.getNome() + 
		             "', idade = " + personagem.getIdade() + 
		             ", sexo = '" + personagem.getSexo() + 
		             "', especie = '" + personagem.getEspecie() + 
		             "' WHERE id = " + personagem.getID();
			//System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluir(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM personagens WHERE id = " + id;
			//System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
}