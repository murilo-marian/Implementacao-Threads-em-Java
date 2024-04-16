public class Main {
    static class Executor implements Runnable {
        private int superior;

        Executor(int superior) {
            this.superior = superior;
        }

        @Override
        public void run() {
            long meuTid = Thread.currentThread().getId();
            for (int i = 1; i <= superior; i++) {
                System.out.printf("Thread %x: %d\n", meuTid, i);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] argumento = {2, 200};
        if (argumento.length != 2) {
            System.out.println("Uso: java ThreadExample <inteiro 1> <inteiro 2>");
            System.out.println("Onde: <inteiro 1> = N de threads");
            System.out.println("Onde: <inteiro 2> = duração da contagem");
            return;
        }
        int nthread = argumento[0];
        int cont = argumento[1];
        if (nthread <= 0 || cont <= 0) {
            System.out.println("Valores devem ser > 0");
            return;
        }
        Thread[] threads = new Thread[nthread];
        for (int ta = 0; ta < nthread; ta++) {
            threads[ta] = new Thread(new Executor(cont));
            threads[ta].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}