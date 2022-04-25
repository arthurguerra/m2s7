package projeto.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Escola {

    @Id
    @GeneratedValue
    @Column(name = "id_escola")
    private Long idEscola;

    private String nome;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Endereco endereco;

    private Date dataCriacao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "escola")
    private List<Turma> turmas;

    public Long getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Long idEscola) {
        this.idEscola = idEscola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
