package org.oregami.transliteratedString;

import org.oregami.transliteratedString.readmodel.live.*;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sebastian on 19.02.17.
 */
public interface TransliteratedStringRepository extends JpaRepository<org.oregami.transliteratedString.readmodel.live.TransliteratedString, String> {
}
