package biz.devspot.management.game.framework.controller;

import biz.devspot.management.game.framework.model.AbstractTransferList;
import org.json.JSONObject;
import uk.co.codeecho.mandrake.core.request.Request;

public class UpdateTransferQueryController extends ActionController{

    private AbstractTransferList transferList;

    public UpdateTransferQueryController(AbstractTransferList transferList) {
        this.transferList = transferList;
    }
    
    @Override
    protected void handle(Request request, JSONObject data) throws Exception {
        transferList.setQuery(data);
    }

}
