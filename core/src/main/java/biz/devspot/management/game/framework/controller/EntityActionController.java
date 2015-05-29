package biz.devspot.management.game.framework.controller;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.management.game.framework.model.MGFEntity;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public abstract class EntityActionController<E extends MGFEntity> extends TransactionalActionController{

    @Override
    protected void doHandle(Request request, JSONObject data) throws Exception {
        String id = request.getPathParameter("id");
        E entity = (E) EntityManagerFactory.getManager().findById(id);
        
    }
    
    protected abstract void doHandle(Request request, E entity, JSONObject data) throws Exception;

}
