package academy.devdojo.maratonajava.projetoCadastro.domain;

import java.util.ArrayList;

public class UserInformation {
    private ArrayList<String> response = new ArrayList<>();
    private FilesManagement filesManagement;
    private UserManagement userManagement;


    public UserInformation(FIlesManagement fIlesManagement, UserManagement userManagement) {
        this.userManagement = userManagement;
        this.filesManagement = filesManagement;
    }

    public saveUserToFile() {
        filesManagement.writeNewUserToFile(this.response);
    }

    public void formatResponse(String name, String height, String age, String email){
        this.response.add(String.format("1-%s, %s, %s, %s:",name,height,age,email);
    }

    public ArrayList<String> getResponse() {
        return response;
    }
}