package projetoCadastro.domain;

import java.io.*;
import java.util.ArrayList;

public class FilesManagement {
    private static File userFile;
    private static File adminFile;
    private static File userInformationFile;
    private UserInformation userInformation;


    public FilesManagement(File file, File adminFile, UserInformation userInformation) {
        userFile = file;
        FilesManagement.adminFile = adminFile;
        this.userInformation = userInformation;
        writeBaseAdminLogin();
    }

    public FilesManagement(File file, UserInformation userInformation) {
        userFile = file;
        this.userInformation = userInformation;
    }


    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public void clearAdminFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(adminFile))) {
            bufferedWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAdminToFile(String adminName, String adminPassword) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(adminFile, true));
        String formattedContent = String.format("Admin:%s - Senha:%s", adminName, adminPassword);
        bufferedWriter.write(formattedContent);
        bufferedWriter.newLine();
    }

    private boolean adminLoginExists() {
        if (!adminFile.exists()) {
            return false;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(adminFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("Admin:Admin - Senha:1234")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeBaseAdminLogin() {
        if (adminLoginExists()) {
            return;
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(adminFile, true))) {
            bufferedWriter.write("Admin:Admin - Senha:1234");
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAdminLogins(ArrayList<String> adminNames, ArrayList<String> adminPasswords) {
        if (!adminFile.exists()) {
            return;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(adminFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("Admin:") && line.contains(" - Senha:")) {
                    String[] parts = line.split(" - Senha:");
                    if (parts.length == 2) {
                        adminNames.add(parts[0].replace("Admin:", "").trim());
                        adminPasswords.add(parts[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readAdmin() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(adminFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    //ADMIN
    //USER

    private void createUserRegisterFile(String archiveName){
        userInformationFile = new File(archiveName + ".txt");
        if (!userInformationFile.exists()) {
            try {
                userInformationFile.createNewFile();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    public void writeNewUserToFile(String response){
        createUserRegisterFile("pedro");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(userInformationFile, true))){
            bufferedWriter.write(response);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void fileUserCheck() {
        if (!userFile.exists()) {
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkQuestionsInFile(String content){
        if (!userFile.exists()) {
            return false;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase(content.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void writeUserQuestionsToFile(String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(userFile, true));
        if (checkQuestionsInFile(content)) return;
        else{
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }

    }

    public int countLines(File file) {
        int lines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public String readUserFileLine(int lineNumber) {
        if (!userFile.exists()) {
            System.out.println("Arquivo de usuários não encontrado.");
            return null;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile))) {
            String line;
            int currentLine = 1;

            while ((line = bufferedReader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    return line;
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("A linha " + lineNumber + " não foi encontrada no arquivo.");
        return null;
    }


    public void readUserFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    public void clearUserFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(userFile))) {
            bufferedWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}