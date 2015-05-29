package biz.devspot.management.game.framework.view;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.management.game.framework.model.AbstractTransferList;
import biz.devspot.management.game.framework.persistence.PersistenceManager;
import biz.devspot.management.game.framework.state.GameStateManager;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.template.JadeTemplate;
import de.neuland.jade4j.template.TemplateLoader;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import uk.co.codeecho.mandrake.core.controller.AbstractController;
import uk.co.codeecho.mandrake.core.renderer.impl.jade.AssetLoader;
import uk.co.codeecho.mandrake.core.request.Response;

public class ViewRenderer extends AbstractController {

    private final PersistenceManager persistenceManager;
    private final AbstractTransferList transferList;
    private final JadeConfiguration jadeConfig;
    private final ViewUtils viewUtils = new ViewUtils();
    private final Localiser localiser;

    public ViewRenderer(TemplateLoader templateLoader, Localiser localiser, PersistenceManager persistenceManager, AbstractTransferList transferList) {
        jadeConfig = new JadeConfiguration();
        jadeConfig.setTemplateLoader(templateLoader);
        jadeConfig.setCaching(false);
        jadeConfig.setPrettyPrint(false);
        jadeConfig.setFilter("invoke", new InvokeFilter());
        jadeConfig.setFilter("assets", new AssetLoader());
        this.localiser = localiser;
        this.persistenceManager = persistenceManager;
        this.transferList = transferList;
    }

    @Override
    protected void handle(uk.co.codeecho.mandrake.core.request.Request request, Response response, Map<String, Object> model) throws Exception {
        model = new HashMap<String, Object>();
        model.put("entityManager", EntityManagerFactory.getManager());
        model.put("persistenceManager", persistenceManager);
        model.put("state", GameStateManager.getGameState());
        model.put("transferList", transferList);
        model.put("utils", viewUtils);
        model.put("localiser", localiser);
        model.put("request", request);
        JadeTemplate template = jadeConfig.getTemplate("views/" + request.getPathParameter("view") + ".jade");
        String html = jadeConfig.renderTemplate(template, model);
        response.setBody(new ByteArrayInputStream(html.getBytes()));
    }

}
