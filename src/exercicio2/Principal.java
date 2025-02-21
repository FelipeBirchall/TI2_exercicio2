package exercicio2;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        XDAO dao = new XDAO();

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println("Lista de Registros:");
                    dao.listar().forEach(x -> 
                        System.out.println(x.getId() + " - " + x.getNome() + ", " + x.getIdade() + " anos"));
                    break;
                case 2:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    dao.inserir(new X(nome, idade));
                    break;
                case 3:
                    System.out.print("ID do registro a atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Nova idade: ");
                    int novaIdade = scanner.nextInt();
                    dao.atualizar(new X(novoNome, novaIdade) {{ setId(idAtualizar); }});
                    break;
                case 4:
                    System.out.print("ID do registro a excluir: ");
                    int idExcluir = scanner.nextInt();
                    dao.excluir(idExcluir);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
