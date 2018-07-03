package rhexa.rhexus.pkgPessoa;

public class Pessoa {
    private Long id;
    private String nome;
    private String tipo;
    private String cpfcnpj;
    private String endereco;
    private String telefone;

    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String TIPO = "tipo";
    public static final String CPFCNPJ = "cpfcnpj";
    public static final String ENDERECO = "endereco";
    public static final String TELEFONE = "telefone";
    public static final String TABELA = "pessoa";
    public static final String[] COLUNAS = {ID,NOME,TIPO,CPFCNPJ,ENDERECO,TELEFONE};

    public Pessoa() { }

    public Long getId() { return id;  }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) {  this.nome = nome; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCpfcnpj() { return cpfcnpj; }

    public void setCpfcnpj(String cpfcnpj) { this.cpfcnpj = cpfcnpj;    }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return id + nome + tipo + cpfcnpj +  endereco + telefone;
    }
}
