package projeto.dto;

import projeto.entity.Escola;
import projeto.entity.Turma;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EscolaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEscola;

    private String nome;

    private EnderecoDTO enderecoDTO = new EnderecoDTO();

    private Date dataCriacao;

    private List<Long> turmas;


    public EscolaDTO() {
    }

    public EscolaDTO(Long idEscola, String nome) {
        this.idEscola = idEscola;
        this.nome = nome;
    }

    public EscolaDTO(Escola escola) {
        this.idEscola = escola.getIdEscola();
        this.nome = escola.getNome();
        this.enderecoDTO = new EnderecoDTO(escola.getEndereco());
        this.dataCriacao = escola.getDataCriacao();
        this.turmas = escola.getTurmas()
                .stream()
                .map(Turma::getIdTurma)
                .collect(Collectors.toList());
    }


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

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Long> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Long> turmas) {
        this.turmas = turmas;
    }
}
