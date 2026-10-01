import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Oficina oficina = new Oficina();

    public static void main(String[] args) {
        criarDadosIniciais();
        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("===== OFICINA MECANICA =====");
            System.out.println("1 - Cadastrar ordem de servico");
            System.out.println("2 - Associar mecanico a um box");
            System.out.println("3 - Atribuir ordem de servico a um box");
            System.out.println("4 - Exibir ordens de um box");
            System.out.println("5 - Total de ordens finalizadas por box");
            System.out.println("6 - Buscar ordens por status");
            System.out.println("7 - Exibir detalhes de uma ordem");
            System.out.println("8 - Finalizar ordem de servico");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            opcao = lerInt();

            switch (opcao) {
                case 1:
                    cadastrarOrdem();
                    break;
                case 2:
                    associarMecanico();
                    break;
                case 3:
                    atribuirOrdem();
                    break;
                case 4:
                    exibirOrdensBox();
                    break;
                case 5:
                    finalizadasPorBox();
                    break;
                case 6:
                    buscarPorStatus();
                    break;
                case 7:
                    detalhesOrdem();
                    break;
                case 8:
                    finalizarOrdem();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    public static void criarDadosIniciais() {
        Mecanico m1 = new Mecanico("Carlos Silva", "111", "Motor", "31 99999-1111");
        Mecanico m2 = new Mecanico("Joao Souza", "222", "Freios", "31 99999-2222");
        Mecanico m3 = new Mecanico("Pedro Santos", "333", "Eletrica", "31 99999-3333");
        oficina.adicionarMecanico(m1);
        oficina.adicionarMecanico(m2);
        oficina.adicionarMecanico(m3);

        Box b1 = new Box(1, "Motor", 3, "Galpao A");
        Box b2 = new Box(2, "Freios", 2, "Galpao A");
        Box b3 = new Box(3, "Eletrica", 2, "Galpao B");
        oficina.adicionarBox(b1);
        oficina.adicionarBox(b2);
        oficina.adicionarBox(b3);
    }

    public static int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um numero valido: ");
            }
        }
    }

    public static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Digite um valor valido: ");
            }
        }
    }

    public static void listarBoxes() {
        for (Box b : oficina.getBoxes()) {
            System.out.println(b);
        }
    }

    public static void listarMecanicos() {
        for (Mecanico m : oficina.getMecanicos()) {
            System.out.println(m);
        }
    }

    public static void listarOrdens() {
        for (OrdemServico o : oficina.getOrdens()) {
            System.out.println(o.resumo());
        }
    }

    public static void cadastrarOrdem() {
        System.out.print("Nome do cliente: ");
        String cliente = sc.nextLine();
        System.out.print("Modelo do veiculo: ");
        String modelo = sc.nextLine();
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Data (dd/mm/aaaa): ");
        String data = sc.nextLine();
        System.out.print("Valor estimado: ");
        double valor = lerDouble();

        System.out.println("-- Dados do servico --");
        System.out.print("Nome do servico: ");
        String nomeServico = sc.nextLine();
        System.out.print("Tempo estimado (horas): ");
        double tempo = lerDouble();
        System.out.print("Valor do servico: ");
        double valorServico = lerDouble();
        System.out.print("Categoria (Motor, Freios, Eletrica...): ");
        String categoria = sc.nextLine();

        Servico servico = new Servico(nomeServico, tempo, valorServico, categoria);
        OrdemServico ordem = oficina.cadastrarOrdem(cliente, modelo, placa, data, valor, servico);
        System.out.println("Ordem cadastrada com codigo " + ordem.getCodigo() + ".");
    }

    public static void associarMecanico() {
        System.out.println("Mecanicos:");
        listarMecanicos();
        System.out.print("CPF do mecanico: ");
        String cpf = sc.nextLine();
        System.out.println("Boxes:");
        listarBoxes();
        System.out.print("Numero do box: ");
        int numero = lerInt();
        System.out.println(oficina.associarMecanico(cpf, numero));
    }

    public static void atribuirOrdem() {
        ArrayList<OrdemServico> abertas = oficina.buscarPorStatus(OrdemServico.ABERTA);
        if (abertas.isEmpty()) {
            System.out.println("Nao ha ordens abertas.");
            return;
        }
        System.out.println("Ordens abertas:");
        for (OrdemServico o : abertas) {
            System.out.println(o.resumo() + " | Categoria: " + o.getServico().getCategoria());
        }
        System.out.print("Codigo da ordem: ");
        int codigo = lerInt();
        System.out.println("Boxes:");
        listarBoxes();
        System.out.print("Numero do box: ");
        int numero = lerInt();
        System.out.println(oficina.atribuirOrdem(codigo, numero));
    }

    public static void exibirOrdensBox() {
        listarBoxes();
        System.out.print("Numero do box: ");
        int numero = lerInt();
        Box b = oficina.buscarBox(numero);
        if (b == null) {
            System.out.println("Box nao encontrado.");
            return;
        }
        System.out.println("Ordens do box " + b.getNumero() + ":");
        for (OrdemServico o : b.getOrdens()) {
            System.out.println(o.resumo());
        }
        System.out.println("Total de ordens: " + b.getOrdens().size());
    }

    public static void finalizadasPorBox() {
        for (Box b : oficina.getBoxes()) {
            System.out.println("Box " + b.getNumero() + ": " + oficina.contarFinalizadas(b) + " ordem(ns) finalizada(s)");
        }
    }

    public static void buscarPorStatus() {
        System.out.println("1 - Aberta");
        System.out.println("2 - Em execucao");
        System.out.println("3 - Finalizada");
        System.out.print("Status: ");
        int op = lerInt();
        String status;
        if (op == 1) {
            status = OrdemServico.ABERTA;
        } else if (op == 2) {
            status = OrdemServico.EM_EXECUCAO;
        } else if (op == 3) {
            status = OrdemServico.FINALIZADA;
        } else {
            System.out.println("Opcao invalida.");
            return;
        }
        ArrayList<OrdemServico> lista = oficina.buscarPorStatus(status);
        if (lista.isEmpty()) {
            System.out.println("Nenhuma ordem encontrada.");
            return;
        }
        for (OrdemServico o : lista) {
            System.out.println("--------------------");
            o.exibirDetalhes();
        }
    }

    public static void detalhesOrdem() {
        listarOrdens();
        System.out.print("Codigo da ordem: ");
        int codigo = lerInt();
        OrdemServico o = oficina.buscarOrdem(codigo);
        if (o == null) {
            System.out.println("Ordem nao encontrada.");
            return;
        }
        o.exibirDetalhes();
    }

    public static void finalizarOrdem() {
        ArrayList<OrdemServico> emExecucao = oficina.buscarPorStatus(OrdemServico.EM_EXECUCAO);
        if (emExecucao.isEmpty()) {
            System.out.println("Nao ha ordens em execucao.");
            return;
        }
        for (OrdemServico o : emExecucao) {
            System.out.println(o.resumo());
        }
        System.out.print("Codigo da ordem: ");
        int codigo = lerInt();
        System.out.println(oficina.finalizarOrdem(codigo));
    }
}
