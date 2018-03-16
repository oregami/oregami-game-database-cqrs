package org.oregami.transliteratedString.model;

import org.oregami.transliteratedString.readmodel.live.*;
import org.oregami.transliteratedString.readmodel.live.TransliteratedString;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sebastian on 19.02.17.
 */
public interface TransliteratedStringRepository extends JpaRepository<TransliteratedString, String> {

    public List<TransliteratedString> findByTextIgnoreCaseContaining(String text);


}
