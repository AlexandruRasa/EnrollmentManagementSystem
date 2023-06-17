import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private final String name;
    private final int totalNumberOfInvitations;
    private List<Guest> confirmedGuests;
    private List<Guest> waitingList;

    public Event(String name, int totalNumberOfInvitations) {
        this.name = name;
        this.totalNumberOfInvitations = totalNumberOfInvitations;
        this.confirmedGuests = new ArrayList<>(this.totalNumberOfInvitations);
        this.waitingList = new ArrayList<>(this.totalNumberOfInvitations);
    }

    /**
     * Add a new, unique guest to the list.
     * @return '-1' if the guest is already present, '0' if he is a guest, or the number on the waiting list
     */
    public int addGuest(String lastName, String firstName, String email, String phoneNumber) {
        if (searchPerson(lastName, 1) != null
                && searchPerson(firstName, 2) != null) {
            return -1;
        }
        if (this.confirmedGuests.size() < this.totalNumberOfInvitations) {
                this.confirmedGuests.add(new Guest(lastName, firstName, email, phoneNumber));
            System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            return 0;
        } else {
            this.waitingList.add(new Guest(lastName, firstName, email, phoneNumber));
            System.out.println("Te-ai inscris cu succes in lista de asteptare si " +
                                "ai primit numarul de ordine " + this.waitingList.size() +
                                ". Te vom notifica daca un loc devine disponibil");
            return this.waitingList.size();
        }
    }

    /**
     * Check if someone is already registered (either as a guest, or on the waiting list).
     * @return Guest if present, null if not
     */
    public Guest searchPerson(String input, int option) {
        Guest person;
        switch (option) {
            case 1:
                person = this.searchLastName(input);
                if (person != null) {
                    return person;
                } else {
                    System.out.println("Persoana nu a fost gasita.");
                    break;
                }
            case 2:
                person = this.searchFirstName(input);
                if (person != null) {
                    return person;
                } else {
                    System.out.println("Persoana nu a fost gasita.");
                    break;
                }
            case 3:
                person = this.searchByEmail(input);
                if (person != null) {
                    return person;
                } else {
                    System.out.println("Persoana nu a fost gasita.");
                    break;
                }
            case 4:
                person = this.searchByPhoneNumber(input);
                if (person != null) {
                    return person;
                } else {
                    System.out.println("Persoana nu a fost gasita.");
                    break;
                }
            default:
                System.out.println("---");
        }
        return null;
    }

    //Update information of a guest
    public boolean updateInformations(String check, String newValue, int option) {
        Guest person;
        person = this.searchPerson(check, option);
        if (person != null) {
            return this.changeInformations(person, newValue, option);
        }
        System.out.println("Persoana nu este inscrisa la eveniment.");
        return false;
    }

    private boolean changeInformations(Guest person, String input, int option) {
        if (this.searchPerson(input, option) == null) {
            switch (option) {
            case 1:
                person.setLastName(input);
                System.out.println("Informatii actualizate cu succes.\n" + person);
                return true;
            case 2:
                person.setFirstName(input);
                System.out.println("Informatii actualizate cu succes.\n" + person);
                return true;
            case 3:
                person.setEmail(input);
                System.out.println("Informatii actualizate cu succes.\n" + person);
                return true;
            case 4:
                person.setPhoneNumber(input);
                System.out.println("Informatii actualizate cu succes.\n" + person);
                return true;
            default:
                System.out.println("---");
            }
        }
        System.out.println("Actualizare nereusita.");
        return false;
    }

    /**
     * Remove a guest based on first and last name. Remove the first result.
     * @return true if removed, false if not
     */
    public boolean deletePerson(String input, int option) {
        Guest person;
        person = this.searchPerson(input, option);
        if (person != null) {
            this.confirmedGuests.remove(person);
            System.out.println("Persoana a fost stearsa cu succes din lista de invitati.");
            if (this.confirmedGuests.size() < this.totalNumberOfInvitations
                    && this.waitingList.size() > 0) {
                this.confirmedGuests.add(this.waitingList.get(0));
                System.out.println(this.waitingList.get(0)
                        + "\nFelicitari! Locul tau la eveniment este confirmat. Te asteptam!");
                this.waitingList.remove(0);
            }
            return true;
        }
        System.out.println("Persoana nu este inscrisa la eveniment.");
        return false;
    }

    /**
     * Find all people based on a partial value search.
     *
     * @param match the match we are looking for
     * @return a list of people matching the criteria
     */
    public ArrayList<Guest> partialSearchList(String match) {
        ArrayList<Guest> result = new ArrayList<>();
        for (Guest i : this.confirmedGuests) {
            if (i.partialSearch(match)) {
                result.add(Guest.createCopy(i));
            }
        }
        for (Guest i : this.waitingList) {
            if (i.partialSearch(match)) {
                result.add(Guest.createCopy(i));
            }
        }
        System.out.println("Rezultat cautare partiala");
        return result;
    }

    // Show the list of guests.
    public void printConfirmedGuests() {
        if (this.confirmedGuests.size() > 0) {
            System.out.println("Lista invitati " + this.name + ": ");
            for (Guest i : this.confirmedGuests) {
                System.out.println("Guest (" + (this.confirmedGuests.indexOf(i) + 1) + "):\n" + i);
            }
        } else {
            System.out.println("Lista de invitati este goala.");
        }
    }


    //Show the people are on the waiting list.
    public void printWaitingList() {
        if (this.waitingList.size() > 0) {
            System.out.println("Lista asteptare " + this.name + ": ");
            for (Guest i : this.waitingList) {
                System.out.println("Guest (" + (this.waitingList.indexOf(i) + 1) + "):\n" + i);
            }
        } else {
            System.out.println("Lista de asteptare este goala.");
        }
    }


     //Show how many free spots are left.
    public void availableInvitations() {
        if (this.confirmedGuests.size() == this.totalNumberOfInvitations) {
            System.out.println("Numarul de locuri disponibile este: 0");
        } else {
            System.out.println("Numarul de locuri disponibile este: " +
                    (this.totalNumberOfInvitations - this.confirmedGuests.size()));
        }
    }


    //Show how many people there are confirmed.
    public void numberOfGuests() {
        System.out.println("Numarul de participanti: " + this.confirmedGuests.size());
    }

    //Show how many people there are on the waiting list.
    public void numberOfPersonsWaiting() {
        System.out.println("Dimensiunea listei de asteptare: " + this.waitingList.size());
    }

    // in total, including guests.
    public void allGuests() {
        System.out.println("Numarul total de persoane: "
                            + (this.confirmedGuests.size() + this.waitingList.size()));
    }

    private Guest searchLastName(String lastName) {
        for (Guest i : this.confirmedGuests) {
            if (Guest.compareLastName(i, lastName)) {
                System.out.println("Persoana a fost deja inscrisa la eveniment.");
                return i;
            }
        }
        for (Guest i : this.waitingList) {
            if (Guest.compareLastName(i, lastName)) {
                System.out.println("Persoana se afla pe lista de asteptare.");
                return i;
            }
        }
        return null;
    }

    private Guest searchFirstName(String firstName) {
        for (Guest i : this.confirmedGuests) {
            if (Guest.compareFirstName(i, firstName)) {
                System.out.println("Persoana a fost deja inscrisa la eveniment.");
                return i;
            }
        }
        for (Guest i : this.waitingList) {
            if (Guest.compareFirstName(i, firstName)) {
                System.out.println("Persoana se afla pe lista de asteptare.");
                return i;
            }
        }
        return null;
    }

    private Guest searchByEmail(String email) {
        for (Guest i : this.confirmedGuests) {
            if (Guest.compareEmails(i, email)) {
                System.out.println("Persoana a fost deja inscrisa la eveniment.");
                return i;
            }
        }
        for (Guest i : this.waitingList) {
            if (Guest.compareEmails(i, email)) {
                System.out.println("Persoana se afla pe lista de asteptare.");
                return i;
            }
        }
        return null;
    }

    private Guest searchByPhoneNumber(String phoneNumber) {
        for (Guest i : this.confirmedGuests) {
            if (Guest.comparePhoneNumbers(i, phoneNumber)) {
                System.out.println("Persoana a fost deja inscrisa la eveniment.");
                return i;
            }
        }
        for (Guest i : this.waitingList) {
            if (Guest.comparePhoneNumbers(i, phoneNumber)) {
                System.out.println("Persoana se afla pe lista de asteptare.");
                return i;
            }
        }
        return null;
    }

    public void saveData () {
        try (ObjectOutputStream binaryFileOut = new ObjectOutputStream
                (new BufferedOutputStream(new FileOutputStream("C:\\Users\\andu3\\Desktop\\Guests.dat")))) {
            binaryFileOut.writeObject(this.confirmedGuests);
            binaryFileOut.writeObject(this.waitingList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readData() {
        try (ObjectInputStream binaryFileIn = new ObjectInputStream(new BufferedInputStream
                (new FileInputStream("C:\\Users\\andu3\\Desktop\\Guests.dat")))) {
            this.confirmedGuests = (List<Guest>) binaryFileIn.readObject();
            this.waitingList = (List<Guest>) binaryFileIn.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
