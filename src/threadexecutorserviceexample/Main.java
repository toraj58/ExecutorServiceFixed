/*
 * http://www.javatpoint.com/java-thread-pool
Java Thread pool represents a group of worker threads that are waiting for the job and reuse many times.

In case of thread pool, a group of fixed size threads are created. A thread from the thread pool is pulled out and assigned a job by the service provider. After completion of the job, thread is contained in the thread pool again.
 * Better performance It saves time because there is no need to create new thread.
 */
package threadexecutorserviceexample;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Touraj
 */

class WorkerThread implements Runnable {  
    private String message;  
    public WorkerThread(String s){  
        this.message=s;  
    }  
     public void run() {  
        System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);  
        processmessage();//call processmessage method that sleeps the thread for 2 seconds  
        System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name  
    }  
    private void processmessage() {  
        try {  
            System.out.println("Sleeping ...");
            Thread.sleep(2000);  
        } catch (InterruptedException e) { e.printStackTrace(); }  
    }  
}  

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("**************************************************");
        System.out.println("**                                              **");
        System.out.println("**    Thread Pool Executor Service Fixed Size   **");
        System.out.println("**                                              **");
        System.out.println("**************************************************");
        
        ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads  
        
        for (int i = 0; i < 10; i++) {  
            Runnable worker = new WorkerThread("" + i);  
            executor.execute(worker);//calling execute method of ExecutorService  
          }  
           
        System.out.println("--- Befor Shutdown ---");
        executor.shutdown(); 
                
        while (!executor.isTerminated()) {   }  
  
        System.out.println("Finished all threads");  
        
        System.out.println("**************************************************");
        
        System.out.println("str1 : " + executor.hashCode());
        System.out.println("str2 : " + executor.toString());
        System.out.println("str3 : " + executor.getClass().getName());
        System.out.println("str4 : " + executor.getClass().getCanonicalName());
        System.out.println("str5 : " + executor.getClass().getSimpleName());
        
       Method[] mts = executor.getClass().getMethods();
       
       System.out.println("mts size is : " + mts.length);
        short i = 1;
        
        for (Method m : mts) {
            
            System.out.println("m" + i + " : " +  m);
            ++i;
        }
    }
}