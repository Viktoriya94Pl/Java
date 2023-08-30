package hw3;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMap<K, V> implements Iterable<HashMap.Entity> {


    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5;

    private Bucket[] buckets;
    private int size;


    @Override
    public Iterator<HashMap.Entity> iterator() {
        return new HashMapIterator();
    }

    class HashMapIterator implements Iterator<HashMap.Entity> {

       int bucketIndex = 0; // Индекс текущего bucket
       int nodeIndex = 0;  // Индекс текущей ноды в bucket
       Entity entity;

        @Override
        public boolean hasNext() {

            for (int i = bucketIndex; i < buckets.length; i ++) {
                
                Bucket<K, V> bucket = buckets[i];

                if (bucket != null) {
                    
                    // Перебираем связный список в бакете с той ноды, на которой остановились
                    Bucket.Node node = bucket.head;
                    
                    int j = 0;
                    
                    while (node != null) {
                        
                        if (j < nodeIndex) {
                            j ++;
                            node = node.next;
                            continue;
                        }
                        
                        entity = new Entity();
                        entity.key = (K)node.value.key;
                        entity.value = (V)node.value.value;
                        nodeIndex ++;
                        return true;
                    }
                    
                    nodeIndex = 0;
                }                
                                
                bucketIndex ++;
            }
            
            return false;
        }

        @Override
        public Entity next() { //метод возвращает следующий элемент
            return entity;
        }
    }


    /**
     * TODO: Вывести все элементы хеш-таблицы на экран через toString()
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }



    class Entity{

        K key;
        V value;

    }

  
    class Bucket<K, V>{

        /**
         * Указатель на первый элемент связного списка
         */
        Node head;

        /**
         * Узел бакета (связного списка)
         */
        class Node{

            /**
             * Указатель на следующий элемент связного списка
             */
            Node next;

            /**
             * Значение узла, указывающее на элемент хеш-таблицы
             */
            Entity value;

        }

        public V add(Entity entity){
            Node node = new Node();
            node.value = entity;

            if (head == null){
                head = node;
                return null;
            }

            Node currentNode = head;
            while (true){
                if (currentNode.value.key.equals(entity.key)){
                    V buf = (V)currentNode.value.value;
                    currentNode.value.value = entity.value;
                    return buf;
                }
                if (currentNode.next != null){
                    currentNode = currentNode.next;
                }
                else {
                    currentNode.next = node;
                    return null;
                }
            }
        }

        public V remove(K key){
            if (head == null)
                return null;
            if (head.value.key.equals(key)){
                V buf = (V)head.value.value;
                head = head.next;
                return buf;
            }
            else {
                Node node = head;
                while (node.next != null){
                    if (node.next.value.key.equals(key)){
                        V buf = (V)node.next.value.value;
                        node.next = node.next.next;
                        return buf;
                    }
                    node = node.next;
                }
                return null;
            }
        }

        public V get(K key){
            Node node = head;
            while (node != null){
                if (node.value.key.equals(key))
                    return (V)node.value.value;
                node = node.next;
            }
            return null;
        }

    }

    private int calculateBucketIndex(K key){
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void recalculate(){
        size = 0;
        Bucket<K, V>[] old = buckets;
        buckets = new Bucket[old.length * 2];
        for (int i = 0; i < old.length; i++){
            Bucket<K, V> bucket = old[i];
            if (bucket != null){
                Bucket.Node node = bucket.head;
                while (node != null){
                    put((K)node.value.key, (V)node.value.value);
                    node = node.next;
                }
            }
        }
    }

    public V put(K key, V value){
        if (size >= buckets.length * LOAD_FACTOR ){
            recalculate();
        }
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null){
            bucket = new Bucket();
            buckets[index] = bucket;
        }

        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;

        V buf = (V)bucket.add(entity);
        if (buf == null){
            size++;
        }
        return buf;
    }

    public V get(K key){
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null)
            return null;
        return (V)bucket.get(key);
    }

    public V remove(K key){
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null)
            return null;
        V buf = (V)bucket.remove(key);
        if (buf != null){
            size--;
        }
        return buf;
    }

    public HashMap(){
        buckets = new HashMap.Bucket[INIT_BUCKET_COUNT];
    }

    public HashMap(int initCount){
        buckets = new HashMap.Bucket[initCount];
    }


}
