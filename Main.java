import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean condition = true;
        System.out.println("Bun venit! Introduceti numele evenimentului: ");
        String name = scanner.nextLine();
        System.out.println("Bun venit! Introduceti numarul de locuri disponibile: ");
        int number;
        number = checkOption(scanner);
        Event event = new Event(name, number);
        try  {
            event.readData();
        } catch (RuntimeException e) {
            System.out.println("---");
        }
        int selection;
        String lastName, firstName, email, phoneNumber, value, newValue;

        while (condition) {
            System.out.println("\nAsteapta comanda (1 - help - Afiseaza lista de comenzi):");
            int option;
            option = checkOption(scanner);
            switch (option) {
                case 0:
                    condition = false;
                    event.saveData();
                    break;
                case 1:
                    System.out.println("""
                            1 - help - Afiseaza aceasta lista de comenzi
                            2 - add - Adauga o noua persoana (inscriere)
                            3 - check - Verifica daca o persoana este inscrisa la eveniment
                            4 - remove - Sterge o persoana existenta din lista
                            5 - update - Actualizeaza detaliile unei persoane
                            6 - guests - Lista de persoane care participa la eveniment
                            7 - waitlist - Persoanele din lista de asteptare
                            8 - available - Numarul de locuri libere
                            9 - guests_no - Numarul de persoane care participa la eveniment
                            10 - waitlist_no - Numarul de persoane din lista de asteptare
                            11 - subscribe_no - Numarul total de persoane inscrise
                            12 - search - Cauta toti invitatii conform sirului de caractere introdus
                            0 - quit - Inchide aplicatia""");
                    break;
                case 2:
                    System.out.println("Nume de familie: ");
                    scanner.nextLine();
                    lastName = scanner.nextLine();
                    System.out.println("Prenume: ");
                    firstName = scanner.nextLine();
                    System.out.println("Email: ");
                    email = scanner.nextLine();
                    System.out.println("Numar de telefon: ");
                    phoneNumber = scanner.nextLine();
                    event.addGuest(lastName, firstName, email, phoneNumber);
                    break;
                case 3:
                    System.out.println("""
                            Alege modul de autentificare, tastand:
                            1 - Nume \s
                            2 - Prenume
                            3 - Email
                            4 - Numar de telefon (format "0733386463")\"""");
                    selection = checkOption(scanner);
                    System.out.println("Cauta: ");
                    scanner.nextLine();
                    value = scanner.nextLine();
                    event.searchPerson(value, selection);
                    break;
                case 4:
                    System.out.println("""
                            Alege modul de autentificare, tastand:
                            1 - Nume \s
                            2 - Prenume
                            3 - Email
                            4 - Numar de telefon (format "0733386463")\"""");
                    selection = checkOption(scanner);
                    System.out.println("Cauta: ");
                    scanner.nextLine();
                    value = scanner.nextLine();
                    event.deletePerson(value, selection);
                    break;
                case 5:
                    System.out.println("""
                            Alege campul de actualizat, tastand:
                            1 - Nume \s
                            2 - Prenume
                            3 - Email
                            4 - Numar de telefon (format "0733386463")\"""");
                    selection = checkOption(scanner);
                    System.out.println("Cauta: ");
                    scanner.nextLine();
                    value = scanner.nextLine();
                    System.out.println("Inlocuieste: ");
                    newValue = scanner.nextLine();
                    event.updateInformations(value, newValue, selection);
                    break;
                case 6:
                    event.printConfirmedGuests();
                    break;
                case 7:
                    event.printWaitingList();
                    break;
                case 8:
                    event.availableInvitations();
                    break;
                case 9:
                    event.numberOfGuests();
                    break;
                case 10:
                    event.numberOfPersonsWaiting();
                    break;
                case 11:
                    event.allGuests();
                    break;
                case 12:
                    System.out.println("Cauta sirul de caractere:");
                    scanner.nextLine();
                    try {
                        value = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        scanner.close();
                        System.out.println("Aplicatia a fost inchisa.");
                        return;
                    }
                    System.out.println(event.partialSearchList(value));
                    break;
                default:
                    System.out.println("---");
            }
        }
        scanner.close();
        System.out.println("Aplicatia a fost inchisa.");
    }

    //check if the option is a number
    public static int checkOption (Scanner scanner) {
        int option;
        while (true) {
            try {
                option = scanner.nextInt();
                return option;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Optiune invalida.");
            } catch (NoSuchElementException e) {
                event.saveData();
                scanner.close();
                System.out.println("Aplicatia a fost inchisa.");
            }
        }
    }

}
