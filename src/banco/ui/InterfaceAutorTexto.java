package banco.ui;

import java.util.List;

import banco.dao.AutorDao;
import banco.modelo.Autor;

public class InterfaceAutorTexto extends InterfaceModeloTexto {

	private AutorDao dao;

	public InterfaceAutorTexto() {
		super();
		dao = new AutorDao();
	}

	private Autor obtemDadosCliente(Autor autor) {

		System.out.print("Insira o nome do autor: ");
		String nome = entrada.nextLine();

		System.out.println("Insira o CPF do autor (somente números): ");
		long cpf = entrada.nextLong();
		entrada.nextLine();

		Autor novoAutor = new Autor(0, nome, cpf);

		return novoAutor;
	}

	@Override
	public void adicionar() {
		System.out.println("Adicionar autor");
		System.out.println();

		Autor novoAutor = obtemDadosCliente(null);
		dao.insert(novoAutor);

	}

	@Override
	public void listarTodos() {
		List<Autor> autores = dao.getAll();

		System.out.println("Lista de autores");
		System.out.println();

		System.out.println("id\tNome\tCPF");

		for (Autor autor : autores) {
			imprimeItem(autor);
		}

	}

	@Override
	public void editar() {
		listarTodos();

		System.out.println("Editar autor");
		System.out.println();

		System.out.print("Entre o id do autor: ");
		int id = entrada.nextInt();
		entrada.nextLine();

		Autor autorAModifcar = dao.getByKey(id);

		Autor novoAutor = obtemDadosCliente(autorAModifcar);

		novoAutor.setId(id);

		dao.update(novoAutor);

	}

	@Override
	public void excluir() {
		listarTodos();

		System.out.println("Excluir autor");
		System.out.println();

		System.out.print("Entre o id do autor: ");
		int id = entrada.nextInt();
		entrada.nextLine();

		dao.delete(id);

	}

}