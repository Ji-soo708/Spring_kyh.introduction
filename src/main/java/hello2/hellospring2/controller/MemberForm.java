package hello2.hellospring2.controller;

public class MemberForm {
    private String name; // home.html에 있는 name과 매칭 되면서 들어올거임
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}