package com.aep5_api.entities.enums;

public enum NivelAcesso {
    ADMIN("A"), COMUM("C");

    private String nivelAcesso;

    private NivelAcesso(String _nivelAcesso) {
        this.nivelAcesso = _nivelAcesso;
    }

    public String getValor() {
        return this.nivelAcesso;
    }

    public static NivelAcesso fromString(String _nivelAcesso) {
        for (NivelAcesso nivel : NivelAcesso.values()) {
            if (nivel.getValor().equalsIgnoreCase(_nivelAcesso)) {
                return nivel;
            }
        }

        throw new IllegalArgumentException("Nivel de acesso inválido: " + _nivelAcesso);
    }
}
