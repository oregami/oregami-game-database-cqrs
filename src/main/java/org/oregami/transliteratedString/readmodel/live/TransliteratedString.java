package org.oregami.transliteratedString.readmodel.live;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oregami.common.BaseEntityUUID;
import org.oregami.gamingEnvironments.readmodel.live.Title;
import org.oregami.transliteratedString.Language;
import org.oregami.transliteratedString.Script;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sebastian on 13.03.17.
 */
@Entity
@NoArgsConstructor
@Getter
public class TransliteratedString extends BaseEntityUUID {

    @Column
    private String text;

    @Column
    private Language language;

    @Column
    private Script script;

    @Column
    private LocalDateTime changeTime;

    public TransliteratedString(String id, String text, Language language, Script script) {
        super(id);
        this.text = text;
        this.language = language;
        this.script = script;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }
}
