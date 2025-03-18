package projetoCadastro.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class UserManagement {
    private final File userFile;
    private final FilesManagement filesManagement;
    private UserInformation userInformation;
    private Scanner scanner;
    private ArrayList<String> formattedResponse = new ArrayList<>();

    public UserManagement(File file, UserInformation userInformation) {
        this.userFile = file;
        this.filesManagement = new FilesManagement(file, userInformation);
        this.userInformation = userInformation;
        this.scanner = new Scanner(System.in);
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
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


    private boolean formatResponse(String prompt, String regex, String ask) {
        if (prompt.matches(regex)){
            return true;
        }

        String response;
        System.out.println(ask);
        response = scanner.nextLine();

        while (!response.matches(regex)) {
            System.out.println(ask);
            response = scanner.nextLine();
        }

        return true;
    }

    private void newUserRegister() {
        ArrayList<String> perguntas = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                perguntas.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        for (String pergunta : perguntas) {
            System.out.println(pergunta);
            String resposta = scanner.nextLine().trim();

            while (resposta.isEmpty()) {
                System.out.println("Resposta não pode estar vazia! Digite novamente:");
                resposta = scanner.nextLine().trim();
            }

            formattedResponse.add(resposta);
        }


        formatResponse(formattedResponse.get(1), "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", "Email Invalido!, Digite Novamente!");
        formatResponse(formattedResponse.get(2), "^[0-9]+$", "Idade invalida, Digite somente Numeros!");
        formatResponse(formattedResponse.get(3), "^[0-9]\\.\\d{2}$", "Altura invalida, Digite nesse formato x.xx!");

        ArrayList<ArrayList<String>> wrappedResponse = new ArrayList<>();
        wrappedResponse.add(formattedResponse);

        userInformation.formatResponse(wrappedResponse);

        mainMenu();
    }

    public ArrayList<String> getFormattedResponse() {
        return formattedResponse;
    }
}