package biz.devspot.management.game.framework.route;

import biz.devspot.management.game.framework.builder.AbstractTransferOfferBuilder;
import biz.devspot.management.game.framework.controller.EntityActionController;
import biz.devspot.management.game.framework.model.AbstractPlayer;
import biz.devspot.management.game.framework.model.AbstractTransferOffer;
import biz.devspot.management.game.framework.model.TransferOfferMessage;
import biz.devspot.management.game.framework.state.GameStateManager;
import org.json.JSONObject;
import static uk.co.codeecho.mandrake.core.request.HttpMethod.*;
import uk.co.codeecho.mandrake.core.request.Request;

public class PlayerRouter extends uk.co.codeecho.mandrake.core.router.Router {

    private AbstractTransferOfferBuilder transferOfferBuilder;

    public PlayerRouter(AbstractTransferOfferBuilder transferOfferBuilder) {
        this.transferOfferBuilder = transferOfferBuilder;
    }
    
    public PlayerRouter() {
        route(POST, "/transferList").to(new EntityActionController<AbstractPlayer>() {

            @Override
            protected void doHandle(Request request, AbstractPlayer player, JSONObject data) throws Exception {
                player.transferList(data.getInt("askingPrice"));
            }
        });
        route(POST, "/release").to(new EntityActionController<AbstractPlayer>() {

            @Override
            protected void doHandle(Request request, AbstractPlayer player, JSONObject data) throws Exception {
                player.release();
            }
        });
        route(POST, "/removeFromTransferList").to(new EntityActionController<AbstractPlayer>() {

            @Override
            protected void doHandle(Request request, AbstractPlayer player, JSONObject data) throws Exception {
                player.removeFromTransferList();
            }
        });
        route(POST, "/makeOffer").to(new EntityActionController<AbstractPlayer>() {

            @Override
            protected void doHandle(Request request, AbstractPlayer player, JSONObject data) throws Exception {
                AbstractTransferOffer offer = (AbstractTransferOffer) transferOfferBuilder.setPlayer(player).setClub(GameStateManager.getGameState().getManager().getClub()).setPrice(data.getLong("offerPrice")).build();
                TransferOfferMessage message = new TransferOfferMessage(offer);
            }
        });
    }

}
