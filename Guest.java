import java.util.Objects;

public class Guest {

    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static boolean compareLastName(Guest person, String lastName) {
        return person.lastName.equalsIgnoreCase(lastName);
    }

    public static boolean compareFirstName(Guest person, String firstName) {
        return person.firstName.equalsIgnoreCase(firstName);
    }

    public static boolean compareEmails(Guest person, String email) {
        return person.email.equalsIgnoreCase(email);
    }

    public static boolean comparePhoneNumbers(Guest person, String phoneNumber) {
        return person.phoneNumber.equals(phoneNumber);
    }

    public boolean partialSearch(String input) {
        String letters = input.toLowerCase();
        return this.lastName.toLowerCase().contains(letters) || this.firstName.toLowerCase().contains(letters)
                || this.email.toLowerCase().contains(letters) || this.phoneNumber.toLowerCase().contains(letters);
    }

    public static Guest createCopy(Guest obj) {
        return new Guest(obj.lastName, obj.firstName, obj.email, obj.phoneNumber);
    }


    @Override
    public String toString() {
        return "Last name: " + this.lastName + "\nFirst name: " + this.firstName
                + "\nEmail: " + this.email + "\nPhone number: " + this.phoneNumber + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(lastName, guest.lastName) && Objects.equals(firstName, guest.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }
}
