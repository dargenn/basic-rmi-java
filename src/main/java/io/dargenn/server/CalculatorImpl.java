package io.dargenn.server;

import io.dargenn.common.ObjectNames;
import io.dargenn.common.SecurityUtils;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl implements Calculator {
    @Override
    public BigDecimal add(BigDecimal a, BigDecimal b) throws RemoteException {
        return a.add(b);
    }

    @Override
    public BigDecimal subtract(BigDecimal a, BigDecimal b) throws RemoteException {
        return a.subtract(b);
    }

    @Override
    public BigDecimal multiply(BigDecimal a, BigDecimal b) throws RemoteException {
        return a.multiply(b);
    }

    @Override
    public BigDecimal divide(BigDecimal a, BigDecimal b) throws RemoteException {
        return a.divide(b, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) throws Exception {
        SecurityUtils.prepareSecurity();
        bindCalculator();
    }

    private static void bindCalculator() throws RemoteException, MalformedURLException {
        System.setProperty("java.rmi.server.hostname", "192.168.0.103");
        Calculator calculator = new CalculatorImpl();
        Calculator stub = (Calculator) UnicastRemoteObject.exportObject(calculator, Registry.REGISTRY_PORT);
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.rebind(ObjectNames.CALCULATOR.name(), stub);
        System.out.println("Calculator bound");
    }
}
