package academy.devdojo.maratonajava.projetoCadastro.domain;

import java.util.ArrayList;

public class UserInformation {
    private ArrayList<String> response = new ArrayList<>();

    public void formatResponse(String name, String height, String age, String email){
        this.response.add(String.format("1-%s, %s, %s, %s:",name,height,age,email);
    }

    private void saveUserToFile(){
        for (int i = 0; response.size)
    }


    public ArrayList<String> getResponse() {
        return response;
    }
}