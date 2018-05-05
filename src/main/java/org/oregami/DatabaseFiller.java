package org.oregami;

import org.oregami.game.application.GameApplicationService;
import org.oregami.game.model.GameEntryType;
import org.oregami.game.model.ReleaseGroupReason;
import org.oregami.gamingEnvironments.application.GamingEnvironmentApplicationService;
import org.oregami.transliteratedString.application.TransliteratedStringApplicationService;
import org.oregami.transliteratedString.model.Language;
import org.oregami.transliteratedString.model.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class DatabaseFiller implements CommandLineRunner {

    @Autowired
    private TransliteratedStringApplicationService transliteratedStringApplicationService;

    @Autowired
    private GamingEnvironmentApplicationService gamingEnvironmentApplicationService;

    @Autowired
    private GameApplicationService gameApplicationService;

    @Override
    public void run(String... args) throws Exception {

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(getAuth());
        SecurityContextHolder.setContext(context);

        CompletableFuture<Object> sony_playstation_ts1 = transliteratedStringApplicationService.createNewTransliteratedString(UUID.randomUUID().toString(), "Sony Playstation", Language.ENGLISH.toString(), Script.LATIN.toString());
        CompletableFuture<Object> sony_playstation_ts2 = transliteratedStringApplicationService.createNewTransliteratedString(UUID.randomUUID().toString(), "プレイステーション", Language.JAPANESE.toString(), Script.JAPANESE.toString());

        String sony_playstation_ge = UUID.randomUUID().toString();
        CompletableFuture<Object> ps1 = gamingEnvironmentApplicationService.createNewGamingEnvironment(sony_playstation_ge, "PS1");

        gamingEnvironmentApplicationService.addTitle(ps1.get().toString(), sony_playstation_ts1.get().toString());
        gamingEnvironmentApplicationService.addTitle(ps1.get().toString(), sony_playstation_ts2.get().toString());


        CompletableFuture<Object> monkey_island = gameApplicationService.createNewGame(UUID.randomUUID().toString(), GameEntryType.GAME.toString(), "Monkey Island");
        gameApplicationService.addReleaseGroup(monkey_island.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.ORIGINAL.toString());
        gameApplicationService.addReleaseGroup(monkey_island.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.ENHANCED.toString());
        gameApplicationService.addReleaseGroup(monkey_island.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.REMAKE.toString());

        CompletableFuture<Object> goldgames3 = gameApplicationService.createNewGame(UUID.randomUUID().toString(), GameEntryType.COMPILATION.toString(), "Gold Games 3");
        gameApplicationService.addReleaseGroup(goldgames3.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.ORIGINAL.toString());

        CompletableFuture<Object> talesOfMonkeyIsland = gameApplicationService.createNewGame(UUID.randomUUID().toString(), GameEntryType.EPISODIC_GAME.toString(), "Tales of Monkey Island");
        gameApplicationService.addReleaseGroup(talesOfMonkeyIsland.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.ORIGINAL.toString());



    }

    private Authentication getAuth() {
        return  new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return "init-user-id";
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "init-user-name";
            }
        };
    }
}