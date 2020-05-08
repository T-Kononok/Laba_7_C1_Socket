public class User {
    private String name = "Аноним";
    private String surname = "Анонимов";
    private String ip = "127.0.0.1";
    private String password = "1234";

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIp() {
        return ip;
    }

    public String getPassword() {
        return password;
    }
}
