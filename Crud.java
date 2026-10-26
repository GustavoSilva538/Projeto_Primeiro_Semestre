import java.util.Scanner;

public class Crud {

    // Arrays paralelos atuando como nosso "banco de dados" (Sem POO)
    static final int LIMITE = 100;
    static int[] ids = new int[LIMITE];
    static String[] nomes = new String[LIMITE];
    static int[] quantidades = new int[LIMITE];

    static int totalProdutos = 0;
    static int geradorId = 1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("        🔐 TELA DE LOGIN ESTOQUE      ");
        System.out.println("========================================");

        int nivelAcesso = 0; // 1 = Admin, 2 = Funcionário

        // Loop de Autenticação
        while (nivelAcesso == 0) {
            System.out.print("Usuário: ");
            String usuario = input.nextLine();
            System.out.print("Senha: ");
            String senha = input.nextLine();

            if (usuario.equals("admin") && senha.equals("admin123")) {
                nivelAcesso = 1;
                System.out.println("\n[✔] Login bem-sucedido! Bem-vindo, Administrador.");
            } else if (usuario.equals("func") && senha.equals("func123")) {
                nivelAcesso = 2;
                System.out.println("\n[✔] Login bem-sucedido! Bem-vindo, Funcionário.");
            } else {
                System.out.println("\n[X] Usuário ou senha incorretos! Tente novamente.\n");
            }
        }

        int opcao = 0;
        while (opcao != 5) {
            exibirMenu(nivelAcesso);
            opcao = input.nextInt();
            input.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    if (nivelAcesso == 1) {
                        cadastrarProduto(input);
                    } else {
                        System.out.println("\n[X] Acesso Negado! Apenas administradores podem cadastrar produtos.");
                    }
                    break;
                case 2:
                    listarProdutos(); // Ambos têm acesso
                    break;
                case 3:
                    atualizarProduto(input); // Ambos têm acesso (Funcionário pode atualizar estoque)
                    break;
                case 4:
                    if (nivelAcesso == 1) {
                        excluirProduto(input);
                    } else {
                        System.out.println("\n[X] Acesso Negado! Apenas administradores podem excluir produtos.");
                    }
                    break;
                case 5:
                    System.out.println("\n[!] Encerrando o sistema...");
                    break;
                default:
                    System.out.println("\n[X] Opção inválida! Tente novamente.");
            }
        }
        input.close();
    }

    static void exibirMenu(int nivelAcesso) {
        System.out.println("\n========================================");
        System.out.println("  📦 SISTEMA DE GERENCIAMENTO (CRUD)  ");
        System.out.println("========================================");
        if (nivelAcesso == 1) {
            System.out.println(" [1] Cadastrar Novo Produto");
        } else {
            System.out.println(" [1] Cadastrar Novo Produto (BLOQUEADO)");
        }

        System.out.println(" [2] Listar Produtos");
        System.out.println(" [3] Atualizar Produto");

        if (nivelAcesso == 1) {
            System.out.println(" [4] Excluir Produto");
        } else {
            System.out.println(" [4] Excluir Produto (BLOQUEADO)");
        }

        System.out.println(" [5] Sair");
        System.out.println("========================================");
        System.out.print("Escolha uma opção: ");
    }

    static void cadastrarProduto(Scanner input) {
        if (totalProdutos >= LIMITE) {
            System.out.println("\n[X] Limite de produtos atingido!");
            return;
        }

        System.out.println("\n--- 🟢 CADASTRAR PRODUTO ---");
        System.out.print("Nome do Produto: ");
        String nome = input.nextLine();

        System.out.print("Quantidade em Estoque: ");
        int qtd = input.nextInt();

        ids[totalProdutos] = geradorId++;
        nomes[totalProdutos] = nome;
        quantidades[totalProdutos] = qtd;

        totalProdutos++;

        System.out.println("[✔] Produto cadastrado com sucesso!");
    }

    static void listarProdutos() {
        System.out.println("\n--- 📋 LISTA DE PRODUTOS ---");

        if (totalProdutos == 0) {
            System.out.println("[!] Nenhum produto cadastrado.");
            return;
        }

        System.out.println("-------------------------------------------------");
        System.out.printf("%-5s | %-25s | %-10s\n", "ID", "NOME DO PRODUTO", "QUANTIDADE");
        System.out.println("-------------------------------------------------");

        for (int i = 0; i < totalProdutos; i++) {
            System.out.printf("%-5d | %-25s | %-10d\n", ids[i], nomes[i], quantidades[i]);
        }
        System.out.println("-------------------------------------------------");
    }

    static void atualizarProduto(Scanner input) {
        System.out.println("\n--- 🔵 ATUALIZAR PRODUTO ---");
        System.out.print("Informe o ID do produto que deseja atualizar: ");
        int idBusca = input.nextInt();
        input.nextLine(); // Limpar buffer

        int indice = buscarIndicePorId(idBusca);

        if (indice == -1) {
            System.out.println("\n[X] Produto não encontrado!");
            return;
        }

        System.out.print("Novo Nome (Atual: " + nomes[indice] + "): ");
        nomes[indice] = input.nextLine();

        System.out.print("Nova Quantidade (Atual: " + quantidades[indice] + "): ");
        quantidades[indice] = input.nextInt();

        System.out.println("[✔] Produto atualizado com sucesso!");
    }

    static void excluirProduto(Scanner input) {
        System.out.println("\n--- 🔴 EXCLUIR PRODUTO ---");
        System.out.print("Informe o ID do produto que deseja excluir: ");
        int idBusca = input.nextInt();

        int indice = buscarIndicePorId(idBusca);

        if (indice == -1) {
            System.out.println("\n[X] Produto não encontrado!");
            return;
        }

        // Lógica algorítmica clássica: Deslocar todos os elementos uma posição para a esquerda
        for (int i = indice; i < totalProdutos - 1; i++) {
            ids[i] = ids[i + 1];
            nomes[i] = nomes[i + 1];
            quantidades[i] = quantidades[i + 1];
        }

        totalProdutos--; // Reduz o total
        System.out.println("[✔] Produto excluído com sucesso!");
    }

    static int buscarIndicePorId(int id) {
        for (int i = 0; i < totalProdutos; i++) {
            if (ids[i] == id) {
                return i;
            }
        }
        return -1;
    }
}