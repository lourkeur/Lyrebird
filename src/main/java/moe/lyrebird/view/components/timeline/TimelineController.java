package moe.lyrebird.view.components.timeline;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import moe.tristan.easyfxml.api.FxmlController;
import moe.lyrebird.model.twitter.observables.Timeline;
import moe.lyrebird.view.components.cells.TweetListCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by tristan on 03/03/2017.
 */
@Component
public class TimelineController implements FxmlController {

    private static final Logger LOG = LoggerFactory.getLogger(TimelineController.class);

    @FXML
    private ListView<Status> tweetsListView;

    @FXML
    private Button loadMoreButton;

    private final Timeline timeline;
    private final Supplier<TweetListCell> tweetListCell;
    private final ListProperty<Status> tweetsProperty;

    public TimelineController(final Timeline timeline, final ApplicationContext context) {
        this.timeline = timeline;
        this.tweetsProperty = new ReadOnlyListWrapper<>(timeline.loadedTweets());
        this.tweetListCell = () -> context.getBean(TweetListCell.class);
    }

    @Override
    public void initialize() {
        bindUi();
        timeline.loadLastTweets();
        loadMoreButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> loadMoreTweets());
    }

    private void bindUi() {
        tweetsListView.setCellFactory(statuses -> tweetListCell.get());
        LOG.debug("Binding displayed tweets to displayable tweets...");
        tweetsListView.itemsProperty().bind(tweetsProperty);
        LOG.debug("Binded.");
    }

    private void loadMoreTweets() {
        getOldestTweetLoaded().ifPresent(oldestStatus -> timeline.loadMoreTweets(oldestStatus.getId()));
    }

    private Optional<Status> getOldestTweetLoaded() {
        if (tweetsProperty.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(tweetsProperty.get(0));
    }

}
