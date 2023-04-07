public class SynchronizedTest {
   public static void main(String[] args) {
      SharedResource sharedResource = new SharedResource();
      Thread thread1 = new Thread(new SynchronizedMethodThread(sharedResource));
      Thread thread2 = new Thread(new SynchronizedMethodThread(sharedResource));
      Thread thread3 = new Thread(new SynchronizedBlockThread(sharedResource));
      Thread thread4 = new Thread(new SynchronizedBlockThread(sharedResource));
      Thread thread5 = new Thread(new NonSynchronizedThread(sharedResource));
      Thread thread6 = new Thread(new NonSynchronizedThread(sharedResource));
      
      thread1.start();
      thread2.start();
      thread3.start();
      thread4.start();
      thread5.start();
      thread6.start();
   }
}

class SynchronizedMethodThread implements Runnable {
   private SharedResource sharedResource;

   public SynchronizedMethodThread(SharedResource sharedResource) {
      this.sharedResource = sharedResource;
   }

   @Override
   public void run() {
      sharedResource.synchronizedMethod();
   }
}

class SynchronizedBlockThread implements Runnable {
   private SharedResource sharedResource;

   public SynchronizedBlockThread(SharedResource sharedResource) {
      this.sharedResource = sharedResource;
   }

   @Override
   public void run() {
      sharedResource.synchronizedBlock();
   }
}

class NonSynchronizedThread implements Runnable {
   private SharedResource sharedResource;

   public NonSynchronizedThread(SharedResource sharedResource) {
      this.sharedResource = sharedResource;
   }

   @Override
   public void run() {
      sharedResource.nonSynchronizedMethod();
   }
}

class SharedResource {
   private int synchronizedNumber = 0;
   private int nonSynchronizedNumber = 0;

   public synchronized void synchronizedMethod() {
      System.out.println(Thread.currentThread().getName() + ": synchronizedMethod Start");
      for(int i=0; i<5; i++) {
         synchronizedNumber++;
         System.out.println(Thread.currentThread().getName()+ " synchronizedMethod: " + synchronizedNumber);
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println(Thread.currentThread().getName() + ": synchronizedMethod End");
   }
   
   public void synchronizedBlock() {
      System.out.println(Thread.currentThread().getName() + ": synchronizedBlock Start");
      synchronized(this) {
         for(int i=0; i<5; i++) {
            synchronizedNumber++;
            System.out.println(Thread.currentThread().getName()+ " synchronizedBlock: " + synchronizedNumber);
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      }
      System.out.println(Thread.currentThread().getName() + ": synchronizedBlock End");
   }
   
   public void nonSynchronizedMethod() {
      System.out.println(Thread.currentThread().getName() + ": nonSynchronizedMethod Start");
      for(int i=0; i<5; i++) {
         nonSynchronizedNumber++;
         System.out.println(Thread.currentThread().getName()+ " nonSynchronizedMethod: " + nonSynchronizedNumber);
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println(Thread.currentThread().getName() + ": nonSynchronizedMethod End");
   }
}
