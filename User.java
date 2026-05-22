package models;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
    private String passwordConfirm;
    private Boolean subscribe;
    private Boolean privacyPolicy;

    // Setters and Getters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public Boolean getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(Boolean privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

}

















