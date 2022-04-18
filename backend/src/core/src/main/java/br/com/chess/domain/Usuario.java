package br.com.chess.domain;

import br.com.chess.domain.enums.SituacaoEmail;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
@Table(name = "usuario")
public class Usuario extends BaseDomain {


    @Column(name = "nome", nullable = false, unique = false, length = 128)
    private String nome;

    @Column(name = "sobrenome", nullable = true, unique = false, length = 128)
    private String sobrenome;


    @Column(name = "email", nullable = false, unique = true, length = 128)
    private String email;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = false;

    @Column(name = "hash_senha", nullable = false, length = 128)
    private String hashSenha;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @Column(name = "cpf", nullable = true, unique = true)
    private String cpf;

    @Column(name = "cep", nullable = true)
    private String cep;

    @Column(name = "numero", nullable = true, length = 128)
    private String numero;

    @Column(name = "logradouro", nullable = true)
    private String logradouro;

    @Column(name = "bairro", nullable = true)
    private String bairro;

    @OneToOne
    @JoinColumn(name = "cidade_id", nullable = true, foreignKey = @ForeignKey(name = "fk_usuario_municio"))
    private Municipio cidade;

    @Column(name = "referencia", nullable = true)
    private String referencia;

    @Column(name = "complemento", nullable = true)
    private String complemento;

    @Column(name = "whatsapp", nullable = true)
    private String whatsapp;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_email", nullable = true)
    private SituacaoEmail situacaoEmail;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Municipio getCidade() {
        return cidade;
    }

    public void setCidade(Municipio cidade) {
        this.cidade = cidade;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public SituacaoEmail getSituacaoEmail() {
        return situacaoEmail;
    }

    public void setSituacaoEmail(SituacaoEmail situacaoEmail) {
        this.situacaoEmail = situacaoEmail;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Boolean isAdmin() {
        for (Permissao perm : perfil.getPermissoes()) {
            if (perm.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
