package org.oregami.gamingEnvironments;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sebastian on 19.02.17.
 */
public interface GamingEnvironmentRepository extends JpaRepository<org.oregami.gamingEnvironments.readmodel.live.GamingEnvironment, String> {
}
