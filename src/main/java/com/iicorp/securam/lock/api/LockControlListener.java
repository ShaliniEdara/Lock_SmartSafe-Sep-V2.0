package com.iicorp.securam.lock.api;

import com.iicorp.securam.lock.messages.AlarmCode;

import java.util.List;

public interface LockControlListener
{
    void controllerConnected();

    void controllerDisconnected();

    default void controllerAuthFailed() {}

    void lockStatusChange(byte lockNumber, LockStatus previousStatus, LockStatus currentStatus);

    void lockOpen(byte lockNumber);

    void lockClosed(byte lockNumber);

    default void openLockCommandResponse(byte lockNumber, LockStatus status, byte state) {};

    default void closeLockCommandResponse(byte lockNumber, LockStatus status, byte state) {};

    default void lockAlarms(List<AlarmCode> alarms) {};
}
