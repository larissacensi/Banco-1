package banco.ui;

import java.util.Scanner;

public class InterfaceTexto {
	private Scanner entrada;
	private Estado estadoAtual;

	private static final int OP_LIVRO = 1;
	private static final int OP_AUTOR = 2;
	private static final int OP_SAIR = 0;
	private static final int OP_NOVO = 1;
	private static final int OP_EDITAR = 2;
	private static final int OP_APAGAR = 3;
	private static final int OP_BUSCAR = 4;

	private enum Estado {
		PRINCIPAL, LIVRO, AUTOR
	};

	public InterfaceTexto() {
		entrada = new Scanner(System.in);
		estadoAtual = Estado.PRINCIPAL;
	}

	private void imprimeMenu() {
		System.out.println("");
		System.out.println();

		switch (estadoAtual) {
		case LIVRO:
			imprimeMenuLivros();
			break;
		case AUTOR:
			imprimeMenuAutores();
			break;
		default:
			imprimeMenuPrincipal();
		}
		System.out.println();
		System.out.println("0 - Sair");

		System.out.println();
		System.out.print("Escolha uma opção: ");
	}

	private int leOpcao() {
		int opcao = entrada.nextInt();
		entrada.nextLine();
		return opcao;
	}

	private void imprimeMenuPrincipal() {
		System.out.println("1 - Administração de Livros");
		System.out.println("2 - Administração de Autores");
	}

	private void imprimeMenuLivros() {
		System.out.println("1 - Adicionar Livro");
		System.out.println("2 - Editar Livro");
		System.out.println("3 - Apagar Livro");
		System.out.println("4 - Listar Livro");
	}

	private void imprimeMenuAutores() {
		System.out.println("1 - Adicionar Autor");
		System.out.println("2 - Editar Autor");
		System.out.println("3 - Apagar Autor");
		System.out.println("4 - Listar Autor");
	}

	public void executa() {
		InterfaceModeloTexto subMenu;

		imprimeMenu();
		int opcao = leOpcao();

		while (opcao != OP_SAIR) {
			if (estadoAtual == Estado.PRINCIPAL) {
				estadoAtual = opcao == OP_LIVRO ? Estado.LIVRO : Estado.AUTOR;
			} else {
				subMenu = estadoAtual == Estado.LIVRO ? new InterfaceClienteTexto() : new InterfaceContaTexto();

				switch (opcao) {
				case OP_NOVO:
					subMenu.adicionar();
					break;
				case OP_EDITAR:
					subMenu.editar();
					break;
				case OP_APAGAR:
					subMenu.excluir();
					break;
				case OP_BUSCAR:
					subMenu.listarTodos();
					break;
				default:
					System.out.println("Opção invalida. Tente novamente!");
				}
			}

			imprimeMenu();
			opcao = leOpcao();
		}

	}

}
