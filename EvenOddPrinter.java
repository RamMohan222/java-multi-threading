
class Printer {
    int count = 0;

    public synchronized int print() {
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "=" + count);
        count += 1;
        try {
            wait(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return count;
    }
}


public class EvenOddPrinter {

    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();

        Thread even = new Thread(() -> {
            while (printer.print() < 10);
        }, "Even Thread");

        Thread odd = new Thread(() -> {
            while (printer.print() < 10);
        }, "Odd Thread");

        even.start();
        Thread.sleep(100);
        odd.start();
    }
}
