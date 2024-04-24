import java.util.Date;

public class Aluno {
    private int numProcesso;
    private String nome;
    private String email;
    private int idade;
    private Date dataNascimento;


    public Aluno(int num, String nome, String email, int idade, java.sql.Date data){

    }

    public Aluno(String nome, String email,int idade, Date dataNascimento){
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
    }


    public int getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(int numProcesso) {
        this.numProcesso = numProcesso;
    }

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
