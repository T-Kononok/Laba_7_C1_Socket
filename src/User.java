import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class User {
    private String name = "Аноним";
    private String surname = "Анонимов";
    private String ip = "127.000.00.01";
    private char[] password = {'1','2','3'};
    private MainFrameClient mainFrameClient;

    User(MainFrameClient mainFrameClient0){
        mainFrameClient = mainFrameClient0;
    }

    void setName(String name) {
        this.name = name;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setIp(String ip) {
        this.ip = ip;
    }

    void setPassword(char[] password) {
        this.password = password;
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    String getIp() {
        return ip;
    }

    char[] getPassword() {
        return password;
    }

    String checkInData() throws FileNotFoundException {
        Scanner scanner = new Scanner(mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/login.txt"));
        while(scanner.hasNextLine()){
            if(("%"+name + " " + surname).equals(scanner.nextLine().trim())) {
                if (Arrays.equals(password, scanner.nextLine().trim().toCharArray())) {
                    ip = scanner.nextLine().trim();
                    return "Вход";
                }
                else
                    return "Не верный пароль";
            }
        }
        return "Зарегистрируйтесь";
    }

    String checkIPInData() throws IOException {
        Scanner scanner = new Scanner(mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/login.txt"));
        while(scanner.hasNextLine()){
            if((ip).equals(scanner.nextLine().trim()))
                return "IP занят";
        }
        mainFrameClient.writeToFile("D:/Джава/Laba_7_C1_Socket/login.txt","%"+name + " " + surname + "\n" +
                new String(password) + "\n" +
                ip + "\n\n",true);
        return "Регистрация";
    }
}
