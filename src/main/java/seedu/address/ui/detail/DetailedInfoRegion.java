package seedu.address.ui.detail;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.UiPart;

/**
 * An ui for the status bar that is displayed at the header of the application.
 */
public class DetailedInfoRegion extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(DetailedInfoRegion.class);
    private static final String FXML = "DetailedInfoRegion.fxml";

    @FXML
    private StackPane headerBarPlaceholder;

    @FXML
    private StackPane detailedContentPlaceholder;

    /**
     * Creates a {@code DetailedInfoRegion} with the given {@code Stage} and {@code Student}.
     *
     * @param textToDisplay The text to display in the header bar.
     */
    public DetailedInfoRegion(String textToDisplay) {
        super(FXML);

        HeaderBar headerBar = new HeaderBar("Detailed Information");
        headerBarPlaceholder.getChildren().add(headerBar.getRoot());

        UiPart<Region> detailedContent = new WelcomeContent();
        detailedContentPlaceholder.getChildren().add(detailedContent.getRoot());
    }

    /**
     * Sets the header bar to display the given text.
     *
     * @param titleText The text to display in the header bar.
     */
    public void setHeaderBar(String titleText) {
        HeaderBar headerBar = new HeaderBar(titleText);
        headerBarPlaceholder.getChildren().add(headerBar.getRoot());
    }

    /**
     * Sets the detailed content to display the given UiPart.
     *
     * @param detailedContent The UiPart to display.
     */
    public void setDetailedContent(UiPart<Region> detailedContent) {
        detailedContentPlaceholder.getChildren().clear();
        detailedContentPlaceholder.getChildren().add(detailedContent.getRoot());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailedInfoRegion // instanceof handles nulls
                && headerBarPlaceholder.equals(((DetailedInfoRegion) other).headerBarPlaceholder)
                && detailedContentPlaceholder
                        .equals(((DetailedInfoRegion) other).detailedContentPlaceholder)); // state check
    }
}
