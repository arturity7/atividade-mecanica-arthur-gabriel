import java.util.ArrayList;

public class Oficina {
    private ArrayList<Mecanico> mecanicos;
    private ArrayList<Box> boxes;
    private ArrayList<OrdemServico> ordens;
    private int proximoCodigo;

    public Oficina() {
        mecanicos = new ArrayList<>();
        boxes = new ArrayList<>();
        ordens = new ArrayList<>();
        proximoCodigo = 1;
    }

    public void adicionarMecanico(Mecanico m) {
        mecanicos.add(m);
    }

    public void adicionarBox(Box b) {
        boxes.add(b);
    }

    public ArrayList<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public ArrayList<OrdemServico> getOrdens() {
        return ordens;
    }

    public OrdemServico cadastrarOrdem(String cliente, String modelo, String placa, String data, double valor, Servico servico) {
        OrdemServico ordem = new OrdemServico(proximoCodigo, cliente, modelo, placa, data, valor, servico);
        ordens.add(ordem);
        proximoCodigo++;
        return ordem;
    }

    public Mecanico buscarMecanico(String cpf) {
        for (Mecanico m : mecanicos) {
            if (m.getCpf().equals(cpf)) {
                return m;
            }
        }
        return null;
    }

    public Box buscarBox(int numero) {
        for (Box b : boxes) {
            if (b.getNumero() == numero) {
                return b;
            }
        }
        return null;
    }

    public OrdemServico buscarOrdem(int codigo) {
        for (OrdemServico o : ordens) {
            if (o.getCodigo() == codigo) {
                return o;
            }
        }
        return null;
    }

    public boolean mecanicoTemBox(Mecanico m) {
        for (Box b : boxes) {
            if (b.getMecanico() == m) {
                return true;
            }
        }
        return false;
    }

    public String associarMecanico(String cpf, int numeroBox) {
        Mecanico m = buscarMecanico(cpf);
        Box b = buscarBox(numeroBox);
        if (m == null) {
            return "Mecanico nao encontrado.";
        }
        if (b == null) {
            return "Box nao encontrado.";
        }
        if (mecanicoTemBox(m)) {
            return "Esse mecanico ja e responsavel por um box.";
        }
        b.setMecanico(m);
        return "Mecanico " + m.getNome() + " associado ao box " + b.getNumero() + ".";
    }

    public String atribuirOrdem(int codigo, int numeroBox) {
        OrdemServico o = buscarOrdem(codigo);
        Box b = buscarBox(numeroBox);
        if (o == null) {
            return "Ordem nao encontrada.";
        }
        if (b == null) {
            return "Box nao encontrado.";
        }
        if (!o.getStatus().equals(OrdemServico.ABERTA)) {
            return "Somente ordens abertas podem ser atribuidas a um box.";
        }
        if (b.getMecanico() == null) {
            return "Esse box nao possui mecanico responsavel.";
        }
        if (!b.getTipoServico().equalsIgnoreCase(o.getServico().getCategoria())) {
            return "O box so aceita servicos do tipo " + b.getTipoServico() + ".";
        }
        if (b.estaCheio()) {
            return "O box esta com a capacidade maxima.";
        }
        o.atribuirBox(b);
        return "Ordem " + o.getCodigo() + " atribuida ao box " + b.getNumero() + ".";
    }

    public String finalizarOrdem(int codigo) {
        OrdemServico o = buscarOrdem(codigo);
        if (o == null) {
            return "Ordem nao encontrada.";
        }
        if (!o.getStatus().equals(OrdemServico.EM_EXECUCAO)) {
            return "Somente ordens em execucao podem ser finalizadas.";
        }
        o.finalizar();
        return "Ordem " + o.getCodigo() + " finalizada.";
    }

    public int contarFinalizadas(Box b) {
        int total = 0;
        for (OrdemServico o : ordens) {
            if (o.getBox() == b && o.getStatus().equals(OrdemServico.FINALIZADA)) {
                total++;
            }
        }
        return total;
    }

    public ArrayList<OrdemServico> buscarPorStatus(String status) {
        ArrayList<OrdemServico> lista = new ArrayList<>();
        for (OrdemServico o : ordens) {
            if (o.getStatus().equals(status)) {
                lista.add(o);
            }
        }
        return lista;
    }
}
