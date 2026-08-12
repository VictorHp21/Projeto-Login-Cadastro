package com.victor.login_cad.Entities;

import com.victor.login_cad.Enums.Departamento;
import jakarta.persistence.*;

@Entity
@Table(name = "Funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer idade;

    @Enumerated(EnumType.STRING)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    public Funcionario(){}

    public Funcionario(String nome, Integer idade, Departamento departamento, Usuarios usuario) {
        this.nome = nome;
        this.idade = idade;
        this.departamento = departamento;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
