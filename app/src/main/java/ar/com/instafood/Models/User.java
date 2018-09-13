package ar.com.instafood.Models;

public class User {

    private String id, name, lastName;

        public User(String id,String name,String lastName){
            this.id = id;
            this.name = name;
            this.lastName = lastName;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
