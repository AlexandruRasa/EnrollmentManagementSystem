public class Test {
    public static void main(String[] args) {


        Event event = new Event("Halloween",3);
        event.printConfirmedGuests();
        event.printWaitingList();
        event.availableInvitations();
        event.deletePerson("Lazar",1);
        event.updateInformations( "Anca","Maria", 2);
        event.addGuest("Traian", "Constantin", "tc@gmail.com", "0733333333");
        event.addGuest("Lazar", "Anca","la@gmail.com","0722221234");
        event.deletePerson("Anca",2);
        event.addGuest("Lazar", "Anca","la@gmail.com","0722221234");
        event.searchPerson("la@gmail.com",3);
        event.updateInformations("Anca", "Constantin", 2);
        event.updateInformations( "Anca","Maria", 2);
        event.addGuest("Florian", "Tudor","fg@gmail.com","0722221234");
        event.addGuest("Lazar", "Anca","la@gmail.com","0722221234");
        event.printConfirmedGuests();
        event.availableInvitations();
        event.numberOfGuests();
        event.numberOfPersonsWaiting();
        event.addGuest("Sabina", "Georgiana","sg@gmail.com","0722221234");
        event.printWaitingList();
        System.out.println(event.partialSearchList("an"));
        System.out.println(event.partialSearchList("911"));
        event.allGuests();
        event.deletePerson("Florian" ,1);
        event.saveData();
        Event event0 = new Event("Halloween1",3);
        event0.readData();
        event.printConfirmedGuests();

    }
}



