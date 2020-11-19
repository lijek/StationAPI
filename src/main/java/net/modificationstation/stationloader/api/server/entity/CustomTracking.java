package net.modificationstation.stationloader.api.server.entity;

// No envtype cause it makes things harder than it needs to be.
public interface CustomTracking {

    int getTrackingDistance();

    int getUpdateFrequency();

    default boolean sendVelocity() {
        return false;
    }
}
