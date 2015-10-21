package concurrency.reader;

//@include
// LR and LW are static members of type Object in the RW class.
// They serve as read and write locks. The static integer
// field readCount in RW tracks the number of readers.
class Reader extends Thread {
    //@exclude
    String name;
    Reader(String name) { this.name = name; }
    //@include
    public void run() {
        while (true) {
            synchronized (RW.LR) {
                RW.readCount++;
            }
            //@exclude
            System.out.println("Reader " + name + " is about to read");
            //@include
            System.out.println(RW.data);
            synchronized (RW.LR) {
                RW.readCount--;
                RW.LR.notify();
            }
            Task.doSomeThingElse();
        }
    }
}
