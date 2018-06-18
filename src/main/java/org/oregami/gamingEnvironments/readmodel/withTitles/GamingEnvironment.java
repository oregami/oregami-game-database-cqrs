package org.oregami.gamingEnvironments.readmodel.withTitles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oregami.common.BaseEntityUUID;
import org.springframework.core.OrderComparator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sebastian on 03.11.16.
 */
@Entity
@NoArgsConstructor
@Getter
public class GamingEnvironment extends BaseEntityUUID {

    @Column
    String workingTitle;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @JoinColumn
    @OrderBy(value = "id")
    Set<Title> gametitles = new TreeSet<>(new OrderComparator());

    @Column
    private LocalDateTime changeTime;

    public GamingEnvironment(String id, String workingTitle) {
        super(id);
        this.workingTitle = workingTitle;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    public void addTitle(Title t) {
        gametitles.add(t);
    }

    public Set<Title> getGametitles() {
        return gametitles;
    }
}
