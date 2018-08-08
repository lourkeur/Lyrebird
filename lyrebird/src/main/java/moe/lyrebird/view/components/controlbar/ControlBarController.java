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

package moe.lyrebird.view.components.controlbar;

import org.springframework.stereotype.Component;
import moe.tristan.easyfxml.EasyFxml;
import moe.tristan.easyfxml.api.FxmlController;
import moe.tristan.easyfxml.model.exception.ExceptionHandler;
import moe.tristan.easyfxml.model.fxml.FxmlLoadResult;
import moe.lyrebird.view.components.FxComponent;
import moe.lyrebird.view.screens.Screen;
import moe.lyrebird.view.screens.root.RootScreenController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.stream.Stream;

import static moe.lyrebird.view.components.FxComponent.TIMELINE;
import static moe.tristan.easyfxml.model.exception.ExceptionHandler.displayExceptionPane;

/**
 * The ControlBar is the left-side view selector for Lyrebird's main UI window.
 */
@Component
public class ControlBarController implements FxmlController {

    private static final Logger LOG = LoggerFactory.getLogger(ControlBarController.class);

    @FXML
    private BorderPane container;

    @FXML
    private HBox tweet;

    @FXML
    private HBox timeline;

    @FXML
    private HBox mentions;

    @FXML
    private HBox directMessages;

    @FXML
    private HBox credits;

    @FXML
    private HBox update;

    private final EasyFxml easyFxml;
    private final RootScreenController rootScreenController;

    private final Property<HBox> currentViewButton;

    public ControlBarController(
            final EasyFxml easyFxml,
            final RootScreenController rootScreenController
    ) {
        this.easyFxml = easyFxml;
        this.rootScreenController = rootScreenController;
        this.currentViewButton = new SimpleObjectProperty<>(null);
    }

    @Override
    public void initialize() {
        currentViewButton.addListener((o, prev, current) -> {
            if (prev != null) {
                prev.setDisable(false);
                prev.setOpacity(1.0);
            }
            current.setDisable(true);
            current.setOpacity(0.5);
        });

        setUpTweetButton();

        bindActionImageToLoadingView(timeline, TIMELINE);
        loadCurrentAccountPanel();

        update.setOnMouseClicked(e -> openUpdatesScreen());
    }

    /**
     * Makes sure the tweet button appears as a nice circle through CSS and clipping work.
     */
    private void setUpTweetButton() {
        tweet.setOnMouseClicked(e -> this.openTweetWindow());
    }

    /**
     * Loads the current user's account view on the top of the bar.
     */
    private void loadCurrentAccountPanel() {
    }

    /**
     * Called on click on the {@link #tweet} box. Opens a new tweet window.
     *
     * @see Screen#NEW_TWEET_VIEW
     */
    private void openTweetWindow() {
    }

    /**
     * This method managed switching from an unlogged to a logged state. It is tied to {@link
     * SessionManager#isLoggedInProperty()}'s value.
     *
     * @param previous Whether the user was previously logged-in
     * @param current  Whether the user is not logged-in
     */
    private void handleLogStatusChange(final boolean previous, final boolean current) {
        Stream.of(
                timeline,
                tweet,
                mentions,
                directMessages,
                tweet
        ).forEach(btn -> btn.setVisible(current));

        // was not connected and now is, mostly to distinguish with the future feature of
        // multiple accounts management
        if (!previous && current) {
            timeline.onMouseClickedProperty().get().handle(null);
        }
    }

    private void bindActionImageToLoadingView(
            final HBox imageBox,
            final FxComponent FxComponent
    ) {
        imageBox.setOnMouseClicked(e -> {
            currentViewButton.setValue(imageBox);
            rootScreenController.setContent(FxComponent);
        });
    }

    /**
     * The {@link #update} box only show up when an update is detected as available. Then if it is the case, this method
     * is called on click to open the update information screen.
     *
     * @see Screen#UPDATE_VIEW
     */
    private void openUpdatesScreen() {
    }
}
