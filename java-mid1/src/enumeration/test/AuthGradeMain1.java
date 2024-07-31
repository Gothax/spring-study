package enumeration.test;

public class AuthGradeMain1 {

    public static void main(String[] args) {
        AuthGrade[] values = AuthGrade.values();
        for (AuthGrade value : values) {
            System.out.println("value.name() ,value.getLevel(), value.getDescription() = " + value.name() + value.getLevel() + value.getDescription());
        }
    }
}
