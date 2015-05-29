package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.Query;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import biz.devspot.management.game.framework.model.state.PlayerStates;
import java.util.List;
import org.json.JSONObject;

public class AbstractTransferList<P extends AbstractPlayer> {

    private JSONObject rawQuery = new JSONObject();
    private Query query = new QueryBuilder().where("status").isEqualTo(PlayerStates.TRANSFER_LISTED).build();

    public JSONObject getQuery() {
        return rawQuery;
    }

    public void setQuery(JSONObject rawQuery) {
        this.rawQuery = rawQuery;
        QueryBuilder queryBuilder = new QueryBuilder().where("status").isEqualTo(PlayerStates.TRANSFER_LISTED);
        if(rawQuery.optBoolean("filterByAge", false)){
            queryBuilder.and("age").isLessThan(rawQuery.getInt("age"));
        }
        if(rawQuery.optBoolean("filterByPrice", false)){
            queryBuilder.and("askingPrice").isLessThan(rawQuery.getInt("askingPrice"));
        }
        query = queryBuilder.build();
    }
    
    public List<P> getPlayerList(){
        return (List<P>)EntityManagerFactory.getManager().find(AbstractPlayer.class, query);
    }
}
