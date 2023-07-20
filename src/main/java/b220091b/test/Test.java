package b220091b.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
public class Test extends Application {
    private String PopName = "";
    private ComboBox<String> view_selection;
    private RadioButton popcorn_price1;
    private RadioButton popcorn_price2;
    private RadioButton popcorn_price3;
    private double price = 0.0;
    @Override
    public void start(Stage stage) throws IOException {

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        Label movie = new Label("  Movie:");
        grid.add(movie,0,0,1,1);

        TextField movie_title = new TextField();
        grid.add(movie_title,1,0,4,1);

        Label experience = new Label("  Experience:");
        grid.add(experience,0,1,1,1);

        view_selection = new ComboBox<>();
        view_selection.getItems().addAll("Beanie", "Classic", "Deluxe", "Family-Friendly", "Flexound",
                "IMAX", "Indulge", "Infinity", "Junior", "0nxy");
        grid.add(view_selection,1,1,1,1);

        Label showtime = new Label("  Session:");
        grid.add(showtime,0,4,1,1);

        RadioButton showtime_choice1 = new RadioButton("11:00AM");
        RadioButton showtime_choice2 = new RadioButton("01:30PM");
        RadioButton showtime_choice3 = new RadioButton("04:00PM");
        RadioButton showtime_choice4 = new RadioButton("06:30PM");
        RadioButton showtime_choice5 = new RadioButton("09:00PM");

        ToggleGroup timeGroup = new ToggleGroup();

        showtime_choice1.setToggleGroup(timeGroup);
        showtime_choice2.setToggleGroup(timeGroup);
        showtime_choice3.setToggleGroup(timeGroup);
        showtime_choice4.setToggleGroup(timeGroup);
        showtime_choice5.setToggleGroup(timeGroup);

        grid.add(showtime_choice1,1,2,1,1);
        grid.add(showtime_choice2,1,3,1,1);
        grid.add(showtime_choice3,1,4,1,1);
        grid.add(showtime_choice4,1,5,1,1);
        grid.add(showtime_choice5,1,6,1,1);

        Label seat = new Label("  Seat:  ");
        grid.add(seat,0,7,1,1);

        TextField seat_selection = new TextField();
        grid.add(seat_selection,1,7,4,1);

        Label food = new Label("  Food \n  & \n  Beverages:");
        grid.add(food,0,8,1,1);
        ToggleGroup popcorn_package = new ToggleGroup();

        popcorn_price1 = new RadioButton("RM 19.90");
        Text popcorn_choice1 = new Text("Royal Popcorn Combo - Member Special");
        Image image1 = new Image(Test.class.getResource("popcorn1.png").toString());
        ImageView popcorn_image1 = new ImageView(image1);
        popcorn_image1.setFitHeight(160);
        popcorn_image1.setFitWidth(250);
        popcorn_price1.setToggleGroup(popcorn_package);

        popcorn_price2 = new RadioButton("RM 17.90");
        Text popcorn_choice2 = new Text("Royal Popcorn");
        Image image2 = new Image(Test.class.getResource("popcorn2.png").toString());
        ImageView popcorn_image2 = new ImageView(image2);
        popcorn_image2.setFitHeight(160);
        popcorn_image2.setFitWidth(250);
        popcorn_price2.setToggleGroup(popcorn_package);

        popcorn_price3 = new RadioButton("RM 21.90");
        Text popcorn_choice3 = new Text("Royal Popcorn Combo");
        Image image3 = new Image(Test.class.getResource("popcorn3.png").toString());
        ImageView popcorn_image3 = new ImageView(image3);
        popcorn_image3.setFitHeight(160);
        popcorn_image3.setFitWidth(250);
        popcorn_price3.setToggleGroup(popcorn_package);

        grid.add(popcorn_image1,1,8,1,1);
        grid.add(popcorn_image2,2,8,1,1);
        grid.add(popcorn_image3,3,8,1,1);
        grid.add(popcorn_choice1,1,9,1,1);
        grid.add(popcorn_choice2,2,9,1,1);
        grid.add(popcorn_choice3,3,9,1,1);
        grid.add(popcorn_price1,1,10,1,1);
        grid.add(popcorn_price2,2,10,1,1);
        grid.add(popcorn_price3,3,10,1,1);

        Button summit = new Button("Summit");
        grid.add(summit,0,12,1,1);

        summit.setOnAction(e -> {
            // Validate user input
            if (movie_title.getText().isEmpty() || view_selection.getValue() == null ||
                    seat.getText().isEmpty() || popcorn_package.getSelectedToggle() == null ||
                    timeGroup.getSelectedToggle() == null) {
                showAlert("Please fill in all fields.");
                return;
            }

        double ticketPrice = getTicketPrice(view_selection.getValue());
        String selectedSeats = seat_selection.getText();
        String Name = PopcornNaming(PopName);
        double numberOfTickets = selectedSeats.split(",").length;
        double totalPrice = ticketPrice * numberOfTickets + PopMess(price);

        // Display user input and total amount
        String userInput = "You selected " + movie_title.getText() + " with " + view_selection.getValue() +
                " experience at " + ((RadioButton) timeGroup.getSelectedToggle()).getText() +
                " for " + selectedSeats + " seat(s) and a " + Name + ".\n" +
                "The total is RM " + totalPrice;
            showAlert(userInput);
        });

        Scene scene = new Scene(grid, 1200, 700);
        stage.setTitle("Movie System");
        stage.setScene(scene);
        stage.show();
    }

    double PopMess(double price) {
        if(popcorn_price1.isSelected()){
            price = 19.90;
        }
        if(popcorn_price2.isSelected()){
            price = 17.90;
        }
        if(popcorn_price3.isSelected()){
            price = 21.90;
        }
        return price;
    }

    //check name for radio F&B
    private String PopcornNaming(String popName) {
        if(popcorn_price1.isSelected()){
            popName = "Royal Popcorn Combo - Member Special";
        }
        if(popcorn_price2.isSelected()){
            popName = "Royal Popcorn";
        }
        if(popcorn_price3.isSelected()){
            popName = "Royal Popcorn Combo";
        }
        return popName;
    }

    private double getTicketPrice(String view_selection) {
        switch (view_selection) {
            case "Beanie":
                return 19.9;
            case "Classic":
            case "Junior":
                return 15.9;
            case "Deluxe":
            case "Family-Friendly":
                return 23.9;
            case "Flexound":
            case "IMAX":
                return 25.90;
            case "Indulge":
            case "Infinity":
                return 120.00;
            case "Onyx":
                return 89.90;
            default:
                return 0.0;
        }
    }

    private void showAlert(String userInput) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thank You!");
        alert.setHeaderText("Confirmation");
        alert.setContentText(userInput);
        alert.getDialogPane().setPrefSize(300, 200);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch();
    }
}
