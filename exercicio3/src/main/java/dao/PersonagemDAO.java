package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Personagem;

public class PersonagemDAO extends DAO {
	
	public PersonagemDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean inserir(Personagem persona) {
		boolean status = false;
		try {  	        
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO personagens (id, nome, idade, sexo, especie) "
				       + "VALUES ("+persona.getID()+ ", '" + persona.getNome() + "', '"  
				       + persona.getIdade() + "', '" + persona.getSexo() + "', '" + persona.getEspecie() + "');";
			//System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Personagem get(int id) {
		Personagem persona = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM personagens WHERE id=" + id;
			//System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 persona = new Personagem(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("sexo").charAt(0), rs.getString("especie"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return persona;
	}
	
	public int getMaxID() {
		int MaxID = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT MAX(id) FROM personagens";
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				MaxID = rs.getInt(1);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return MaxID;
	}
	
	
	public List<Personagem> get() {
		return get("id");
	}	
	
	private List<Personagem> get(String orderBy) {	
	
		List<Personagem> personagens = new ArrayList<Personagem>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM personagens" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			//System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Personagem p = new Personagem(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("sexo").charAt(0), rs.getString("especie"));
	            personagens.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return personagens;
	}
	
	public List<Personagem> getAll(){
		List<Personagem> personagens = new ArrayList<Personagem>();
		return personagens;
	}
	
	public boolean atualizar(Personagem persona) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE personagens SET nome = '" + persona.getNome() + 
		             "', idade = " + persona.getIdade() + 
		             ", sexo = '" + persona.getSexo() + 
		             "', especie = '" + persona.getEspecie() + 
		             "' WHERE id = " + persona.getID();
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