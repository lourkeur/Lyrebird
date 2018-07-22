/*
 *     Lyrebird, a free open-source cross-platform twitter client.
 *     Copyright (C) 2017-2018, Tristan Deloche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package moe.lyrebird.view.screens.root;

import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.model.exception.ExceptionHandler;
import moe.lyrebird.view.components.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import static moe.lyrebird.view.components.Component.CONTROL_BAR;

/**
 * The RootViewController manages the location of content on the root view scene.
 */
@org.springframework.stereotype.Component
public class RootScreenController implements FxmlController {

    private static final Logger LOG = LoggerFactory.getLogger(RootScreenController.class);

    @FXML
    private BorderPane contentPane;

    private final EasyFxml easyFxml;

    public RootScreenController(final EasyFxml easyFxml) {
        this.easyFxml = easyFxml;
    }

    @Override
    public void initialize() {
        loadControlBar();
        loadNotificationPane();
    }

    /**
     * Loads up the {@link Component#CONTROL_BAR} on the left side of the {@link BorderPane} which is the main
     * container for the main view.
     */
    private void loadControlBar() {
        LOG.debug("Initializing control bar...");
        final Pane controlBarPane = this.easyFxml
                .loadNode(CONTROL_BAR)
                .getNode()
                .getOrElseGet(ExceptionHandler::fromThrowable);
        LOG.debug("Initialized control bar !");
        contentPane.setLeft(controlBarPane);
    }

    /**
     * Loads up the {@link Component#NOTIFICATIONS_PANE} on the top side of the {@link BorderPane} which is the main
     * container for the main view.
     */
    private void loadNotificationPane() {
        LOG.debug("Initializing notification pane...");
        final Pane notificationPane = this.easyFxml
                .loadNode(Component.NOTIFICATIONS_PANE)
                .getNode()
                .getOrElseGet(ExceptionHandler::fromThrowable);

        this.contentPane.setTop(notificationPane);
    }

    /**
     * Helper function to load a given component as center node for the {@link BorderPane} which is the main container
     * for the main view.
     *
     * @param component The component to load.
     */
    public void setContent(final Component component) {
        LOG.info("Switching content of root pane to {}", component);
        final Pane contentNode = this.easyFxml
                .loadNode(component)
                .getNode()
                .getOrElseGet(ExceptionHandler::fromThrowable);

        this.contentPane.setCenter(contentNode);
        LOG.info("Set content of root pane to {}", component);
    }

}
