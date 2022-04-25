package projeto.business;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EscolaDTO;
import projeto.dto.EstudanteDTO;
import projeto.entity.Endereco;
import projeto.entity.Escola;
import projeto.entity.Estudante;
import projeto.entity.Turma;
import projeto.exception.BusinessException;
import projeto.repository.EscolaRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EscolaBusiness {

    @Inject
    private EscolaRepository escolaRepository;

    public void cadastrar(EscolaDTO escolaDTO) throws BusinessException {
        validarCadastrar(escolaDTO);
        cadastrarEscolaNoBanco(escolaDTO);
    }

    private void cadastrarEscolaNoBanco(EscolaDTO escolaDTO) throws BusinessException {
        Escola escola;
        if (escolaDTO.getIdEscola() != null) {
            escola = escolaRepository.find(Escola.class, escolaDTO.getIdEscola());
            if (escola == null) {
                throw new BusinessException("Escola não encontrada.");
            }
        } else {
            escola = new Escola();
        }

        escola.setNome(escolaDTO.getNome());
        escola.setEndereco(new Endereco(escolaDTO.getEnderecoDTO()));
        escola.setDataCriacao(escolaDTO.getDataCriacao());

        List<Turma> turmas = escolaDTO.getTurmas()
                .stream()
                .map(id -> escolaRepository.find(Turma.class, id))
                .collect(Collectors.toList());

        if (turmas.isEmpty()) {
            throw new BusinessException("Turma(s) não encontrada(s)");
        }

        escola.setTurmas(turmas);

        for (Turma turma : turmas) {
            turma.setEscola(escola);
        }

        escola.setEndereco(new Endereco(escolaDTO.getEnderecoDTO()));

        if (escola.getIdEscola() != null) {
            escolaRepository.merge(escola);
        } else {
            escolaRepository.persist(escola);
            escolaDTO.setIdEscola(escola.getIdEscola());
        }
    }

    private void validarCadastrar(EscolaDTO escolaDTO) throws BusinessException {
        List<String> erros = new ArrayList<>();

        if (StringUtils.isBlank(escolaDTO.getNome())) {
            erros.add("O nome da escola é inválido.");
        }

        if (escolaDTO.getDataCriacao() == null) {
            erros.add("A data de criação é inválida.");
        }

        if (escolaDTO.getTurmas().isEmpty()) {
            erros.add("Selecione ao menos uma turma.");
        }

        if (StringUtils.isBlank(escolaDTO.getEnderecoDTO().getRua())) {
            erros.add("A rua do endereço é inválida.");
        }
        if (StringUtils.isBlank(escolaDTO.getEnderecoDTO().getNumero())) {
            erros.add("O número do endereço é inválido.");
        }
        if (StringUtils.isBlank(escolaDTO.getEnderecoDTO().getBairro())) {
            erros.add("O bairro do endereço é inválido.");
        }
        if (StringUtils.isBlank(escolaDTO.getEnderecoDTO().getCidade())) {
            erros.add("A cidade do endereço é inválida.");
        }
        if (StringUtils.isBlank(escolaDTO.getEnderecoDTO().getEstado())) {
            erros.add("O estado do endereço é inválido.");
        }
        if (StringUtils.isBlank(escolaDTO.getEnderecoDTO().getPais())) {
            erros.add("O país do endereço é inválido.");
        }

        if (!erros.isEmpty()) {
            throw new BusinessException(erros);
        }
    }

    public EscolaDTO consultarDadosEscola(Long idEscola) throws BusinessException {
        Escola escola = escolaRepository.find(Escola.class, idEscola);
        if (escola == null) {
            throw new BusinessException("Escola não encontrada através do ID " + idEscola);
        }

        return new EscolaDTO(escola);
    }
}
