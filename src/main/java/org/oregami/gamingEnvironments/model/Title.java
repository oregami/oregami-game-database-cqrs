package org.oregami.gamingEnvironments.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.commandhandling.model.EntityId;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sebastian on 28.02.17.
 */
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Title {

    @EntityId
    private String titleId;

    private String transliteratedStringId;

    @AggregateMember
    Set<TitleUsage> titleUsageSet = new HashSet<>();

    public Title(String titleId, String transliteratedStringId) {
        this.titleId = titleId;
        this.transliteratedStringId = transliteratedStringId;
    }

}
