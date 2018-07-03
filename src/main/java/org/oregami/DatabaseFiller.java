package org.oregami;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.oregami.game.application.GameApplicationService;
import org.oregami.game.model.GameEntryType;
import org.oregami.game.model.ReleaseGroupReason;
import org.oregami.gamingEnvironments.application.GamingEnvironmentApplicationService;
import org.oregami.gamingEnvironments.model.Region;
import org.oregami.gamingEnvironments.model.TitleType;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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


        TitleType titleType = TitleType.ORIGINAL_TITLE;

        //################   gaming environments ###################

        //PS1
        CompletableFuture<Object> sony_playstation_ts1 = transliteratedStringApplicationService.createNewTransliteratedString(UUID.randomUUID().toString(), "Sony Playstation", Language.ENGLISH.toString(), Script.LATIN.toString());
        CompletableFuture<Object> sony_playstation_ts2 = transliteratedStringApplicationService.createNewTransliteratedString(UUID.randomUUID().toString(), "プレイステーション", Language.JAPANESE.toString(), Script.JAPANESE.toString());
        String sony_playstation_ge = UUID.randomUUID().toString();
        CompletableFuture<Object> ps1 = gamingEnvironmentApplicationService.createNewGamingEnvironment(sony_playstation_ge, "PS1");
        CompletableFuture<Object> title1 = gamingEnvironmentApplicationService.addTitle("t1", ps1.get().toString(), sony_playstation_ts1.get().toString());
        CompletableFuture<Object> title2 = gamingEnvironmentApplicationService.addTitle("t2", ps1.get().toString(), sony_playstation_ts2.get().toString());

        gamingEnvironmentApplicationService.addTitleUsage("t1", ps1.get().toString(), title2.get().toString(), Region.JAPAN, titleType);
        gamingEnvironmentApplicationService.addTitleUsage("t2", ps1.get().toString(), title1.get().toString(), Region.WORLDWIDE, titleType);

        //C64
        String idGeC64 = UUID.randomUUID().toString();
        gamingEnvironmentApplicationService.createNewGamingEnvironment(idGeC64, "C64");
        String idTsC64 = UUID.randomUUID().toString();
        transliteratedStringApplicationService.createNewTransliteratedString(idTsC64, "C64", Language.NOLANGUAGE.toString(), Script.LATIN.toString());
        String idTitleC64 = UUID.randomUUID().toString();
        gamingEnvironmentApplicationService.addTitle(idTitleC64, idGeC64, idTsC64);
        String idTitleUsageC64Worldwide = UUID.randomUUID().toString();
        gamingEnvironmentApplicationService.addTitleUsage(idTitleUsageC64Worldwide, idGeC64, idTitleC64, Region.WORLDWIDE, TitleType.ABBREVIATION);

        String idTsBrotkasten = UUID.randomUUID().toString();
        transliteratedStringApplicationService.createNewTransliteratedString(idTsBrotkasten, "Brotkasten", Language.GERMAN.toString(), Script.LATIN.toString());
        String idTitleBrotkasten = UUID.randomUUID().toString();
        gamingEnvironmentApplicationService.addTitle(idTitleBrotkasten, idGeC64, idTsBrotkasten);
        String idTitleUsageBrotkastenGermany = UUID.randomUUID().toString();
        gamingEnvironmentApplicationService.addTitleUsage(idTitleUsageBrotkastenGermany, idGeC64, idTitleBrotkasten, Region.GERMANY, TitleType.INOFFICIAL_TITLE);



        List<ImmutablePair<ImmutableTriple<String, Language, Script>, List<ImmutablePair<Region, TitleType>>>> amigaInputList = new ArrayList<>();

        List<ImmutablePair<Region, TitleType>> titleUsageInputList = new ArrayList<>();
        titleUsageInputList.add(new ImmutablePair<>(Region.WORLDWIDE, TitleType.ORIGINAL_TITLE));
        ImmutablePair<ImmutableTriple<String, Language, Script>, List<ImmutablePair<Region, TitleType>>> pair =
                new ImmutablePair<ImmutableTriple<String, Language, Script>, List<ImmutablePair<Region, TitleType>>>(
                        new ImmutableTriple<String, Language, Script>(
                                "Commodore Amiga",
                                Language.ENGLISH,
                                Script.LATIN
                        ),
                        titleUsageInputList);
        amigaInputList.add(pair);
        createGamingEnvironment("Amiga", amigaInputList);

        //########## games ###############

        CompletableFuture<Object> monkey_island = gameApplicationService.createNewGame(UUID.randomUUID().toString(), GameEntryType.GAME.toString(), "Monkey Island");
        gameApplicationService.addReleaseGroup(monkey_island.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.ORIGINAL.toString());
        gameApplicationService.addReleaseGroup(monkey_island.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.ENHANCED.toString());
        gameApplicationService.addReleaseGroup(monkey_island.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.REMAKE.toString());

        CompletableFuture<Object> goldgames3 = gameApplicationService.createNewGame(UUID.randomUUID().toString(), GameEntryType.COMPILATION.toString(), "Gold Games 3");
        gameApplicationService.addReleaseGroup(goldgames3.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.ORIGINAL.toString());

        CompletableFuture<Object> talesOfMonkeyIsland = gameApplicationService.createNewGame(UUID.randomUUID().toString(), GameEntryType.EPISODIC_GAME.toString(), "Tales of Monkey Island");
        gameApplicationService.addReleaseGroup(talesOfMonkeyIsland.get().toString(), UUID.randomUUID().toString(), ReleaseGroupReason.ORIGINAL.toString());





    }

    private void createGamingEnvironment(String workingTitle, List<ImmutablePair<ImmutableTriple<String, Language, Script>, List<ImmutablePair<Region, TitleType>>>> inputList) {


        String geId = UUID.randomUUID().toString();
        gamingEnvironmentApplicationService.createNewGamingEnvironment(geId, workingTitle);

        for (ImmutablePair<ImmutableTriple<String, Language, Script>, List<ImmutablePair<Region, TitleType>>> input: inputList) {
            ImmutableTriple<String, Language, Script> tsInput = input.left;

            String idTs = UUID.randomUUID().toString();
            transliteratedStringApplicationService.createNewTransliteratedString(idTs, tsInput.left, tsInput.middle.toString(), tsInput.right.toString());

            for (ImmutablePair<Region, TitleType> titleUsageInput: input.right) {

                String titleId = UUID.randomUUID().toString();
                gamingEnvironmentApplicationService.addTitle(titleId, geId, idTs);
                
                String idTitleUsage = UUID.randomUUID().toString();
                gamingEnvironmentApplicationService.addTitleUsage(idTitleUsage, geId, titleId, titleUsageInput.left, titleUsageInput.right);
            }
        }
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