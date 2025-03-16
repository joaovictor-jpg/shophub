package br.com.jota.shophub.domain.interfaces;

import br.com.jota.shophub.domain.enums.RotaEnum;

public interface EntidadeComEmail {
    String getNome();
    String getUsername();
    Long getId();
    RotaEnum getRotaVerificacao();
}
