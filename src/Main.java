import escola.ConexãoBD;
import java.sql.Connection;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int menu(){
        Scanner ler = new Scanner(System.in);
        System.out.println("=====Menu Alunos=====");
        System.out.println("1 => Inserir");
        System.out.println("2 => Listar");
        System.out.println("3 => Atualizar");
        System.out.println("4 => Eliminar");
        System.out.println("5 => Pesquisar por número de processo");
        System.out.println("6 => sair por nome");
        System.out.println("0 => Sair da aplicação");
        System.out.println("==========================================");
        System.out.println("Escolha a sua posição : ");
        return 0;
    }
    public static <SimpleDataFormat> void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Scanner lerString = new Scanner(System.in);
        try{
            System.out.println("Insere o nome do aluno : ");
            String nome = lerString.nextLine();
            System.out.println("Insere o e-email do aluno : ");
            String email = lerString.nextLine();
            System.out.println("Insere a idade do aluno : ");
            int idade = ler.nextInt();
            System.out.println("Insere a data nascimento do aluno(YYYY-MM-DD):");
            String dataNasc = lerString.nextLine();
            //Manipulação das datas
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date data = formato.parse(dataNasc);
            Aluno al = new Aluno(nome, email, idade, data);
            AlunoDAO.inserirAluno(al);

        }catch(ParseException ex){
            System.out.println("Erro na data: "+ex);
        }
    }

   /*public static void atualizar(){
        ALuno al = null;
        Scanner ler = new Scanner(System.in);
        Scanner lerString = new Scanner(System.in);
        System.out.println("Insere o numero de pessoas que pretende atualizar ");
        int num = ler.nextInt();
        al = AlunoDAO.pesquisarNumProcesso(num);
    }*/

    public static void pesquisarNome(){
        Scanner lerString = new  Scanner (System.in);
        System.out.println("Insere o nome qu epretende pesquisar:");
        String nome = lerString.nextLine();
        int contar = 0;

        for(Aluno al : AlunoDAO.pesquisarNome(nome)){
            System.out.println("Numero de processo "+ al.getNumProcesso());
            System.out.println("Nome: "+ al.getNome());
            System.out.println("Email: "+ al.getEmail());
            System.out.println("Idade: "+ al.getIdade());
            System.out.println("Data de nascimento: "+ al.getDataNascimento());
            contar++;
        }
        if(contar == 0){
            System.out.println("Não existe o aluno na base de dados");
        }



    }

}
