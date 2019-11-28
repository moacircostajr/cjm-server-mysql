package tech.moacircosta.api.cursos.service;


import tech.moacircosta.api.cursos.model.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {

    public Boolean registreEmpresa(Empresa empresa);
    public List<Empresa> busqueEmpresa(String nomeFantasia);
    public Optional<Empresa> busqueEmpresaPorId(Integer idEmpresa);
}
