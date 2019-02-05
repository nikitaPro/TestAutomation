package dataSets;

public class LoginDataSet {
    private String email;
    private String password;
    
    public LoginDataSet(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    
}
