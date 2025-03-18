package projetoCadastro.domain;

import java.util.ArrayList;

public class UserInformation {
    private ArrayList<String> response = new ArrayList<>();
    private FilesManagement filesManagement;
    private UserManagement userManagement;


    public UserInformation(FilesManagement filesManagement, UserManagement userManagement) {
        this.userManagement = userManagement;
        this.filesManagement = filesManagement;
    }

    public void saveUserToFile() {
        for (int i = 0; i < response.size(); i++) {
            filesManagement.writeNewUserToFile(response.get(i));
        }
    }

    public void formatResponse(ArrayList<ArrayList<String>> respostas){
        for (int i = 0; i < respostas.size(); i++) {
            ArrayList<String> respostaAtual = respostas.get(i);
            String respostaFormatada = (i + 1) + " - " + String.join(", ", respostaAtual) + ":";
            this.response.add(respostaFormatada);
            saveUserToFile();
        }
    }

    public ArrayList<String> getResponse() {
        return response;
    }
}