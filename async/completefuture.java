package com.finx.payroll.core.v2.service;

import java.util.concurrent.CompletableFuture;

class DebtSyncException extends RuntimeException {
  public DebtSyncException(String message, Throwable cause) {
    super(message, cause);
  }
}

public class CompletableFutureWhenCompleteExample {
  public static void main(String[] args) {
    // Create a few CompletableFutures, one of which will throw an exception
    CompletableFuture<Void> future1 =
        CompletableFuture.runAsync(
            () -> {
              try {
                Thread.sleep(500);
                System.out.println("Task 1 completed");
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            });

    CompletableFuture<Void> future2 =
        CompletableFuture.runAsync(
            () -> {
              try {
                Thread.sleep(500);
                System.out.println("Task 2 completed");
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            });

    CompletableFuture<Void> future3 =
        CompletableFuture.runAsync(
            () -> {
              //              throw new RuntimeException("Task 3 failed");
              try {
                Thread.sleep(500);
                System.out.println("Task 3 completed");
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            });

    CompletableFuture<Void> future4 =
        CompletableFuture.runAsync(
            () -> {
              //              try {
              //                Thread.sleep(500);
              //                System.out.println("Task 4 completed");
              //              } catch (InterruptedException e) {
              //                throw new RuntimeException(e);
              //              }
              throw new RuntimeException("Task 4 DebtSyncException occurred", null);
            });

    // Combine all futures
    CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2, future3, future4);

    // Handle completion and exceptions
    //    try {
    allOf
        .whenComplete(
            (result, throwable) -> {
              if (throwable instanceof DebtSyncException) {
                System.out.println("One of the tasks failed: " + throwable.getMessage());
                throw new DebtSyncException("DebtSyncException occurred", throwable);
              }
            })
        .join(); // Wait for all tasks to complete
    //    } catch (CompletionException e) {
    //      // Handle the exception
    //      if (e.getCause() instanceof DebtSyncException) {
    //        System.out.println("Caught DebtSyncException: " + e.getCause().getMessage());
    //      } else {
    //        System.out.println("Caught unexpected exception: " + e.getCause());
    //      }
    //    }
  }
}
