1. Spring boot @Async
- apply to public method
- sefl-invocation won't work
2. Return type
- void
- Future(AsyncResult): java 5
    + not have any methods to combine these computations or handle possible errors
- CompletableFuture: java 8
3. The executor
- Spring have a default executor 'SimpleAsyncTaskExecutor'
- We can create a new executor
    + @Bean(name = "sampleTaskExecutor")
    public Executor sampleTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
    + @Async("sampleTaskExecutor")