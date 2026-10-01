import java.util.ArrayList;

public class Box {
    private int numero;
    private String tipoServico;
    private int capacidadeMaxima;
    private String localizacao;
    private Mecanico mecanico;
    private ArrayList<OrdemServico> ordens;

    public Box(int numero, String tipoServico, int capacidadeMaxima, String localizacao) {
        this.numero = numero;
        this.tipoServico = tipoServico;
        this.capacidadeMaxima = capacidadeMaxima;
        this.localizacao = localizacao;
        this.ordens = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public ArrayList<OrdemServico> getOrdens() {
        return ordens;
    }

    public boolean estaCheio() {
        return ordens.size() >= capacidadeMaxima;
    }

    public void adicionarOrdem(OrdemServico ordem) {
        ordens.add(ordem);
    }

    public void removerOrdem(OrdemServico ordem) {
        ordens.remove(ordem);
    }

    @Override
    public String toString() {
        String resp = "Box " + numero + " | Tipo: " + tipoServico + " | Capacidade: " + capacidadeMaxima + " | Local: " + localizacao;
        if (mecanico != null) {
            resp += " | Mecanico: " + mecanico.getNome();
        } else {
            resp += " | Mecanico: nenhum";
        }
        return resp;
    }
}
