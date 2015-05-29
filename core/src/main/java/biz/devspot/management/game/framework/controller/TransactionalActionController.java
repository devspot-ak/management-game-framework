package biz.devspot.management.game.framework.controller;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public abstract class TransactionalActionController extends ActionController{

    @Override
    protected void handle(Request request, JSONObject data) throws Exception {
        EntityManagerFactory.getManager().openTransaction();
        doHandle(request, data);
        EntityManagerFactory.getManager().commitTransaction();
    }
    
    protected abstract void doHandle(Request request, JSONObject data) throws Exception;

}
