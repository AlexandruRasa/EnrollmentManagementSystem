import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean condition = true;
        System.out.println("Bun venit! Introduceti numele evenimentului: ");
        String name = sc.nextLine();
        System.out.println("Bun venit! Introduceti numarul de locuri disponibile: ");
        int number;
        try {
            number = sc.nextInt();
        } catch (InputMismatchException e) {
            number = 1;
            sc.nextLine();
        } catch (NoSuchElementException e) {
            sc.close();
            System.out.println("Aplicatia a fost inchisa.");
            return;
        }
        GuestList event = new GuestList(name, number);
        int selection;
        String lastName, firstName, email, phoneNumber, value, newValue;

        while (condition) {
            System.out.println("\nAsteapta comanda (1 - help - Afiseaza lista de comenzi):");
            int option = 0;
            try {
                option = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Optiune invalida.");
                sc.nextLine();
                continue;
            } catch (NoSuchElementException e) {
                sc.close();
                System.out.println("Aplicatia a fost inchisa.");
                return;
            }
            switch (option) {
                case 0:
                    condition = false;
                    break;
                case 1:
                    System.out.println("1 - help - Afiseaza aceasta lista de comenzi\n" +
                            "2 - add - Adauga o noua persoana (inscriere)\n" +
                            "3 - check - Verifica daca o persoana este inscrisa la eveniment\n" +
                            "4 - remove - Sterge o persoana existenta din lista\n" +
                            "5 - update - Actualizeaza detaliile unei persoane\n" +
                            "6 - guests - Lista de persoane care participa la eveniment\n" +
                            "7 - waitlist - Persoanele din lista de asteptare\n" +
                            "8 - available - Numarul de locuri libere\n" +
                            "9 - guests_no - Numarul de persoane care participa la eveniment\n" +
                            "10 - waitlist_no - Numarul de persoane din lista de asteptare\n" +
                            "11 - subscribe_no - Numarul total de persoane inscrise\n" +
                            "12 - search - Cauta toti invitatii conform sirului de caractere introdus\n" +
                            "0 - quit - Inchide aplicatia");
                    break;
                case 2:
                    System.out.println("Nume de familie: ");
                    sc.nextLine();
                    lastName = sc.nextLine();
                    System.out.println("Prenume: ");
                    firstName = sc.nextLine();
                    System.out.println("Email: ");
                    email = sc.nextLine();
                    System.out.println("Numar de telefon: ");
                    phoneNumber = sc.nextLine();
                    event.addGuest(lastName, firstName, email, phoneNumber);
                    break;
                case 3:
                    System.out.println("Alege modul de autentificare, tastand:\n" +
                                        "1 - Nume  \n" +
                                        "2 - Prenume\n" +
                                        "3 - Email\n" +
                                        "4 - Numar de telefon (format \"0733386463\")\"");
                    try {
                        selection = sc.nextInt();
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("Optiune invalida.");
                        continue;
                    } catch (NoSuchElementException e) {
                        sc.close();
                        System.out.println("Aplicatia a fost inchisa.");
                        return;
                    }
                    System.out.println("Cauta: ");
                    sc.nextLine();
                    value = sc.nextLine();
                    event.searchPerson(value, selection);
                    break;
                case 4:
                    System.out.println("Alege modul de autentificare, tastand:\n" +
                            "1 - Nume  \n" +
                            "2 - Prenume\n" +
                            "3 - Email\n" +
                            "4 - Numar de telefon (format \"0733386463\")\"");
                    try {
                        selection = sc.nextInt();
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("Optiune invalida.");
                        continue;
                    } catch (NoSuchElementException e) {
                        sc.close();
                        System.out.println("Aplicatia a fost inchisa.");
                        return;
                    }
                    System.out.println("Cauta: ");
                    sc.nextLine();
                    value = sc.nextLine();
                    event.deletePerson(value, selection);
                    break;
                case 5:
                    System.out.println("Alege campul de actualizat, tastand:\n" +
                            "1 - Nume  \n" +
                            "2 - Prenume\n" +
                            "3 - Email\n" +
                            "4 - Numar de telefon (format \"0733386463\")\"");
                    try {
                        selection = sc.nextInt();
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("Optiune invalida.");
                        continue;
                    } catch (NoSuchElementException e) {
                        sc.close();
                        System.out.println("Aplicatia a fost inchisa.");
                        return;
                    }
                    System.out.println("Cauta: ");
                    sc.nextLine();
                    value = sc.nextLine();
                    System.out.println("Inlocuieste: ");
                    newValue = sc.nextLine();
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
                    sc.nextLine();
                    try {
                        value = sc.nextLine();
                    } catch (NoSuchElementException e) {
                        sc.close();
                        System.out.println("Aplicatia a fost inchisa.");
                        return;
                    }
                    System.out.println(event.partialSearchList(value));
                    break;
                default:
                    System.out.println("---");
            }
        }
        sc.close();
        System.out.println("Aplicatia a fost inchisa.");
    }
}
