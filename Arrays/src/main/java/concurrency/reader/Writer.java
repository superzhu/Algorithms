package concurrency.reader;

import java.util.Date;

class Writer extends Thread {
    //@exclude
    String name;
    Writer(String name) { this.name = name; }
    //@include
    public void run() {
        while (true) {
            synchronized (RW.LW) {
                boolean done = false;
                while (!done) {
                    synchronized (RW.LR) {
                        if (RW.readCount == 0) {
                            //@exclude
                            System.out.println("Writer " + name + " is about to write");
                            //@include
                            RW.data = new Date().toString();
                            done = true;
                        } else {
                            // use wait/notify to avoid busy waiting
                            try {
                                // protect against spurious notify, see
                                // stackoverflow.com do-spurious-wakeups-actually-happen
                                while ( RW.readCount != 0 ) {
                                    RW.LR.wait();
                                }
                            } catch (InterruptedException e) {
                                System.out.println("InterruptedException in Writer wait");
                            }
                        }
                    }
                }
            }
            Task.doSomeThingElse();
        }
    }
}
// @exclude