/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.services;

import br.siqueira.medi.connect.models.Especialidade;

/**
 *
 * @author eduar
 */
public class EspecialidadeService {
    public void validateUpdateEspecialidade(Especialidade especialidade) {
        if (especialidade.getId() != 0) {
            throw new IllegalArgumentException("Especialidade não pode ser alterada!");
        }

        if (!especialidade.getDescricao().isEmpty()||
                !especialidade.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Especialidade não pode ser alterada!");
        }
    }
}
