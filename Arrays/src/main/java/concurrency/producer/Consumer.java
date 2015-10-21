package concurrency.producer;

class Consumer implements Runnable {
    EmailMessageQueueClass messageQueue;

    public Consumer(EmailMessageQueueClass messageQueue) {
        this.messageQueue = messageQueue;
    }

    public void run() {
        try {
            messageQueue.retrieve();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
