package rhexa.rhexus.pkgPedido;

public class Pedido {
    public long id;
    public String situacao;
    public String operacao;
    public long pessoaID;
    public String emissao;

    public static final String ID = "id";
    public static final String SITUACAO = "situacao";
    public static final String OPERACAO = "operaca";
    public static final String PESSOAID = "pessoaId";
    public static final String EMISSAO = "emissao";
    public static final String TABELA = "pedido";
    public static final String[] COLUNAS = {ID,SITUACAO,OPERACAO,PESSOAID,EMISSAO};

    public Pedido() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public long getPessoaID() {
        return pessoaID;
    }

    public void setPessoaID(long pessoaID) {
        this.pessoaID = pessoaID;
    }

    public String getEmissao() {
        return emissao;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

}
