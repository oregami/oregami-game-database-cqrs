package org.oregami.gamingEnvironments.readmodel.withTitles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oregami.common.BaseEntityUUID;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sebastian on 28.02.17.
 */
@Entity
@NoArgsConstructor
@Getter
public class Title extends BaseEntityUUID {

    @Column
    private String transliteratedStringId;

    @Column
    private String transliteratedStringText;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @JoinColumn
    Set<TitleUsage> titleUsages = new HashSet<>();


    public Title(String id, String transliteratedStringId, String transliteratedStringText) {
        super(id);
        this.transliteratedStringId = transliteratedStringId;
        this.transliteratedStringText = transliteratedStringText;
    }


}
