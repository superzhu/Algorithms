package patterns.creational;

/**
 * http://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-with-examples
 * http://javarevisited.blogspot.com/2012/07/why-enum-singleton-are-better-in-java.html
 *
 * Why Enum Singleton are better in Java
 *   Enum is thread safe and implementation of Singleton through Enum ensures that your singleton will
 *   have only one instance even in a multithreaded environment. Let us see a simple implementation
 */
public enum EnumSingleton {
    INSTANCE;

    public void doSomething(){
        System.out.println("Calling Enum  Singleton...");
    }

    public static void main(String[] args) {
        EnumSingleton.INSTANCE.doSomething();
    }
}
