public class OrdemServico {
    public static final String ABERTA = "Aberta";
    public static final String EM_EXECUCAO = "Em execucao";
    public static final String FINALIZADA = "Finalizada";

    private int codigo;
    private String nomeCliente;
    private String modeloVeiculo;
    private String placa;
    private String data;
    private String status;
    private double valorEstimado;
    private Servico servico;
    private Box box;

    public OrdemServico(int codigo, String nomeCliente, String modeloVeiculo, String placa, String data, double valorEstimado, Servico servico) {
        this.codigo = codigo;
        this.nomeCliente = nomeCliente;
        this.modeloVeiculo = modeloVeiculo;
        this.placa = placa;
        this.data = data;
        this.valorEstimado = valorEstimado;
        this.servico = servico;
        this.status = ABERTA;
        this.box = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public double getValorEstimado() {
        return valorEstimado;
    }

    public Servico getServico() {
        return servico;
    }

    public Box getBox() {
        return box;
    }

    public void atribuirBox(Box box) {
        this.box = box;
        this.status = EM_EXECUCAO;
        box.adicionarOrdem(this);
    }

    public void finalizar() {
        this.status = FINALIZADA;
        box.removerOrdem(this);
    }

    public String resumo() {
        return "Ordem " + codigo + " | Cliente: " + nomeCliente + " | Veiculo: " + modeloVeiculo + " | Placa: " + placa + " | Status: " + status;
    }

    public void exibirDetalhes() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Veiculo: " + modeloVeiculo);
        System.out.println("Placa: " + placa);
        System.out.println("Data: " + data);
        System.out.println("Status: " + status);
        System.out.println("Valor estimado: R$ " + valorEstimado);
        System.out.println("Servico: " + servico);
        if (box != null) {
            System.out.println("Box: " + box.getNumero() + " - " + box.getLocalizacao());
            if (box.getMecanico() != null) {
                System.out.println("Mecanico: " + box.getMecanico());
            } else {
                System.out.println("Mecanico: nenhum");
            }
        } else {
            System.out.println("Box: nao atribuido");
        }
    }
}
