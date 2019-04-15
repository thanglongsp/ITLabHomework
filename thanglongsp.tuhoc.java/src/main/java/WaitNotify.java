public class WaitNotify {
    public static class Message {
        private String content;

        String getContent() {
            return content;
        }

        void setContent(String content) {
            this.content = content;
        }
    }

    public static class Sender implements Runnable {
        final Message ms;

        Sender(Message ms) {
            this.ms = ms;
        }

        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (ms) {
                ms.setContent("Hello ! I'm thanglongsp  =))");
                ms.notifyAll();
            }
        }
    }

    public static class Receiver implements Runnable {
        final Message ms;

        Receiver(Message ms) {
            this.ms = ms;
        }

        public void run() {
            synchronized (ms) {
                System.out.println("Waiting ...");
                try {
                    ms.wait();
                    System.out.println("Received : " + ms.getContent());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Message ms = new Message();
        Thread t1 = new Thread(new Sender(ms));
        Thread t2 = new Thread(new Receiver(ms));

        t1.start();
        t2.start();
    }
}
