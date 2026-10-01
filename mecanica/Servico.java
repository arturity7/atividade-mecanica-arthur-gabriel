public class Servico {
    private String nome;
    private double tempoEstimado;
    private double valor;
    private String categoria;

    public Servico(String nome, double tempoEstimado, double valor, String categoria) {
        this.nome = nome;
        this.tempoEstimado = tempoEstimado;
        this.valor = valor;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public double getTempoEstimado() {
        return tempoEstimado;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return nome + " | Tempo estimado: " + tempoEstimado + "h | Valor: R$ " + valor + " | Categoria: " + categoria;
    }
}
