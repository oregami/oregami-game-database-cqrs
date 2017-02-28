package org.oregami.gamingEnvironments.event;

/**
 * Created by sebastian on 24.02.17.
 */
public class GamingEnvironmentCreatedEvent {

    private final String id;

    String workingTitle;

    public GamingEnvironmentCreatedEvent(String id, String workingTitle) {
        this.id = id;
        this.workingTitle = workingTitle;
    }

    public String getId() {
        return id;
    }

    public String getWorkingTitle() {
        return workingTitle;
    }
}
