package academy.devdojo.maratonajava.projetoCadastro.test;

import academy.devdojo.maratonajava.projetoCadastro.domain.FilesManagement;
import academy.devdojo.maratonajava.projetoCadastro.domain.UserManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class registerSystemTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File userFile = new File("Formulario.txt");
        File adminFile = new File("LoginsAdmins.txt");
        UserManagement userManagement = new UserManagement(userFile);
        FilesManagement filesManagement = new FilesManagement(userFile,adminFile);
        filesManagement.fileUserCheck();
        filesManagement.clearUserFile();

        //armazenar respostas e concatenar elas

        try {
            filesManagement.writeUserToFile(
                    "1 - Qual seu nome completo?\n" +
                    "2 - Qual seu email de contato?\n" +
                    "3 - Qual sua idade?\n" +
                    "4 - Qual sua altura");
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("----------------------------------");
        System.out.println("Logue com sua conta!");
        System.out.println("1-Admin");
        System.out.println("----------------------------------");
        String newUserResponse = scanner.nextLine();
        while (!newUserResponse.matches("[0-1]")) {
            System.out.println("Apenas 1 como resposta");
            System.out.println("----------------------------------");
            System.out.println("1-Admin");
            System.out.println("----------------------------------");
            newUserResponse = scanner.nextLine();
        }
        if (newUserResponse.equals("1")){
            userManagement.login(newUserResponse);
        }else{

        }

    }
}
