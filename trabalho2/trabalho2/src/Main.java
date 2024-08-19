import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Criando instâncias dos DAOs
        ProjetoDAO projetoDAO = new ProjetoDAO();
        EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
        OperarioDAO operarioDAO = new OperarioDAO();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        MaterialDAO materialDAO = new MaterialDAO();

        // Exemplo de criação e inserção de um novo projeto
        Projeto projeto = new Projeto();
        projeto.setNomeProjeto("Construção de Ponte");
        projeto.setLocal("São Paulo");
        projeto.setDataInicio(new Date());
        projeto.setDataTermino(new Date());
        projetoDAO.inserir(projeto);

        // Exemplo de criação e inserção de um novo engenheiro
        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setNomeEngenheiro("Carlos Silva");
        engenheiro.setEspecialidade("Engenheiro Civil");
        engenheiroDAO.inserir(engenheiro);

        // Exemplo de criação e inserção de um novo operário
        Operario operario = new Operario();
        operario.setNomeOperario("José Santos");
        operario.setFuncao("Pedreiro");
        operarioDAO.inserir(operario);

        // Exemplo de criação e inserção de um novo equipamento
        Equipamento equipamento = new Equipamento();
        equipamento.setNomeEquipamento("Guindaste");
        equipamento.setTipo("Pesado");
        equipamentoDAO.inserir(equipamento);

        // Exemplo de criação e inserção de um novo material
        Material material = new Material();
        material.setNomeMaterial("Cimento");
        material.setQuantidade(1000);
        materialDAO.inserir(material);

        // Exemplo de listagem de projetos
        for (Projeto p : projetoDAO.listar()) {
            System.out.println("Projeto: " + p.getNomeProjeto() + " em " + p.getLocal());
        }

        // Exemplo de listagem de engenheiros
        for (Engenheiro e : engenheiroDAO.listar()) {
            System.out.println("Engenheiro: " + e.getNomeEngenheiro() + ", Especialidade: " + e.getEspecialidade());
        }

        // Exemplo de listagem de operários
        for (Operario o : operarioDAO.listar()) {
            System.out.println("Operário: " + o.getNomeOperario() + ", Função: " + o.getFuncao());
        }

        // Exemplo de listagem de equipamentos
        for (Equipamento eq : equipamentoDAO.listar()) {
            System.out.println("Equipamento: " + eq.getNomeEquipamento() + ", Tipo: " + eq.getTipo());
        }

        // Exemplo de listagem de materiais
        for (Material m : materialDAO.listar()) {
            System.out.println("Material: " + m.getNomeMaterial() + ", Quantidade: " + m.getQuantidade());
        }
    }
}
