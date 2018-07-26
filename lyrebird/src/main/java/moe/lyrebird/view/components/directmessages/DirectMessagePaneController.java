package moe.lyrebird.view.components.directmessages;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import moe.tristan.easyfxml.model.components.listview.ComponentCellFxmlController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4a.DirectMessage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DirectMessagePaneController implements ComponentCellFxmlController<DirectMessage> {

    private static final Logger LOG = LoggerFactory.getLogger(DirectMessagePaneController.class);

    @FXML
    private Label messageContent;

    @Override
    public void initialize() {

    }

    @Override
    public void updateWithValue(DirectMessage newValue) {
        LOG.debug("Direct message pane {} assigned with displaying {}", this, newValue);
        messageContent.setText(newValue.getText());
    }

}
