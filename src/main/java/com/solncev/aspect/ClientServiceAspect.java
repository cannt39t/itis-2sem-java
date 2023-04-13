package com.solncev.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Aspect
@Component
public class ClientServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceAspect.class);

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Around("execution(* com.solncev.service.ClientService.create(..))")
    public Object aroundCreateClient(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Starting transaction for createClient method");

        // Start a new transaction
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Object result = joinPoint.proceed(); // Execute the original method
            transactionManager.commit(transactionStatus); // Commit the transaction
            LOGGER.info("Transaction committed successfully for createClient method");
            return result;
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus); // Rollback the transaction
            LOGGER.error("Transaction rolled back for createClient method: {}", e.getMessage());
            throw e;
        }
    }
}
