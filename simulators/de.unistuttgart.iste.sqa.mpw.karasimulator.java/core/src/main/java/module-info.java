module de.unistuttgart.kara.core {
    requires transitive mpw.framework.core;
    requires mpw.framework.utils;
    requires javafx.base;

    exports de.unistuttgart.kara.facade;
    exports de.unistuttgart.kara.kara to de.unistuttgart.kara.main;
    exports de.unistuttgart.kara.viewmodel.impl to de.unistuttgart.kara.ui;

    opens de.unistuttgart.kara.kara;
}