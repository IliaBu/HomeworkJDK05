package gb.jdk;

public class Main {

//    Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
//    Вилки лежат на столе между каждой парой ближайших философов.
//    Каждый философ может либо есть, либо размышлять.
//    Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
//    Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
//    Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза

    public static void main(String[] args) {
        Philosopher phil1 = Philosopher.createAndStart("Сократ");
        Philosopher phil2 = Philosopher.createAndStart("Ницше");
        Philosopher phil3 = Philosopher.createAndStart("Гераклит");
        Philosopher phil4 = Philosopher.createAndStart("Платон");
        Philosopher phil5 = Philosopher.createAndStart("Конфуций");
        
        try {
            phil1.philosopherThread.join();
            phil2.philosopherThread.join();
            phil3.philosopherThread.join();
            phil4.philosopherThread.join();
            phil5.philosopherThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Philosopher.println("Все философы насытились, обед закончен.", Colors.GREEN);
    }
}
