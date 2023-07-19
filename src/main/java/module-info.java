module b220091b.test {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens b220091b.test to javafx.fxml;
    exports b220091b.test;
}