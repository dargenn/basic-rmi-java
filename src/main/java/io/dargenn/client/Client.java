package io.dargenn.client;

import io.dargenn.common.ObjectNames;
import io.dargenn.common.SecurityUtils;
import io.dargenn.server.Calculator;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.MessageFormat;

public class Client {
    public static void main(String[] args) throws Exception {
        SecurityUtils.prepareSecurity();
        Calculator calculator = getCalculator();
        performCalculations(calculator);
    }

    private static void performCalculations(Calculator calculator) throws RemoteException {
        BigDecimal a = new BigDecimal(20);
        BigDecimal b = new BigDecimal(5);

        BigDecimal additionResult = calculator.add(a, b);
        BigDecimal subtractionResult = calculator.subtract(a, b);
        BigDecimal multiplicationResult = calculator.multiply(a, b);
        BigDecimal divisionResult = calculator.divide(a, b);

        System.out.println(MessageFormat.format("Addition: {0}, Subtraction: {1}, Multiplication: {2}, Division: {3}",
                additionResult, subtractionResult, multiplicationResult, divisionResult));
    }

    private static Calculator getCalculator() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        return (Calculator) registry.lookup(ObjectNames.CALCULATOR.name());
    }
}
