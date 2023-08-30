package hw3;

public class Program {

     public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>(4);

        String res = hashMap.put("+79078595443", "Abroham");
        res = hashMap.put("+79005887443", "Boris");
        res = hashMap.put("+79005554432", "Oleg");
        res = hashMap.put("+79005554433", "Oleg1");
        res = hashMap.put("+79005554434", "Oleg2");
        res = hashMap.put("+79005554435", "Oleg4");
        res = hashMap.put("+79005554436", "Oleg5");
        res = hashMap.put("+79005554437", "Oleg6");
        res = hashMap.put("+79005554438", "Oleg7");
        res = hashMap.put("+79005554439", "Oleg8");


        //res = hashMap.get("+79005554436");
        
        //hashMap.remove("+79005554438");
        
        for (HashMap.Entity element : hashMap) {
            System.out.println(element.key + " - " + element.value);
        }

    }

}