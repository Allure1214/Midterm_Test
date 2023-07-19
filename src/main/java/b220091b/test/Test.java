package b220091b.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class McDonaldMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(topBar());
        borderPane.setLeft(leftBar());
        borderPane.setCenter(displayMenu());

        Scene scene = new Scene(borderPane, 320, 240);
        stage.setTitle("Welcome to McDonald's!");
        stage.setScene(scene);
        stage.show();
    }

    public MenuBar topBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #3d3d3d; -fx-color: #000000");

        Menu menuMenu = new Menu("Menu");
        Menu menuOffer = new Menu("Offers");
        Menu menuAccount = new Menu("Account");
        Menu menuSupport = new Menu("Support");

        menuBar.getMenus().addAll(menuMenu, menuOffer, menuAccount, menuSupport);

        return menuBar;
    }

    public VBox leftBar() {
        VBox vb = new VBox();

        MenuButton itemBurger = new MenuButton("Burger");
        itemBurger.setPrefWidth(150);
        MenuButton itemAyam = new MenuButton("Ayam Gorend McD");
        itemAyam.setPrefWidth(150);
        MenuButton itemNugget = new MenuButton("McNuggets");
        itemNugget.setPrefWidth(150);
        MenuButton itemNasiLemak = new MenuButton("Nasi Lemak & Bubur");
        itemNasiLemak.setPrefWidth(150);

        vb.getChildren().addAll(itemBurger, itemAyam, itemNugget, itemNasiLemak);

        return vb;
    }

    public GridPane displayMenu() {
        GridPane grid = new GridPane();

        int col = 0;
        int row = 0;
        int rowLabel = 1;
        double[] price = {14.15, 17.92, 14.15, 17.92};
        Button btnOrder;
        Font font = Font.font("Verdana",FontWeight.BOLD, 15);

        for(int i=1; i < 5; i++) {
            Image image = new Image(McDonaldMenu.class.getResource("burger"+i+".png").toString());
            ImageView imageView = new ImageView(image);
            grid.add(imageView, col, row, 1, 1);

            HBox hb = new HBox();
            hb.setSpacing(60);
            hb.setPadding(new Insets(10));
            Text txtPrice = new Text("From RM"+price[i-1]);
            txtPrice.setFont(font);
            txtPrice.setFill(Color.GREEN);
            btnOrder = new Button("Order");
            hb.getChildren().addAll(txtPrice, btnOrder);

            grid.add(hb, col, rowLabel, 1, 1);

            if(col < 1) {
                col++;
            } else {
                row += 2;
                rowLabel += 2;
                col = 0;
            }
        }

        return grid;
    }
    public static void main(String[] args) {
        launch();
    }
}
