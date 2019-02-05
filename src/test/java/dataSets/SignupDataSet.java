package dataSets;

public class SignupDataSet {
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean checkBoxesChecked;
    
    public SignupDataSet(String firstName, String lastName, String email, String password, 
            boolean checkBoxes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.checkBoxesChecked = checkBoxes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCheckBoxesChecked() {
        return checkBoxesChecked;
    }
    
}
