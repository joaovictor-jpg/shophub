package br.com.jota.shophub.dtos.endereco;

public record AtualizarDadosEndereco(
        String logradouro,
        Integer numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String cep) {

}
