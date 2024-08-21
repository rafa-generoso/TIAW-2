package exercicio2;

import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		PersonagemDAO personagemDAO = new PersonagemDAO();
		
		System.out.println("O que deseja fazer? \n1)Listar \n2)Inserir \n3)Excluir \n4)Atualizar \n5)Sair\n");
		int opcao;
		
		do{
			opcao = sc.nextInt();
			sc.nextLine();
			
		if(opcao==1) {	
			
		//Listar
		 System.out.println("== Personagens de Teen Wolf == \n");
	        List<Personagens> personagens = personagemDAO.get();
	        for (Personagens personagem : personagens) {
	            System.out.println(personagem.toString());
	        }
		} else if(opcao==2) {
	        
	    //Inserir
	     System.out.println("ID: ");
	     int id = sc.nextInt();
	     sc.nextLine();
	     
	     System.out.println("Nome: ");
	     String nome = sc.nextLine();
	     
	     System.out.println("Idade: ");
	     int idade = sc.nextInt();
	     sc.nextLine();
	     
	     System.out.println("Sexo (F/M): ");
	     char sexo = sc.nextLine().charAt(0);
	     
	     System.out.println("Especie: ");
	     String especie = sc.nextLine();
	     
	     Personagens novoPersonagem = new Personagens(id, nome, idade, sexo, especie);
	     personagemDAO.inserir(novoPersonagem);
	     
		} else if(opcao==3) {
			
	    //Excluir
	     System.out.println("Digite o ID do personagem que deseja excluir: ");
	     int id = sc.nextInt();
	     personagemDAO.excluir(id);
	     
		} else if(opcao==4) {
	     
	    //Atualizar
	     System.out.println("ID do novo personagem : ");
	     int id = sc.nextInt();
	     sc.nextLine();
	     
	     System.out.println("Nome: ");
	     String nome = sc.nextLine();
	     
	     System.out.println("Idade: ");
	     int idade = sc.nextInt();
	     sc.nextLine();
	     
	     System.out.println("Sexo (F/M): ");
	     char sexo = sc.nextLine().charAt(0);
	     
	     System.out.println("Especie: ");
	     String especie = sc.nextLine();
	     
	     Personagens personagemAtualizado = new Personagens(id, nome, idade, sexo, especie);
	     personagemDAO.atualizar(personagemAtualizado);
	     
		}

		}while(opcao!=5);
		
		sc.close();
		personagemDAO.close();
	}

}
