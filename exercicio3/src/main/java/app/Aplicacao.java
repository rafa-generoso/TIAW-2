package app;

import static spark.Spark.*;
import service.PersonagemService;

public class Aplicacao {
	
	private static PersonagemService personagemService = new PersonagemService();
	
	public static void main(String[] args) {
		port(8080);
		
		before((request, response) -> {
		    response.header("Access-Control-Allow-Origin", "*"); // Permite todas as origens
		    response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		    response.header("Access-Control-Allow-Headers", "Content-Type");
		});

		// Permitir preflight requests
		options("/*", (request, response) -> {
		    response.header("Access-Control-Allow-Origin", "*");
		    response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		    response.header("Access-Control-Allow-Headers", "Content-Type");
		    response.status(200); // 200 OK
		    return "";
		});

		
		post("/personagem", (request, response) -> personagemService.add(request, response));
		
		get("/personagem/:id", (request, response) -> personagemService.get(request, response));
		
		put("/personagem/update/:id", (request, response) -> personagemService.update(request, response));
		
		delete("/personagem/remove/:id", (request, response) -> personagemService.remove(request, response));
		
		get("/personagem", (request, response) -> personagemService.getAll(request, response));
	}

}
