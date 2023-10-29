module YukMakan {
    requires javafx.controls;
    requires javafx.fxml;

    opens YukMakan to javafx.fxml;
    exports YukMakan;
}
