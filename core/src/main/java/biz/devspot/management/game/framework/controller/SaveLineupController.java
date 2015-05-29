package biz.devspot.management.game.framework.controller;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.management.game.framework.model.AbstractPlayer;
import biz.devspot.management.game.framework.model.AbstractPlayerTactics;
import java.util.Iterator;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public class SaveLineupController extends TransactionalActionController {

    @Override
    protected void doHandle(Request request, JSONObject data) throws Exception {
        Iterator<String> iterator = data.keys();
        while (iterator.hasNext()) {
            String playerTacticsId = iterator.next();
            AbstractPlayerTactics playerTactics = (AbstractPlayerTactics) EntityManagerFactory.getManager().findById(playerTacticsId);
            AbstractPlayer player = null;
            if (!data.isNull(playerTacticsId)) {
                String playerId = data.getString(playerTacticsId);
                player = (AbstractPlayer) EntityManagerFactory.getManager().findById(playerId);
            }
            playerTactics.setPlayer(player);
        }
    }

}
