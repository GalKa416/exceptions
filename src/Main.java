public class Main {

    private static final String VALIDATE_PATTERN = "^[a-zA-Z0-9_]+$";
    public static void main(String[] args) {
        System.out.println("Hello world!");
        validate("logg", "pass", "pass");
        validate("logg", "pass", "pas2");
        validate("logglogglogglogglogglogglogglogg", "pass", "pass");
        validate("log&", "pass", "pass");
        validate("logg", "logglogglogglogglogglogglogglogg", "logglogglogglogglogglogglogglogg");
        validate("logg", "pas#", "pas#");
    }
    public static  Boolean validate (String login, String password, String confirmPassword) {
        boolean isValid = true;
        try {
            validateLogin(login);
            validatePassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println("Ошибка введения логина " + e.getMessage());
            isValid = false;

        } catch (WrongPasswordException e) {
            System.out.println("Ошибка введения пароля " + e.getMessage());
            isValid = false;

        }
        if (isValid) {
            System.out.println("Логин и пароль введены корректно");


        }
        return (isValid);
    }
    public static void validateLogin (String login ) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)){
            throw new WrongLoginException("Логин может содержать в себе только латинские буквы, цифры и знак подчеркивания");
        }
        if (login.length()>20){
            throw new WrongLoginException("Логин не может содержать в себе более 20 символов");
        }
    }
    public static void validatePassword (String password, String confirmPassword ) throws WrongPasswordException {
        if (!password.matches(VALIDATE_PATTERN)){
            throw new WrongPasswordException ("Пароль может содержать в себе только латинские буквы, цифры и знак подчеркивания");
        }
        if (password.length()>20){
            throw new WrongPasswordException("Пароль не может содержать в себе более 20 символов");
        }
        if (!password.equals(confirmPassword)){
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }
}