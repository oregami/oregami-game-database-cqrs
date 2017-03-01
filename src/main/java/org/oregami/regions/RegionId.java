package org.oregami.regions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by sebastian on 28.02.17.
 */
@Embeddable
@AllArgsConstructor
public class RegionId {

    @Column(name="region_name")
    private String value = null;

    public RegionId() {
    }

    public String getValue() {
        return value;
    }
}
