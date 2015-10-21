package patterns.creational;

/**
 * 静态内部类 --lazy loading
 * http://www.blogjava.net/kenzhh/archive/2013/03/15/357824.html
 * http://java.dzone.com/articles/singleton-design-pattern-%E2%80%93  Singleton Design Pattern
 */
public class Singleton {
    /**
     * 1 Each class loader creates its one instance
     * 2 Reflection can changes its behavior. AccessibleObject.setAccessible(array, flag);
     * 3 Serialization
     *
     *  Singleton is used for logging, driver objects,
     *       thread pool, caching, configuration file
     *
     */
    private static final class SingletonHolder {
        private static final Singleton instance = new Singleton();
    }

    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}
