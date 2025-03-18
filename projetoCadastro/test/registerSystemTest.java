package projetoCadastro.test;

import projetoCadastro.domain.FilesManagement;
import projetoCadastro.domain.UserInformation;
import projetoCadastro.domain.UserManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class registerSystemTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File userFile = new File("Formulario.txt");
        File adminFile = new File("LoginsAdmins.txt");

        UserManagement userManagement = new UserManagement(userFile, null);
        FilesManagement filesManagement = new FilesManagement(userFile,adminFile, null);
        UserInformation userInformation = new UserInformation(filesManagement, userManagement);

        filesManagement.setUserInformation(userInformation);
        userManagement.setUserInformation(userInformation);

        filesManagement.fileUserCheck();
        filesManagement.clearUserFile();

        try {
            filesManagement.writeUserQuestionsToFile(
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