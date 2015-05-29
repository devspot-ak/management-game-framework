package biz.devspot.management.game.framework.route;

import biz.devspot.management.game.framework.builder.AbstractManagerBuilder;
import biz.devspot.management.game.framework.controller.AdvanceController;
import biz.devspot.management.game.framework.controller.DebugController;
import biz.devspot.management.game.framework.controller.EndSeasonController;
import biz.devspot.management.game.framework.controller.LoadGameController;
import biz.devspot.management.game.framework.controller.NewGameController;
import biz.devspot.management.game.framework.controller.PlayMatchesController;
import biz.devspot.management.game.framework.controller.SaveGameController;
import biz.devspot.management.game.framework.controller.SaveLineupController;
import biz.devspot.management.game.framework.controller.StartGameController;
import biz.devspot.management.game.framework.controller.UpdateTransferQueryController;
import biz.devspot.management.game.framework.debug.RemoteDebugger;
import biz.devspot.management.game.framework.match.AbstractMatchEngine;
import biz.devspot.management.game.framework.model.AbstractTransferList;
import biz.devspot.management.game.framework.persistence.PersistenceManager;
import biz.devspot.management.game.framework.state.GameStateBuilder;
import biz.devspot.management.game.framework.view.ViewRenderer;
import java.io.File;
import uk.co.codeecho.mandrake.core.controller.impl.ClasspathAssetController;
import uk.co.codeecho.mandrake.core.controller.impl.FileAssetController;
import static uk.co.codeecho.mandrake.core.request.HttpMethod.*;

public class Router extends uk.co.codeecho.mandrake.core.router.Router{

    public Router(ViewRenderer viewRenderer, PersistenceManager persistenceManager, GameStateBuilder gameStateBuilder, AbstractManagerBuilder managerBuilder, AbstractMatchEngine matchEngine, AbstractTransferList transferList, PlayerRouter playerRouter, RemoteDebugger debugger) {
        route(GET, "/debug").to(new DebugController(debugger));
        //route(GET, "/assets/.*").to(new ClasspathAssetController());
        route(GET, "/assets/.*").to(new FileAssetController(new File("/private/code/management-game-framework/core/src/main/resources/META-INF/resources")));
        route(GET, "/webjars/.*").to(new ClasspathAssetController());
        route(GET, "/{view}").to(viewRenderer);
        route(POST, "/load/new").to(new NewGameController(persistenceManager, gameStateBuilder));
        route(POST, "/start").to(new StartGameController(managerBuilder));
        route(POST, "/save").to(new SaveGameController(persistenceManager));
        route(POST, "/load").to(new LoadGameController(persistenceManager));
        route(POST, "/advance").to(new AdvanceController());
        route(POST, "/endSeason").to(new EndSeasonController());
        route(POST, "/playMatches").to(new PlayMatchesController(matchEngine));
        route(POST, "/updateTransferQuery").to(new UpdateTransferQueryController(transferList));
        route(POST, "/updateTransferQuery").to(new UpdateTransferQueryController(transferList));
        route(POST, "/saveLineup").to(new SaveLineupController());
        route(POST, "/player/{id}/*").to(playerRouter);
        
    }

}
