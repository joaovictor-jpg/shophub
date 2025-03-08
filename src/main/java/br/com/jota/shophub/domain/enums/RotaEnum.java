package br.com.jota.shophub.domain.enums;

public enum RotaEnum {
    CLIENTE("/clientes/verificar"),
    FORNECEDOR("/fornecedores/verificar");

    private final String rota;

    RotaEnum(String rota) {
        this.rota = rota;
    }

    public String getRota() {
        return rota;
    }
}
