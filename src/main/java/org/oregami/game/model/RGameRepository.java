package org.oregami.game.model;

import org.oregami.game.readmodel.RGame;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sebastian on 19.02.17.
 */
public interface RGameRepository extends JpaRepository<RGame, String> {
}
