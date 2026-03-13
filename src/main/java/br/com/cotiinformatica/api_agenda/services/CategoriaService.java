package br.com.cotiinformatica.api_agenda.services;

import br.com.cotiinformatica.api_agenda.dtos.CategoriaRequest;
import br.com.cotiinformatica.api_agenda.dtos.CategoriaResponse;
import br.com.cotiinformatica.api_agenda.entities.Categoria;
import br.com.cotiinformatica.api_agenda.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /*
        Serviço para realizar o cadastro da categoria
     */
    public CategoriaResponse cadastrar(CategoriaRequest request) {

        //Criando um objeto da classe Categoria
        var categoria = new Categoria();
        categoria.setNome(request.getNome()); //capturando o nome

        /*
            Abaixo estamos gerando um ID aleatório para o campo usuarioId, isso é provisório
            enquanto não temos ainda pronto na API de Agenda a captura do TOKEN JWT e do Id
            do usuário que deverá vir no conteudo do TOKEN
         */
        categoria.setUsuarioId(UUID.randomUUID());

        //Salvar no banco de dados
        categoriaRepository.save(categoria);

        //Retornar a resposta
        return toResponse(categoria);
    }

    /*
        Serviço para realizar a atualização dos dados da categpria
     */
    public CategoriaResponse atualizar(UUID id, CategoriaRequest request) {

        //Buscar a categoria no banco de dados através do ID
        var categoria = categoriaRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada para edição."));

        //Alterar o nome da categoria
        categoria.setNome(request.getNome());

        //Atualizar no banco de dados
        categoriaRepository.save(categoria);

        //Retornar a resposta
        return toResponse(categoria);
    }

    /*
        Serviço para excluir uma categoria no banco de dados
     */
    public CategoriaResponse excluir(UUID id) {

        //Buscar a categoria no banco de dados através do ID
        var categoria = categoriaRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada para exclusão."));

        //Excluir a categoria no banco de dados
        categoriaRepository.delete(categoria);

        //Retornar a resposta
        return toResponse(categoria);
    }

    /*
        Método para consultar as categorias
     */
    public List<CategoriaResponse> consultar() {

        //Consultar todas as categorias do banco de dados
        var categorias = categoriaRepository.findAll();

        //Gerar uma lista de CategoriaResponse copiando os dados de cada categoria
        var response = categorias
                        .stream()
                        .map(this::toResponse)
                        .toList();

        //Retornar a lista
        return response;
    }

    /*
        Serviço para consultar 1 categoria através do ID
     */
    public CategoriaResponse obterPorId(UUID id) {

        //Buscar a categoria no banco de dados através do ID
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));

        //Retornar os dados da categoria encontrada
        return toResponse(categoria);
    }

    /*
        Método privado da classe para copiar as informações
        da entidade Categoria para o DTO CategoriaResponse
     */
    private CategoriaResponse toResponse(Categoria categoria) {
        var response = new CategoriaResponse();
        response.setId(categoria.getId());
        response.setNome(categoria.getNome());

        return response;
    }

}
