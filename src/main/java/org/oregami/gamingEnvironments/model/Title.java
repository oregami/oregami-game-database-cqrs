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
    private Set<TitleUsage> titleUsages = new HashSet<>();

    public Title(String titleId, String transliteratedStringId) {
        this.titleId = titleId;
        this.transliteratedStringId = transliteratedStringId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTransliteratedStringId() {
        return transliteratedStringId;
    }

    public void setTransliteratedStringId(String transliteratedStringId) {
        this.transliteratedStringId = transliteratedStringId;
    }

    public Set<TitleUsage> getTitleUsages() {
        return titleUsages;
    }

    public void setTitleUsages(Set<TitleUsage> titleUsages) {
        this.titleUsages = titleUsages;
    }
}
