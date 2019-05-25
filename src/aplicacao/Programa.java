package aplicacao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String op;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		do {

			System.out.println(menu());
			op = scanner.nextLine();

			String nome;
			String email;
			Integer id;
			switch (op) {
			case "1":
				nome = scanner.nextLine();
				email = scanner.nextLine();
				id = scanner.nextInt();

				cadastrarPessoa(entityManager, nome, email, id);
				break;
			case "2":
				System.out.println(listarRegistros(entityManager));
			case "3":
				nome = scanner.nextLine();
				email = scanner.nextLine();
				id = scanner.nextInt();
				alterarUmRegistro(entityManager, nome, email, id);
			case "4":
				id = scanner.nextInt();
				removerRegistro(entityManager, entityManagerFactory, id);
			default:
				break;

			}
		} while (op != "0");
		entityManager.close();
		entityManagerFactory.close();

	}

	private static void cadastrarPessoa(EntityManager entityManager, String nome, String email, Integer id) {

		Pessoa medicoFound = entityManager.find(Pessoa.class, id);
		medicoFound.setNome(nome);
		medicoFound.setEmail(email);
		entityManager.getTransaction().begin();
		entityManager.persist(medicoFound);
		entityManager.getTransaction().commit();

	}

	private static List<Pessoa> listarRegistros(EntityManager entityManager) {
		String jpql = "SELECT m FROM Pessoa m";
		return entityManager.createQuery(jpql, Pessoa.class).getResultList();
	}

	private static void alterarUmRegistro(EntityManager entityManager, String nome, String email, Integer id) {
		Pessoa medicoFound = entityManager.find(Pessoa.class, id);
		medicoFound.setNome(nome);
		medicoFound.setEmail(email);
		entityManager.getTransaction().begin();
		entityManager.persist(medicoFound);
		entityManager.getTransaction().commit();
	}

	private static void removerRegistro(EntityManager entityManager, EntityManagerFactory entityManagerFactory,
			Integer id) {
		Pessoa medicoFound = entityManager.find(Pessoa.class, 1);
		entityManager.getTransaction().begin();
		entityManager.remove(medicoFound);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	public static String menu() {
		String menu = "";
		menu += "Digite 1 para cadastrar um médico.\n";
		menu += "Digite 2 para listar os médicos. \n";
		menu += "Digite 3 para alterar um registro. \n";
		menu += "Digite 4 para remover um registro. \n";
		menu += "Digite 0 para sair";
		return menu;
	}

}