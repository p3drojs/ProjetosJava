package academy.devdojo.maratonajava.projetoCadastro.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManagement {
    private final File userFile;
    private final FilesManagement filesManagement;
    UserInformation userInformation = null;
    private Scanner scanner;

    public UserManagement(File file) {
        this.userFile = file;
        this.filesManagement = new FilesManagement(file);
        this.scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        String response;
        System.out.println("BEM VINDO AO SISTEMA DE CADASTROS!");
        System.out.println("----------------------------------------");
        do {
            System.out.println("1 - Cadastrar novo usuario:");
            System.out.println("2 - Editar usuario:");
            System.out.println("3 - Excluir usuario:");
            System.out.println("4 - Adicionar nova pergunta:");
            System.out.println("5 - Excluir pergunta:");
            response = scanner.nextLine();
            System.out.println("----------------------------------------");
        } while (!response.matches("[1-5]"));

        switch (response) {
            case "1":
                newUserRegister();
                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            case "5":

                break;
        }
    }

    private boolean validate(String name, String password) {
        ArrayList<String> adminsNames = new ArrayList<>();
        ArrayList<String> adminsPasswords = new ArrayList<>();
        filesManagement.getAdminLogins(adminsNames, adminsPasswords);
        for (int i = 0; i < adminsNames.size(); i++) {
            if (adminsNames.get(i).equals(name) && adminsPasswords.get(i).equals(password)) {
                System.out.println("Login bem-sucedido! Bem-vindo, Admin " + name);
                return true;
            }
        }
        System.out.println("Nome de usuário ou senha incorretos!");
        return false;
    }

    public void login(String userResponse) {
        String adminName;
        String adminPassword;
        if (userResponse.equals("1")) {
            System.out.println("Digite seu nome:");
            adminName = scanner.nextLine();
            System.out.println("Digite sua senha:");
            adminPassword = scanner.nextLine();
            if (validate(adminName, adminPassword)) {
                mainMenu();
            }
        } else if (userResponse.equals("0")) {

        }
    }


    private String formatResponse(String prompt, String regex) {
        String response;
        System.out.println(prompt);
        response = scanner.nextLine();

        while (!response.matches(regex)) {
            System.out.println("Entrada inválida! Responda corretamente.");
            System.out.println(prompt);
            response = scanner.nextLine();
        }

        return response;
    }

    private void newUserRegister() {
        ArrayList<String> response = new ArrayList<>();
        String conteudoLinha;

        for (int i = 1; i <= filesManagement.countLines(userFile); i++) {
                conteudoLinha = filesManagement.readUserFileLine(i);
                System.out.println(conteudoLinha);
                response.add(scanner.nextLine());
        }

    }

}
