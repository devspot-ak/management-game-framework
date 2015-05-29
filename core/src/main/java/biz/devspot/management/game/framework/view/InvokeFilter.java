package biz.devspot.management.game.framework.view;

import biz.devspot.entity.framework.core.EntityManager;
import de.neuland.jade4j.filter.Filter;
import java.util.Map;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JadeJexlEngine;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.MapContext;

public class InvokeFilter implements Filter {

    private JadeJexlEngine jexlEngine;

    public InvokeFilter() {
        jexlEngine = new JadeJexlEngine();
    }

    @Override
    public String convert(String expression, Map<String, Object> attributes, Map<String, Object> model) {
        EntityManager entityManager = (EntityManager) model.get("entityManager");
        entityManager.openTransaction();
        Expression exp = jexlEngine.createExpression(expression);
        JexlContext context = new MapContext(model);
        exp.evaluate(context);
        entityManager.commitTransaction();
        return "";
    }

}
