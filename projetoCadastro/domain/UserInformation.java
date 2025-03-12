package academy.devdojo.maratonajava.projetoCadastro.domain;

import java.util.ArrayList;

public class UserInformation {
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> email = new ArrayList<>();
    private ArrayList<Integer> age = new ArrayList<>();
    private ArrayList<Double> height = new ArrayList<>();


    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<String> getEmail() {
        return email;
    }

    public ArrayList<Integer> getAge() {
        return age;
    }

    public ArrayList<Double> getHeight() {
        return height;
    }
}
