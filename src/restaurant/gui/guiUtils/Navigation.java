package restaurant.gui.guiUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import restaurant.data.repositories.ReservationRepository;
import restaurant.gui.pages.*;
import restaurant.models.reservation.Reservation;
import restaurant.models.users.User;

import java.io.IOException;

public class Navigation {

    private ReservationRepository reservationRepository = new ReservationRepository();
    private Alerts alerts = new Alerts();
    private User loggedInUser;

    public void showPage(String fileName ,String title, int width, int height, Stage stage)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
            GridPane grid = createControllerInstance(this.loggedInUser,loader);
            stage.setTitle(title);
            Scene sc = new Scene(grid, width, height);
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            alerts.showErrorAlert("Data Error", "Something wrong happened!");
        }

    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    private GridPane createControllerInstance(User user , FXMLLoader loader) throws IOException {

        switch (user.getUserRole()){

            case CUSTOMER:
                CustomerDashBoardController cdc = new CustomerDashBoardController(user);
                loader.setController(cdc);
                break;

            case WAITER:
                WaiterDashboardController wdc = new WaiterDashboardController(user);
                loader.setController(wdc);
                break;

            case MANAGER:
                ManagerDashBoardController mdc = new ManagerDashBoardController(user);
                loader.setController(mdc);
                break;

            case COOK:
                CookDashBoardController cookdc = new CookDashBoardController(user);
                loader.setController(cookdc);
                break;
        }
        return loader.load();
    }

    public void showPageByRole(User user, Stage stage) {
        int width = 1200;
        int height = 700;

        switch (user.getUserRole()) {
            case CUSTOMER:
                showPage("../pages/CustomerDashBoardPage.fxml", "Hello Customer", width, height, stage);
                break;
            case COOK:
                showPage("../pages/CookDashBoardPage.fxml", "Hello Cook", width, height, stage);
                break;
            case WAITER:
                showPage("../pages/WaiterDashboardPage.fxml", "Hello Waiter", width, height, stage);
                break;
            case MANAGER:
                showPage("../pages/ManagerDashBoardPage.fxml", "Hello Manager", width, height, stage);
                break;
        }
    }

    public void showOrdersPage(String fileName , String title, int width, int height, Stage stage, Reservation res){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
            OrderController oc = new OrderController(res);
            loader.setController(oc);
            GridPane grid = loader.load();
            stage.setTitle(title);
            Scene sc = new Scene(grid, width, height);
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            alerts.showErrorAlert("Data Error", "Something wrong happened!");
        }
    }

    public void showPageWithoutController(String fileName , String title, int width, int height, Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
            GridPane grid = loader.load();
            stage.setTitle(title);
            Scene sc = new Scene(grid, width, height);
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            alerts.showErrorAlert("Data Error", "Something wrong happened!");
        }
    }

    public void logout(Stage stage)
    {
        int width = 1200; int height = 720;
        showPageWithoutController("../pages/LoginPage.fxml", "Login", width, height, stage);
    }
}