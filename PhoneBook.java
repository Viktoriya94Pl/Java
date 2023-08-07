import java.util.*;

public class PhoneBook {

  private Map <String, List<String>> phoneBook;

  public PhoneBook (){
    phoneBook = new HashMap<>();
  }

  public void addContact(String name, String phoneNumber) {
      List<String> phoneList = phoneBook.getOrDefault(name, new ArrayList<>());
      phoneList.add(phoneNumber);
      phoneBook.put(name, phoneList);
  }
    

   public void printPhoneBook(){
    List<Map.Entry<String, List<String>>> entries = new ArrayList<>(phoneBook.entrySet());
    Collections.sort(entries, new Comparator<Map.Entry<String, List<String>>>() {
      @Override
      public int compare (Map.Entry<String, List<String>> entry1, Map.Entry<String, List<String>> entry2) {
        return Integer.compare(entry2.getValue().size(), entry1.getValue().size());
      }
    });
    for (Map.Entry<String, List<String>> entry: entries){
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }

  }

  
  public static void main(String[] args) {
    
    PhoneBook phoneBook = new PhoneBook();
    phoneBook.addContact("Ivanov Ivan", "+7-985-796-44-55");
    phoneBook.addContact("Ivanov Ivan", "+7-999-589-77-99");
    phoneBook.addContact("Ivanov Ivan", "+7-963-888-77-66");
    phoneBook.addContact("Petrov Petr", "+7-612-748-11-25");
    phoneBook.addContact("Smirnova Svetlana", "+7-965-741-88-55");
    phoneBook.printPhoneBook();
  }
}