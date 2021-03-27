module de.unistuttgart.kara.ui {
    requires transitive de.unistuttgart.kara.core;
    requires mpw.framework.utils;

    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;

    exports de.unistuttgart.kara.ui;
    opens de.unistuttgart.kara.ui;
    opens fxml;
    opens css;
    opens images;
}