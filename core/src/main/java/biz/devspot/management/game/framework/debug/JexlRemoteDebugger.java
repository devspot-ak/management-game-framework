package biz.devspot.management.game.framework.debug;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.dao.EntityDao;
import biz.devspot.management.game.framework.state.GameStateManager;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.JexlException;
import org.apache.commons.jexl2.MapContext;

public class JexlRemoteDebugger implements RemoteDebugger {

    JexlEngine jexlEngine = new JexlEngine();
    JexlContext jexlContext = new MapContext();

    public JexlRemoteDebugger(EntityDao dao) {
        jexlEngine.setStrict(true);
        jexlEngine.setSilent(false);
        jexlContext.set("dao", dao);
    }

    @Override
    public Object execute(String command) throws Exception {
        jexlContext.set("entityManager", EntityManagerFactory.getManager());
        jexlContext.set("state", GameStateManager.getGameState());
        Expression expression = jexlEngine.createExpression(command);
        try {
            EntityManagerFactory.getManager().openTransaction();
            Object result = expression.evaluate(jexlContext);
            EntityManagerFactory.getManager().commitTransaction();
            return result;
        } catch (JexlException ex) {
            System.out.println(ex.getCause());
            ex.printStackTrace();
            throw ex;
        }
    }

}
