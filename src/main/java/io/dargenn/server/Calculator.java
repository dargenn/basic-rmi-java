package io.dargenn.server;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    BigDecimal add(BigDecimal a, BigDecimal b) throws RemoteException;

    BigDecimal subtract(BigDecimal a, BigDecimal b) throws RemoteException;

    BigDecimal multiply(BigDecimal a, BigDecimal b) throws RemoteException;

    BigDecimal divide(BigDecimal a, BigDecimal b) throws RemoteException;
}
