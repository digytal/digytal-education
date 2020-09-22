package digytal.springdicas.api.enums;

public enum LocalEndereco {
    CASA("CASA"),
    APTO("APARTAMENTO"),
    LOJA("LOJA"),;
    private String nome;
    
    private LocalEndereco(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    

}

