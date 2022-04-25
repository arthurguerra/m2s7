package projeto.dto;

import projeto.entity.Turma;

import java.io.Serializable;
import java.util.Date;

public class FiltroTurmaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idTurma;

    private String nome;

    private Long idEstudante;

    private Date dataInicio;

    private Date dataTermino;


    public FiltroTurmaDTO() {
    }

    public FiltroTurmaDTO(Long idTurma, String nome) {
        this.idTurma = idTurma;
        this.nome = nome;
    }

    public FiltroTurmaDTO(Turma turma) {
        this.idTurma = turma.getIdTurma();
        this.nome = turma.getNome();
        this.dataInicio = turma.getDataInicio();
        this.dataTermino = turma.getDataTermino();
    }


    public Long getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(Long idEstudante) {
        this.idEstudante = idEstudante;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

}
