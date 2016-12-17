package org.oregami.game;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+5+-+Game+Entry+Type
 * Used in Game-Entity
 */
public enum GameEntryType {
    GAME,
    EPISODIC_GAME,
    COMPILATION,
    ADD_ON_SIGNIFICANT,
    ADD_ON_NOT_SIGNIFICANT,
    EPISODE
}
