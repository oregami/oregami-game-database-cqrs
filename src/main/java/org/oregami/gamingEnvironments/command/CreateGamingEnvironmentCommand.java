package org.oregami.gamingEnvironments.command;

import org.oregami.transliteratedString.Language;
import org.oregami.transliteratedString.Script;

/**
 * Created by sebastian on 24.02.17.
 */
public class CreateGamingEnvironmentCommand {

    private final String id;

    String workingTitle;

    public CreateGamingEnvironmentCommand(String id, String workingTitle) {
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
