package banco.ui;

import java.util.List;

import banco.dao.ClienteDao;
import banco.dao.ContaDao;
import banco.modelo.Cliente;
import banco.modelo.Livro;

public class InterfaceContaTexto extends InterfaceModeloTexto {

	private ContaDao dao;
	private ClienteDao clienteDao;
	
	public InterfaceContaTexto() {
		super();
		dao = new ContaDao();
		clienteDao = new ClienteDao();
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar conta");
		System.out.println();
		
		Livro novaConta = obtemDadosConta(null);	
		dao.insert(novaConta);
	}

	private Livro obtemDadosConta(Livro conta) {
		System.out.print("Insira o número da conta: ");
		int numero = entrada.nextInt();
		
		System.out.print("Insira o número da agência: ");
		int agencia = entrada.nextInt();
		
		System.out.print("Insira o saldo: ");
		double saldo = entrada.nextDouble();
		
		System.out.print("Insira o ID do cliente: ");
		int idCliente = entrada.nextInt();
		
		Cliente cliente = clienteDao.getByKey(idCliente);
		
		return new Livro(0, agencia, numero, cliente, saldo);
	}

	@Override
	public void listarTodos() {
		List<Livro> contas = dao.getAll();
		
		System.out.println("Lista de contas");
		System.out.println();
		
		System.out.println("id\tAgência\tNúmero\tSaldo\tID do Cliente\tNome do Cliente");
		
		for (Livro conta : contas) {
			imprimeItem(conta);
		}
	}

	@Override
	public void editar() {
		listarTodos();
		
		System.out.println("Editar conta");
		System.out.println();
		
		System.out.print("Entre o id da conta: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		Livro contaAModificar = dao.getByKey(id);
		
		Livro novaConta = obtemDadosConta(contaAModificar);
		
		novaConta.setId(id);
		
		dao.update(novaConta);
	}

	@Override
	public void excluir() {
		listarTodos();
		
		System.out.println("Excluir conta");
		System.out.println();
		
		System.out.print("Entre o id da conta: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		dao.delete(id);
	}

}
