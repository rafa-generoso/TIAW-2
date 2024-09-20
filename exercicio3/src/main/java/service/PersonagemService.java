package service;

import dao.PersonagemDAO;
import model.Personagem;
import spark.Request;
import spark.Response;

public class PersonagemService {
	
	private PersonagemDAO personagemDAO;
	
	public PersonagemService() {
		personagemDAO = new PersonagemDAO();
	}
		
	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		int idade = Integer.parseInt(request.queryParams("idade"));
		char sexo = request.queryParams("sexo").charAt(0);
		String especie = request.queryParams("especie");
		
		if (nome == null || especie == null || idade <= 0) {
	        response.status(400); // Bad request
	        return "Parâmetros inválidos";
	    }
		
		int id = personagemDAO.getMaxID() + 1;
		
		Personagem persona = new Personagem(id, nome, idade, sexo, especie);
		
		personagemDAO.inserir(persona);
		
		response.status(201); //201 Created
		
		return id;
	}
	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Personagem persona = (Personagem) personagemDAO.get(id);
		
		if(persona!=null) {
			response.header("Content-Type" , "application/xml");
			response.header("Content-Encoding" , "UTF-8");
			
			return "<personagem>\n" +
					"\t<id>" + persona.getID() + "</id>\n" +
					"\t<nome>" + persona.getNome() + "</nome>\n" +
					"\t<idade>" + persona.getIdade() + "</idade>\n" +
					"\t<sexo>" + persona.getSexo() + "</sexo>\n" +
					"\t<especie>" + persona.getEspecie() + "</especie>\n" +
					"</personagem>";
		} else {
			response.status(404); //404 Not found
			return "Personagem" + id + " não encontrado.";
		}
				
	}
	
	public Object update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));

		Personagem persona = (Personagem) personagemDAO.get(id);

		if(persona!=null) {
			persona.setNome(request.queryParams("nome"));
			persona.setIdade(Integer.parseInt(request.queryParams("idade")));
			persona.setSexo(Character.toUpperCase(request.queryParams("sexo").charAt(0)));
			persona.setEspecie(request.queryParams("especie"));
			
			personagemDAO.atualizar(persona);
			
			return id;
		} else {
			response.status(404); //404 Not found
			return "Personagem não encontrado";
		}
	}
	
	public Object remove(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));

		Personagem persona = (Personagem) personagemDAO.get(id);

		if(persona!=null) {
			personagemDAO.excluir(id);
			
			response.status(200); //Success
			return id;
		} else {
			response.status(404); //404 Not found
			return "Personagem não encontrado";
		}
	}
	
	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<personagens type=\"array\">");
		PersonagemDAO personagemDAO = new PersonagemDAO();
		
		for(Personagem persona : personagemDAO.get()) {
			returnValue.append("\n<personagem>\n" +
					"\t<id>" + persona.getID() + "</id>\n" +
					"\t<nome>" + persona.getNome() + "</nome>\n" +
					"\t<idade>" + persona.getIdade() + "</idade>\n" +
					"\t<sexo>" + persona.getSexo() + "</sexo>\n" +
					"\t<especie>" + persona.getEspecie() + "</especie>\n" +
					"</personagem>");
		}
		returnValue.append("</personagens>");
		response.header("Content-Type" , "application/xml");
		response.header("Content-Encoding" , "UTF-8");
		return returnValue.toString();
	}
}
