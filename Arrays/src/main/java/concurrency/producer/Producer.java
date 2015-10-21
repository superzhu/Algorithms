package concurrency.producer;

class Producer implements Runnable {
    EmailMessageQueueClass messageQueue;
    String emailMessageContent;

    public Producer(String message, EmailMessageQueueClass messageQueue) {
        emailMessageContent = message;
        this.messageQueue = messageQueue;
    }

    public void run() {
        try {
            messageQueue.adds(emailMessageContent);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
